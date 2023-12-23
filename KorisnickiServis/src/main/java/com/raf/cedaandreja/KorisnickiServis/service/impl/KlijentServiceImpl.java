package com.raf.cedaandreja.KorisnickiServis.service.impl;

import com.raf.cedaandreja.KorisnickiServis.domain.Klijent;
import com.raf.cedaandreja.KorisnickiServis.domain.Manager;
import com.raf.cedaandreja.KorisnickiServis.domain.User;
import com.raf.cedaandreja.KorisnickiServis.dto.KlijentCreateDto;
import com.raf.cedaandreja.KorisnickiServis.dto.KlijentDto;
import com.raf.cedaandreja.KorisnickiServis.dto.TokenRequestDto;
import com.raf.cedaandreja.KorisnickiServis.dto.TokenResponseDto;
import com.raf.cedaandreja.KorisnickiServis.exception.NotFoundException;
import com.raf.cedaandreja.KorisnickiServis.mapper.KlijentMapper;
import com.raf.cedaandreja.KorisnickiServis.repository.KlijentRepository;
import com.raf.cedaandreja.KorisnickiServis.repository.ManagerRepository;
import com.raf.cedaandreja.KorisnickiServis.security.service.TokenService;
import com.raf.cedaandreja.KorisnickiServis.service.KlijentService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public KlijentDto updateKlijent(KlijentDto klijentDto) {
        return null;
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
        Claims claims = Jwts.claims();
        claims.put("id", klijent.getId());
        claims.put("role", klijent.getIme());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
    }
}
