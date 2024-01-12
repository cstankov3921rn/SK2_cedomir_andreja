package com.raf.cedaandreja.KorisnickiServis.service;

import com.raf.cedaandreja.KorisnickiServis.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerService {
    Page<ManagerDto> findAllManagers(Pageable pageable);

    ManagerDto findManager(String ime, String prezime);

    ManagerDto addManager(ManagerCreateDto managerDto);

    ManagerDto updateManager(ManagerUpdateDto managerUpdateDto);

    ManagerDto updatePasswordMagager(UpdatePaswordDto updatePaswordDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);

    ManagerDto setForbidenManager(String username,Boolean forbiden);

    ManagerDto findManagerId(Long managerId);
}
