package com.raf.cedaandreja.KorisnickiServis.repository;

import com.raf.cedaandreja.KorisnickiServis.domain.Klijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KlijentRepository extends JpaRepository<Klijent,Long> {
    Optional<Klijent> findKlijentByUsernameAndPassword(String username, String password);
}
