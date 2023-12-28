package com.raf.cedaandreja.KorisnickiServis.service;

import com.raf.cedaandreja.KorisnickiServis.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KlijentService {
    Page<KlijentDto> findAllKlijents(Pageable pageable);

    KlijentDto findKlijent(String ime, String prezime);

    KlijentDto addKlijent(KlijentCreateDto klijentCreateDto);

    KlijentDto updateKlijent(KlijentUpdateDto klijentUpdateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    KlijentDto setForbidenKlijent(String username,Boolean forbiden);
}
