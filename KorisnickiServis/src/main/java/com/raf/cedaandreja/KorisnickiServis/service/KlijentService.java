package com.raf.cedaandreja.KorisnickiServis.service;

import com.raf.cedaandreja.KorisnickiServis.dto.KlijentDto;

import java.util.List;

public interface KlijentService {
    List<KlijentDto> findAllKlijents();

    KlijentDto findKlijent(String ime, String prezime);

    KlijentDto addKlijent(KlijentDto klijentDto);

    KlijentDto updateKlijent(KlijentDto klijentDto);
}
