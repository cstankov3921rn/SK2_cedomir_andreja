package com.raf.cedaandreja.KorisnickiServis.service.impl;

import com.raf.cedaandreja.KorisnickiServis.domain.Klijent;
import com.raf.cedaandreja.KorisnickiServis.domain.Manager;
import com.raf.cedaandreja.KorisnickiServis.domain.User;
import com.raf.cedaandreja.KorisnickiServis.dto.KlijentCreateDto;
import com.raf.cedaandreja.KorisnickiServis.dto.KlijentDto;
import com.raf.cedaandreja.KorisnickiServis.mapper.KlijentMapper;
import com.raf.cedaandreja.KorisnickiServis.repository.KlijentRepository;
import com.raf.cedaandreja.KorisnickiServis.repository.ManagerRepository;
import com.raf.cedaandreja.KorisnickiServis.service.KlijentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlijentServiceImpl implements KlijentService {
    private KlijentRepository klijentRepository;
    private KlijentMapper klijentMapper;

    public KlijentServiceImpl(KlijentRepository klijentRepository,KlijentMapper klijentMapper){
        this.klijentRepository=klijentRepository;
        this.klijentMapper = klijentMapper;
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
    public KlijentDto addKlijent(KlijentCreateDto klijentCreateDto) {
        User klijent = klijentMapper.klijentCreateDtoToKlijent(klijentCreateDto);
        klijentRepository.save((Klijent) klijent);
        return klijentMapper.klijentToKlijentDto((Klijent) klijent);
    }

    @Override
    public KlijentDto updateKlijent(KlijentDto klijentDto) {
        return null;
    }
}
