package com.raf.cedaandreja.ZakazivanjeServis.repository;

import com.raf.cedaandreja.ZakazivanjeServis.domain.FiskulturnaSala;
import com.raf.cedaandreja.ZakazivanjeServis.domain.Rezervacija;
import com.raf.cedaandreja.ZakazivanjeServis.domain.Termin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
    Optional<Rezervacija> findByUserIdAndTerminId(Long userId, Long terminId);

    Page<Rezervacija> findRezervacijaByUserId(Long userId, Pageable pageable);
}
