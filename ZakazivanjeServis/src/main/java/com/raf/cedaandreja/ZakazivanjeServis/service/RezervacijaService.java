package com.raf.cedaandreja.ZakazivanjeServis.service;

import com.raf.cedaandreja.ZakazivanjeServis.dto.RezervacijaCreateDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.RezervacijaDto;

public interface RezervacijaService {
    RezervacijaDto rezervisi(RezervacijaCreateDto rezervacijaCreateDto);

    void otkaziManager(Long id);

    void otkaziKlijent(Long id);
}
