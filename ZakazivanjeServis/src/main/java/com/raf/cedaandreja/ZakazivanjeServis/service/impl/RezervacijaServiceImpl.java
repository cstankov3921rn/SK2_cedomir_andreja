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
import org.springframework.stereotype.Service;

@Service
public class RezervacijaServiceImpl implements RezervacijaService {

    private RezervacijaRepository rezervacijaRepository;

    private RezervacijaMapper rezervacijaMapper;

    private TerminRepository terminRepository;

    public RezervacijaServiceImpl(RezervacijaRepository rezervacijaRepository, RezervacijaMapper rezervacijaMapper, TerminRepository terminRepository) {
        this.rezervacijaRepository = rezervacijaRepository;
        this.rezervacijaMapper = rezervacijaMapper;
        this.terminRepository = terminRepository;
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
        terminRepository.save(termin);
        rezervacijaRepository.save(rezervacija);
        return rezervacijaMapper.rezervacijaToRezervacijaDto(rezervacija);
    }

    @Override
    public void otkaziManager(Long id) {
        Termin termin = terminRepository.findById(rezervacijaRepository.findById(id).get().getTerminId()).orElseThrow(()->new NotFoundException(String.format("Termin sa id-jem %s nije nadjen",rezervacijaRepository.findById(id).get().getTerminId())));
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
        terminRepository.save(termin);
        rezervacijaRepository.delete(rezervacijaRepository.findById(id).get());
    }
}
