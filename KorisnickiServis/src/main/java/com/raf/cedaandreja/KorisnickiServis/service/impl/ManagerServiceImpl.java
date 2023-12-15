package com.raf.cedaandreja.KorisnickiServis.service.impl;

import com.raf.cedaandreja.KorisnickiServis.domain.Manager;
import com.raf.cedaandreja.KorisnickiServis.domain.User;
import com.raf.cedaandreja.KorisnickiServis.dto.ManagerCreateDto;
import com.raf.cedaandreja.KorisnickiServis.dto.ManagerDto;
import com.raf.cedaandreja.KorisnickiServis.mapper.ManagerMapper;
import com.raf.cedaandreja.KorisnickiServis.repository.ManagerRepository;
import com.raf.cedaandreja.KorisnickiServis.service.ManagerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    private ManagerRepository managerRepository;
    private ManagerMapper managerMapper;

    public ManagerServiceImpl(ManagerRepository managerRepository,  ManagerMapper managerMapper){
        this.managerRepository=managerRepository;
        this.managerMapper=managerMapper;
    }
    @Override
    public List<ManagerDto> findAllManagers() {
        return null;
    }

    @Override
    public ManagerDto findManager(String ime, String prezime) {
        return null;
    }

    @Override
    public ManagerDto addManager(ManagerCreateDto managerCreateDto) {
        User manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        managerRepository.save((Manager) manager);
        return managerMapper.managerToManagerDto((Manager) manager);
    }
    @Override
    public ManagerDto updateManager(ManagerDto managerDto) {
        return null;
    }
}
