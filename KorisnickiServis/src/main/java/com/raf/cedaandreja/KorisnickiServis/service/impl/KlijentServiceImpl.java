package com.raf.cedaandreja.KorisnickiServis.service.impl;

import com.raf.cedaandreja.KorisnickiServis.dto.KlijentDto;
import com.raf.cedaandreja.KorisnickiServis.repository.KlijentRepository;
import com.raf.cedaandreja.KorisnickiServis.repository.ManagerRepository;
import com.raf.cedaandreja.KorisnickiServis.service.KlijentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlijentServiceImpl implements KlijentService {
    private KlijentRepository klijentRepository;
    //mapper

    public KlijentServiceImpl(KlijentRepository klijentRepository){
        this.klijentRepository=klijentRepository;
    }
    @Override
    public List<KlijentDto> findAllKlijents() {
        return null;
    }

    @Override
    public KlijentDto findKlijent(String ime, String prezime) {
        return null;
    }

    @Override
    public KlijentDto addKlijent(KlijentDto klijentDto) {
        return null;
    }

    @Override
    public KlijentDto updateKlijent(KlijentDto klijentDto) {
        return null;
    }
}
