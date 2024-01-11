package com.raf.cedaandreja.ZakazivanjeServis.service.impl;

import com.raf.cedaandreja.ZakazivanjeServis.domain.Rezervacija;
import com.raf.cedaandreja.ZakazivanjeServis.domain.Termin;
import com.raf.cedaandreja.ZakazivanjeServis.dto.RezervacijaCreateDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.RezervacijaDto;
import com.raf.cedaandreja.ZakazivanjeServis.exception.NotFoundException;
import com.raf.cedaandreja.ZakazivanjeServis.mapper.RezervacijaMapper;
import com.raf.cedaandreja.ZakazivanjeServis.repository.RezervacijaRepository;
import com.raf.cedaandreja.ZakazivanjeServis.repository.TerminRepository;
import com.raf.cedaandreja.ZakazivanjeServis.service.RezervacijaService;
import com.raf.cedaandreja.ZakazivanjeServis.userservice.dto.KlijentDto;
import io.github.resilience4j.retry.Retry;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {

    private RezervacijaRepository rezervacijaRepository;

    private RezervacijaMapper rezervacijaMapper;

    private TerminRepository terminRepository;

    private RestTemplate userServiceRestTemplate;

    private Retry userServiceRetry;

    public RezervacijaServiceImpl(RezervacijaRepository rezervacijaRepository, RezervacijaMapper rezervacijaMapper, TerminRepository terminRepository,RestTemplate userServiceRestTemplate,Retry userServiceRetry) {
        this.rezervacijaRepository = rezervacijaRepository;
        this.rezervacijaMapper = rezervacijaMapper;
        this.terminRepository = terminRepository;
        this.userServiceRestTemplate=userServiceRestTemplate;
        this.userServiceRetry=userServiceRetry;
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
        ResponseEntity<KlijentDto> klijentDtoResponseEntity = userServiceRestTemplate.exchange("/klijent/"+rezervacijaRepository.findById(id).get().getUserId()+"/smanjiBrojZakazanihTermina", HttpMethod.POST,null,KlijentDto.class);
        terminRepository.delete(termin);
        rezervacijaRepository.delete(rezervacijaRepository.findById(id).get());
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
        terminRepository.save(termin);
        rezervacijaRepository.delete(rezervacijaRepository.findById(id).get());
    }
}
