package com.raf.cedaandreja.ZakazivanjeServis.repository;

import com.raf.cedaandreja.ZakazivanjeServis.domain.Termin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TerminRepository extends JpaRepository<Termin,Long> {

    Page<Termin> findTerminByDan(String dan,Pageable pageable);

    Page<Termin> findTerminByTip(String tip,Pageable pageable);

    Page<Termin> findAllByOrderByVremeOd(Pageable pageable);

    List<Termin> findByTrenutanBrojOsobaLessThanAndTip(int maxBrojOsoba, String tip);

}
