package com.raf.cedaandreja.KorisnickiServis.service;

import com.raf.cedaandreja.KorisnickiServis.dto.ManagerCreateDto;
import com.raf.cedaandreja.KorisnickiServis.dto.ManagerDto;

import java.util.List;

public interface ManagerService {
    List<ManagerDto> findAllManagers();

    ManagerDto findManager(String ime, String prezime);

    ManagerDto addManager(ManagerCreateDto managerDto);

    ManagerDto updateManager(ManagerDto managerDto);
}
