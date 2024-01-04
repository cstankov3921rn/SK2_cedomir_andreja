package com.raf.cedaandreja.ZakazivanjeServis.repository;

import com.raf.cedaandreja.ZakazivanjeServis.domain.Termin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TerminRepository extends JpaRepository<Termin,Long> {

    Optional<Termin> findTerminByDan(String dan);

    Optional<Termin> findTerminByTip(String tip);

    Page<Termin> findAllByOrderByVremeOd(Pageable pageable);

}
