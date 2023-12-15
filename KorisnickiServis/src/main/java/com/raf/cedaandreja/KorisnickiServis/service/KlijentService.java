package com.raf.cedaandreja.KorisnickiServis.service;

import com.raf.cedaandreja.KorisnickiServis.dto.KlijentCreateDto;
import com.raf.cedaandreja.KorisnickiServis.dto.KlijentDto;

import java.util.List;

public interface KlijentService {
    List<KlijentDto> findAllKlijents();

    KlijentDto findKlijent(String ime, String prezime);

    KlijentDto addKlijent(KlijentCreateDto klijentCreateDto);

    KlijentDto updateKlijent(KlijentDto klijentDto);
}
