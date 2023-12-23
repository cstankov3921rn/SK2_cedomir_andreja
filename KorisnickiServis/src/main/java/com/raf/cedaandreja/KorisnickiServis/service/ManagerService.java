package com.raf.cedaandreja.KorisnickiServis.service;

import com.raf.cedaandreja.KorisnickiServis.dto.ManagerCreateDto;
import com.raf.cedaandreja.KorisnickiServis.dto.ManagerDto;
import com.raf.cedaandreja.KorisnickiServis.dto.TokenRequestDto;
import com.raf.cedaandreja.KorisnickiServis.dto.TokenResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ManagerService {
    Page<ManagerDto> findAllManagers(Pageable pageable);

    ManagerDto findManager(String ime, String prezime);

    ManagerDto addManager(ManagerCreateDto managerDto);

    ManagerDto updateManager(ManagerDto managerDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
