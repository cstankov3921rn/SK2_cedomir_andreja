package com.raf.cedaandreja.ZakazivanjeServis.scheduler;

import com.raf.cedaandreja.ZakazivanjeServis.domain.Rezervacija;
import com.raf.cedaandreja.ZakazivanjeServis.domain.Termin;
import com.raf.cedaandreja.ZakazivanjeServis.dto.TerminDto;
import com.raf.cedaandreja.ZakazivanjeServis.exception.NotFoundException;
import com.raf.cedaandreja.ZakazivanjeServis.repository.RezervacijaRepository;
import com.raf.cedaandreja.ZakazivanjeServis.repository.TerminRepository;
import com.raf.cedaandreja.ZakazivanjeServis.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class MyScheduler {

    @Autowired
    private RezervacijaRepository rezervacijaRepository;
    @Autowired
    private TerminRepository terminRepository;
    @Autowired
    private RezervacijaService rezervacijaService;

    @Scheduled(cron="0 */5 * * * *")
    public void executeTask(){

        List<Rezervacija> rezervacijaList = rezervacijaRepository.findAll();
        for(Rezervacija rezervacija:rezervacijaList){ //posto u rezervaciji nemam vreme, moram da nadjem za koji termin je vezana pa onda odatle da vidim vremeOd
            Termin termin = terminRepository.findById(rezervacija.getTerminId()).orElseThrow(()->new NotFoundException(String.format("Termin sa tim id-jem nije nadjen")));
            LocalDateTime vremeOd = LocalDateTime.of(termin.getDatum().getYear(),termin.getDatum().getMonth(),termin.getDatum().getDayOfMonth(),termin.getVremeOd().getHour(),termin.getVremeOd().getMinute());
            Duration razlikaSati = Duration.between(LocalDateTime.now(), vremeOd);
            System.out.println(razlikaSati.toHours());
            if(termin.getTip().equals("grupni") && termin.getTrenutanBrojOsoba()<3 && razlikaSati.toHours()<24){
                rezervacijaService.otkaziManager(rezervacija.getId());
            }

        }
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa\n");

    }
}