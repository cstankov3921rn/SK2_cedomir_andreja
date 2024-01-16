package com.raf.cedaandreja.ZakazivanjeServis.repository;

import com.raf.cedaandreja.ZakazivanjeServis.domain.FiskulturnaSala;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FiskulturnaSalaRepository extends JpaRepository<FiskulturnaSala, Long> {
    Optional<FiskulturnaSala> findFiskulturnaSalaByIme(String ime);

    Optional<FiskulturnaSala> findFiskulturnaSalaById(Long id);

    Page<FiskulturnaSala> findFiskulturnaSalaByManagerId(String managerId, Pageable pageable);

}
