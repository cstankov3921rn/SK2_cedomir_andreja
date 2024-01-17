package com.raf.cedaandreja.ZakazivanjeServis.service;

import com.raf.cedaandreja.ZakazivanjeServis.dto.RezervacijaCreateDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.RezervacijaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RezervacijaService {
    RezervacijaDto rezervisi(RezervacijaCreateDto rezervacijaCreateDto);

    void otkaziManager(Long id);

    void otkaziKlijent(Long id);

    Page<RezervacijaDto> findRezervacijeOdKlijenta(Long userId, Pageable pageable);

    Page<RezervacijaDto> findSveRezervacije(Pageable pageable);
}
