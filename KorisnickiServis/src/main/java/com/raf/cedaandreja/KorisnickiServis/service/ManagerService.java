package com.raf.cedaandreja.KorisnickiServis.service;

import com.raf.cedaandreja.KorisnickiServis.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ManagerService {
    Page<ManagerDto> findAllManagers(Pageable pageable);

    ManagerDto findManager(String ime, String prezime);

    ManagerDto addManager(ManagerCreateDto managerDto);

    ManagerDto updateManager(ManagerUpdateDto managerUpdateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    ManagerDto setForbidenManager(String username,Boolean forbiden);
}
