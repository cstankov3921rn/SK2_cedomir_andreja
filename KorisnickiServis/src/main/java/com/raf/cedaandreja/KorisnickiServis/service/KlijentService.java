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

    KlijentDto updatePasswordKlijent(UpdatePaswordDto updatePaswordDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    KlijentDto setForbidenKlijent(String username,Boolean forbiden);

    void povecajBrojZakazanihTermina(Long klijentId);

    void smanjiBrojZakazanihTermina(Long klijentId);

    int getBrojZakazanihTermina(Long klijentId);

    KlijentDto findKlijentId(Long klijentId);
}
