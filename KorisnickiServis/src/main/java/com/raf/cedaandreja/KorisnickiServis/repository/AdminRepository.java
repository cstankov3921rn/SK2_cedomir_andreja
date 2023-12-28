package com.raf.cedaandreja.KorisnickiServis.repository;

import com.raf.cedaandreja.KorisnickiServis.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    Optional<Admin> findAdminByUsernameAndPassword(String username, String password);
}
