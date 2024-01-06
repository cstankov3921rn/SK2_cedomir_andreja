package com.raf.cedaandreja.ZakazivanjeServis.mapper;

import com.raf.cedaandreja.ZakazivanjeServis.domain.Rezervacija;
import com.raf.cedaandreja.ZakazivanjeServis.dto.RezervacijaCreateDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.RezervacijaDto;
import com.raf.cedaandreja.ZakazivanjeServis.repository.TerminRepository;
import com.raf.cedaandreja.ZakazivanjeServis.repository.TipTreningaRepository;
import org.springframework.stereotype.Component;

@Component
public class RezervacijaMapper {
    private TerminMapper terminMapper;
    private TerminRepository terminRepository;

    public RezervacijaMapper(TerminMapper terminMapper, TerminRepository terminRepository) {
        this.terminMapper = terminMapper;
        this.terminRepository = terminRepository;
    }

    public RezervacijaDto rezervacijaToRezervacijaDto(Rezervacija rezervacija) {
        RezervacijaDto rezervacijaDto = new RezervacijaDto();
        rezervacijaDto.setId(rezervacija.getId());
        rezervacijaDto.setUserId(rezervacija.getUserId());
        rezervacijaDto.setTerminId(rezervacijaDto.getTerminId());
        rezervacijaDto.setCena(rezervacija.getCena());
        return rezervacijaDto;
    }

    public Rezervacija rezervacijaCreateDtoToRezervacija(RezervacijaCreateDto rezervacijaCreateDto){
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setUserId(rezervacijaCreateDto.getUserId());
        rezervacija.setTerminId(rezervacijaCreateDto.getTerminId());
        rezervacija.setCena(terminRepository.findById(rezervacijaCreateDto.getTerminId()).get().getTipTreninga().getCena());
        return rezervacija;
    }
}
