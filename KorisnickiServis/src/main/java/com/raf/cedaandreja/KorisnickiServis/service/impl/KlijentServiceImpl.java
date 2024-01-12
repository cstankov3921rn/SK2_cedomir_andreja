package com.raf.cedaandreja.KorisnickiServis.service.impl;

import com.raf.cedaandreja.KorisnickiServis.notification.NotificationApi;
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

import java.util.Optional;

@Service
public class KlijentServiceImpl implements KlijentService {
    private KlijentRepository klijentRepository;
    private KlijentMapper klijentMapper;
    private TokenService tokenService;
    private NotificationApi notificationApi;

    public KlijentServiceImpl(KlijentRepository klijentRepository, KlijentMapper klijentMapper, TokenService tokenService, NotificationApi notificationApi) {
        this.klijentRepository = klijentRepository;
        this.klijentMapper = klijentMapper;
        this.tokenService = tokenService;
        this.notificationApi = notificationApi;
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
        Claims claims = Jwts.claims();
        claims.put("id", ((Klijent)klijent).getId());
        claims.put("role", "Klijent");

        TokenResponseDto tr = new TokenResponseDto(tokenService.generate(claims));
        NotificationDto nt = new NotificationDto();
        nt.setKorisnik(klijent.getEmail());
        nt.setType("activation");
        nt.setLink(tr.getToken());
        notificationApi.sendNotification(nt);

        return klijentMapper.klijentToKlijentDto((Klijent) klijent);

    }

    @Override
    public KlijentDto updateKlijent(KlijentUpdateDto klijentUpdateDto) {
        User klijent = klijentRepository.findKlijentByUsername(klijentUpdateDto.getOldUsername()).orElseThrow(()->new NotFoundException(String.format("User with username %s not found",klijentUpdateDto.getOldUsername())));
        klijent = klijentMapper.klijentUpdateDtoToKlijent((Klijent) klijent, klijentUpdateDto);
        klijent = klijentRepository.save((Klijent) klijent);

        Claims claims = Jwts.claims();
        claims.put("id", ((Klijent)klijent).getId());
        claims.put("role", "Klijent");

        TokenResponseDto tr = new TokenResponseDto(tokenService.generate(claims));
        NotificationDto nt = new NotificationDto();
        nt.setKorisnik(klijent.getEmail());
        nt.setType("update");
        nt.setLink(tr.getToken());
        notificationApi.sendNotification(nt);

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
    public void povecajBrojZakazanihTermina(Long klijentId) {
        User klijent = klijentRepository.findById(klijentId).orElseThrow(()->new NotFoundException(String.format("User with given id not found")));
        ((Klijent)klijent).setBrojZakazanihTreninga(((Klijent)klijent).getBrojZakazanihTreninga()+1);
        klijent = klijentRepository.save((Klijent) klijent);
    }

    @Override
    public void smanjiBrojZakazanihTermina(Long klijentId) {
        User klijent = klijentRepository.findById(klijentId).orElseThrow(()->new NotFoundException(String.format("User with given id not found")));
        ((Klijent)klijent).setBrojZakazanihTreninga(((Klijent)klijent).getBrojZakazanihTreninga()-1);
        klijent = klijentRepository.save((Klijent) klijent);
    }

    @Override
    public int getBrojZakazanihTermina(Long klijentId) {
        User klijent = klijentRepository.findById(klijentId).orElseThrow(()->new NotFoundException(String.format("User with given id not found")));
        return ((Klijent)klijent).getBrojZakazanihTreninga();
    }

    @Override
    public KlijentDto findKlijentId(Long klijentId) {
        User klijent = klijentRepository.findById(klijentId).get();
        return klijentMapper.klijentToKlijentDto((Klijent)klijent);
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


        TokenResponseDto tr = new TokenResponseDto(tokenService.generate(claims));
        NotificationDto nt = new NotificationDto();
        nt.setKorisnik(klijent.getEmail());
        nt.setType("login");
        nt.setLink(tr.getToken());
        notificationApi.sendNotification(nt);

        return tr;
    }
    @Override
    public KlijentDto updatePasswordKlijent(UpdatePaswordDto updatePaswordDto) {
        Klijent klijent = klijentRepository
                .findKlijentByUsernameAndPassword(updatePaswordDto.getUsername(), updatePaswordDto.getOldPass())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", updatePaswordDto.getUsername(),
                                updatePaswordDto.getOldPass())));
        //Create token payload
        if(klijent.isForbiden()){
            throw new ForbiddenUserException("Klijent is forbidden");
        }
        Claims claims = Jwts.claims();
        claims.put("id", klijent.getId());
        claims.put("role", "Klijent");

        TokenResponseDto tr = new TokenResponseDto(tokenService.generate(claims));
        NotificationDto nt = new NotificationDto();


        nt.setKorisnik(klijent.getEmail());
        nt.setType("passwordChange");
        nt.setLink(tr.getToken());
        notificationApi.sendNotification(nt);
        klijent.setPassword(updatePaswordDto.getNewPass());
        return klijentMapper.klijentToKlijentDto(klijent);
    }
}
