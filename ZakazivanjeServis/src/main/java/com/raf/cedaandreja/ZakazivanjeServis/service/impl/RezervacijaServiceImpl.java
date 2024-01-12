package com.raf.cedaandreja.ZakazivanjeServis.service.impl;

import com.raf.cedaandreja.ZakazivanjeServis.domain.Rezervacija;
import com.raf.cedaandreja.ZakazivanjeServis.domain.Termin;
import com.raf.cedaandreja.ZakazivanjeServis.dto.NotificationDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.RezervacijaCreateDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.RezervacijaDto;
import com.raf.cedaandreja.ZakazivanjeServis.exception.NotFoundException;
import com.raf.cedaandreja.ZakazivanjeServis.mapper.RezervacijaMapper;
import com.raf.cedaandreja.ZakazivanjeServis.notification.NotificationApi;
import com.raf.cedaandreja.ZakazivanjeServis.repository.RezervacijaRepository;
import com.raf.cedaandreja.ZakazivanjeServis.repository.TerminRepository;
import com.raf.cedaandreja.ZakazivanjeServis.service.RezervacijaService;
import com.raf.cedaandreja.ZakazivanjeServis.userservice.dto.KlijentDto;
import com.raf.cedaandreja.ZakazivanjeServis.userservice.dto.ManagerDto;
import io.github.resilience4j.retry.Retry;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {

    private RezervacijaRepository rezervacijaRepository;

    private RezervacijaMapper rezervacijaMapper;

    private TerminRepository terminRepository;

    private RestTemplate userServiceRestTemplate;

    private Retry userServiceRetry;

    private NotificationApi notificationApi;;

    public RezervacijaServiceImpl(RezervacijaRepository rezervacijaRepository, RezervacijaMapper rezervacijaMapper, TerminRepository terminRepository,RestTemplate userServiceRestTemplate,Retry userServiceRetry,NotificationApi notificationApi) {
        this.rezervacijaRepository = rezervacijaRepository;
        this.rezervacijaMapper = rezervacijaMapper;
        this.terminRepository = terminRepository;
        this.userServiceRestTemplate=userServiceRestTemplate;
        this.userServiceRetry=userServiceRetry;
        this.notificationApi=notificationApi;
    }

    @Override
    public RezervacijaDto rezervisi(RezervacijaCreateDto rezervacijaCreateDto) {
        Rezervacija rezervacija = rezervacijaMapper.rezervacijaCreateDtoToRezervacija(rezervacijaCreateDto);
        Termin termin = terminRepository.findById(rezervacija.getTerminId()).orElseThrow(()->new NotFoundException(String.format("Termin sa id-jem %s nije nadjen",rezervacija.getTerminId())));
        if(termin.getTipTreninga().getTip().equals("grupni") && termin.getTrenutanBrojOsoba()<termin.getMaxBrojOsoba()){
            termin.setTrenutanBrojOsoba(termin.getTrenutanBrojOsoba()+1);
            if(termin.getTrenutanBrojOsoba()==termin.getMaxBrojOsoba())
                termin.setZauzet(true);
        }else if(termin.getTipTreninga().getTip().equals("individualni") && !termin.isZauzet()){
            termin.setZauzet(true);
        }else{
            return null;
        }
        ResponseEntity<KlijentDto> klijentDtoResponseEntity = Retry.decorateSupplier(userServiceRetry, ()->getKlijent(rezervacijaCreateDto.getUserId())).get();
        ResponseEntity<Integer> brojZakazanihTerminaResponseEntity = userServiceRestTemplate.exchange("/klijent/"+rezervacijaCreateDto.getUserId()+"/brojZakazanihTermina", HttpMethod.GET,null,Integer.class);
        int brojZakazanihTermina = brojZakazanihTerminaResponseEntity.getBody();
        if(brojZakazanihTermina%Integer.parseInt(termin.getFiskulturnaSala().getBesplatanTrening())==0){
            rezervacija.setCena("0");
        }
        terminRepository.save(termin);
        rezervacijaRepository.save(rezervacija);
        ResponseEntity<KlijentDto> klijentDtoResponseEntity2 = userServiceRestTemplate.exchange("/klijent/"+rezervacijaCreateDto.getUserId(),HttpMethod.GET,null, KlijentDto.class);
        String klijentEmail = klijentDtoResponseEntity2.getBody().getEmail();
        ResponseEntity<ManagerDto> managerDtoResponseEntity = userServiceRestTemplate.exchange("/manager/"+termin.getFiskulturnaSala().getManagerId(), HttpMethod.GET,null, ManagerDto.class);
        String managerEmail = managerDtoResponseEntity.getBody().getEmail();
        NotificationDto nt = new NotificationDto();
        nt.setKorisnik(klijentEmail);
        nt.setType("schedule");
        LocalDateTime datumvreme = LocalDateTime.of(termin.getDatum().getYear(),termin.getDatum().getMonth(),termin.getDatum().getDayOfMonth(),termin.getVremeOd().getHour(),termin.getVremeOd().getMinute());
        nt.setLink(datumvreme.toString());
        notificationApi.sendNotification(nt);
        NotificationDto nt2 = new NotificationDto();
        nt2.setKorisnik(managerEmail);
        nt2.setType("schedule");
        nt2.setLink(datumvreme.toString());
        notificationApi.sendNotification(nt2);
        return rezervacijaMapper.rezervacijaToRezervacijaDto(rezervacija);
    }

    public ResponseEntity<KlijentDto> getKlijent(Long klijentId){
        System.out.println("["+System.currentTimeMillis() /1000 + "] Getting Klijent for id: "+klijentId);
        try {
            Thread.sleep(5000);
            return userServiceRestTemplate.exchange("/klijent/"+klijentId+"/povecajBrojZakazanihTermina", HttpMethod.POST,null,KlijentDto.class);
        } catch(HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Klijent wiht id: %d not found.",klijentId));
            throw new RuntimeException("Internal server error.");
        } catch (Exception e){
            throw new RuntimeException("Internal server error.");
        }
    }

    @Override
    public void otkaziManager(Long id) {
        Termin termin = terminRepository.findById(rezervacijaRepository.findById(id).get().getTerminId()).orElseThrow(()->new NotFoundException(String.format("Termin sa id-jem %s nije nadjen",rezervacijaRepository.findById(id).get().getTerminId())));

        ResponseEntity<ManagerDto> managerDtoResponseEntity = userServiceRestTemplate.exchange("/manager/"+termin.getFiskulturnaSala().getManagerId(), HttpMethod.GET,null, ManagerDto.class);
        String managerEmail = managerDtoResponseEntity.getBody().getEmail();
        LocalDateTime datumvreme = LocalDateTime.of(termin.getDatum().getYear(),termin.getDatum().getMonth(),termin.getDatum().getDayOfMonth(),termin.getVremeOd().getHour(),termin.getVremeOd().getMinute());
        NotificationDto nt2 = new NotificationDto();
        nt2.setKorisnik(managerEmail);
        nt2.setType("cancel");
        nt2.setLink(datumvreme.toString());
        notificationApi.sendNotification(nt2);

        List<Rezervacija> rezervacijaList = rezervacijaRepository.findAll();
        for(Rezervacija rezervacija:rezervacijaList){
            if(rezervacija.getTerminId().equals(termin.getId())){
                ResponseEntity<KlijentDto> klijentDtoResponseEntity = userServiceRestTemplate.exchange("/klijent/"+rezervacija.getUserId()+"/smanjiBrojZakazanihTermina", HttpMethod.POST,null,KlijentDto.class);
                ResponseEntity<KlijentDto> klijentDtoResponseEntity2 = userServiceRestTemplate.exchange("/klijent/"+rezervacija.getUserId(),HttpMethod.GET,null, KlijentDto.class);
                String klijentEmail = klijentDtoResponseEntity2.getBody().getEmail();
                NotificationDto nt = new NotificationDto();
                nt.setKorisnik(klijentEmail);
                nt.setType("cancel");
                nt.setLink(datumvreme.toString());
                notificationApi.sendNotification(nt);
                rezervacijaRepository.delete(rezervacija);
            }
        }
        terminRepository.delete(termin);
    }

    @Override
    public void otkaziKlijent(Long id) {
        Termin termin = terminRepository.findById(rezervacijaRepository.findById(id).get().getTerminId()).orElseThrow(()->new NotFoundException(String.format("Termin sa id-jem %s nije nadjen",rezervacijaRepository.findById(id).get().getTerminId())));
        if(termin.getTipTreninga().getTip().equals("grupni")){
            termin.setTrenutanBrojOsoba(termin.getTrenutanBrojOsoba()-1);
            termin.setZauzet(false);
        }else if(termin.getTipTreninga().getTip().equals("individualni")){
            termin.setZauzet(false);
        }else{
            return;
        }
        ResponseEntity<KlijentDto> klijentDtoResponseEntity = userServiceRestTemplate.exchange("/klijent/"+rezervacijaRepository.findById(id).get().getUserId()+"/smanjiBrojZakazanihTermina", HttpMethod.POST,null,KlijentDto.class);

        ResponseEntity<KlijentDto> klijentDtoResponseEntity2 = userServiceRestTemplate.exchange("/klijent/"+rezervacijaRepository.findById(id).get().getUserId(),HttpMethod.GET,null, KlijentDto.class);
        String klijentEmail = klijentDtoResponseEntity2.getBody().getEmail();
        ResponseEntity<ManagerDto> managerDtoResponseEntity = userServiceRestTemplate.exchange("/manager/"+termin.getFiskulturnaSala().getManagerId(), HttpMethod.GET,null, ManagerDto.class);
        String managerEmail = managerDtoResponseEntity.getBody().getEmail();
        NotificationDto nt = new NotificationDto();
        nt.setKorisnik(klijentEmail);
        nt.setType("cancel");
        LocalDateTime datumvreme = LocalDateTime.of(termin.getDatum().getYear(),termin.getDatum().getMonth(),termin.getDatum().getDayOfMonth(),termin.getVremeOd().getHour(),termin.getVremeOd().getMinute());
        nt.setLink(datumvreme.toString());
        notificationApi.sendNotification(nt);
        NotificationDto nt2 = new NotificationDto();
        nt2.setKorisnik(managerEmail);
        nt2.setType("cancel");
        nt2.setLink(datumvreme.toString());
        notificationApi.sendNotification(nt2);

        terminRepository.save(termin);
        rezervacijaRepository.delete(rezervacijaRepository.findById(id).get());
    }
}
