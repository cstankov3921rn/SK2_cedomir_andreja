package com.raf.cedaandreja.KorisnickiServis.service;

import com.raf.cedaandreja.KorisnickiServis.dto.KlijentCreateDto;
import com.raf.cedaandreja.KorisnickiServis.dto.KlijentDto;
import com.raf.cedaandreja.KorisnickiServis.dto.TokenRequestDto;
import com.raf.cedaandreja.KorisnickiServis.dto.TokenResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KlijentService {
    Page<KlijentDto> findAllKlijents(Pageable pageable);

    KlijentDto findKlijent(String ime, String prezime);

    KlijentDto addKlijent(KlijentCreateDto klijentCreateDto);

    KlijentDto updateKlijent(KlijentDto klijentDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
