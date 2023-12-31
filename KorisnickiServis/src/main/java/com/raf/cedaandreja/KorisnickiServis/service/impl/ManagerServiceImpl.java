package com.raf.cedaandreja.KorisnickiServis.service.impl;

import com.raf.cedaandreja.KorisnickiServis.domain.Manager;
import com.raf.cedaandreja.KorisnickiServis.domain.User;
import com.raf.cedaandreja.KorisnickiServis.dto.*;
import com.raf.cedaandreja.KorisnickiServis.exception.ForbiddenUserException;
import com.raf.cedaandreja.KorisnickiServis.exception.NotFoundException;
import com.raf.cedaandreja.KorisnickiServis.mapper.ManagerMapper;
import com.raf.cedaandreja.KorisnickiServis.repository.ManagerRepository;
import com.raf.cedaandreja.KorisnickiServis.security.service.TokenService;
import com.raf.cedaandreja.KorisnickiServis.service.ManagerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class ManagerServiceImpl implements ManagerService {

    private ManagerRepository managerRepository;
    private ManagerMapper managerMapper;
    private TokenService tokenService;

    public ManagerServiceImpl(ManagerRepository managerRepository,  ManagerMapper managerMapper, TokenService tokenService){
        this.managerRepository=managerRepository;
        this.managerMapper=managerMapper;
        this.tokenService = tokenService;
    }
    @Override
    public Page<ManagerDto> findAllManagers(Pageable pageable) {
        return managerRepository.findAll(pageable).map(managerMapper::managerToManagerDto);
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
    public ManagerDto updateManager(ManagerUpdateDto managerUpdateDto) {
        User manager = managerRepository.findManagerByUsername(managerUpdateDto.getOldUsername()).orElseThrow(()->new NotFoundException(String.format("User with username %s not found",managerUpdateDto.getOldUsername())));
        manager = managerMapper.managerUpdateDtoToManager((Manager) manager, managerUpdateDto);
        manager = managerRepository.save((Manager) manager);
        return managerMapper.managerToManagerDto((Manager) manager);

    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        //Try to find active user for specified credentials
        Manager manager = managerRepository
                .findManagerByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        if(manager.isForbiden()){
            throw new ForbiddenUserException("Manager is forbidden");
        }
        Claims claims = Jwts.claims();
        claims.put("id", manager.getId());
        claims.put("role", "Manager");
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public ManagerDto setForbidenManager(String username, Boolean forbiden) {
        User manager = managerRepository.findManagerByUsername(username).orElseThrow(()->new NotFoundException(String.format("Manager with username %s not found",username)));
        ((Manager)manager).setForbiden(forbiden);
        manager = managerRepository.save((Manager) manager);
        return managerMapper.managerToManagerDto((Manager) manager);
    }
}
