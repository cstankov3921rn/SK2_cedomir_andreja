package com.raf.cedaandreja.KorisnickiServis.service.impl;

import com.raf.cedaandreja.KorisnickiServis.domain.Klijent;
import com.raf.cedaandreja.KorisnickiServis.domain.User;
import com.raf.cedaandreja.KorisnickiServis.dto.*;
import com.raf.cedaandreja.KorisnickiServis.exception.ForbiddenUserException;
import com.raf.cedaandreja.KorisnickiServis.exception.NotFoundException;
import com.raf.cedaandreja.KorisnickiServis.mapper.KlijentMapper;
import com.raf.cedaandreja.KorisnickiServis.repository.KlijentRepository;
import com.raf.cedaandreja.KorisnickiServis.security.service.TokenService;
import com.raf.cedaandreja.KorisnickiServis.service.KlijentService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class KlijentServiceImpl implements KlijentService {
    private KlijentRepository klijentRepository;
    private KlijentMapper klijentMapper;
    private TokenService tokenService;

    public KlijentServiceImpl(KlijentRepository klijentRepository, KlijentMapper klijentMapper, TokenService tokenService) {
        this.klijentRepository = klijentRepository;
        this.klijentMapper = klijentMapper;
        this.tokenService = tokenService;
    }

    @Override
    public Page<KlijentDto> findAllKlijents(Pageable pageable) {
        return klijentRepository.findAll(pageable).map(klijentMapper::klijentToKlijentDto);
    }

    @Override
    public KlijentDto findKlijent(String ime, String prezime) {
        return null;
    }

    @Override
    public KlijentDto addKlijent(KlijentCreateDto klijentCreateDto) {
        User klijent = klijentMapper.klijentCreateDtoToKlijent(klijentCreateDto);
        klijentRepository.save((Klijent) klijent);
        return klijentMapper.klijentToKlijentDto((Klijent) klijent);
    }

    @Override
    public KlijentDto updateKlijent(KlijentUpdateDto klijentUpdateDto) {
        User klijent = klijentRepository.findKlijentByUsername(klijentUpdateDto.getOldUsername()).orElseThrow(()->new NotFoundException(String.format("User with username %s not found",klijentUpdateDto.getOldUsername())));
        klijent = klijentMapper.klijentUpdateDtoToKlijent((Klijent) klijent, klijentUpdateDto);
        klijent = klijentRepository.save((Klijent) klijent);
        return klijentMapper.klijentToKlijentDto((Klijent) klijent);

    }


    @Override
    public KlijentDto setForbidenKlijent(String username, Boolean forbiden) {
        User klijent = klijentRepository.findKlijentByUsername(username).orElseThrow(()->new NotFoundException(String.format("User with username %s not found",username)));
        ((Klijent)klijent).setForbiden(forbiden);
        klijent = klijentRepository.save((Klijent) klijent);
        return klijentMapper.klijentToKlijentDto((Klijent) klijent);
    }
    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        //Try to find active user for specified credentials
        Klijent klijent = klijentRepository
                .findKlijentByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        //ubacimo if koje proverava da li je iskljucen, ako jeste salje neku poruku(error)
        if(klijent.isForbiden()){
            throw new ForbiddenUserException("Client is forbidden");
        }
        Claims claims = Jwts.claims();
        claims.put("id", klijent.getId());
        claims.put("role", "Klijent");
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }

}
