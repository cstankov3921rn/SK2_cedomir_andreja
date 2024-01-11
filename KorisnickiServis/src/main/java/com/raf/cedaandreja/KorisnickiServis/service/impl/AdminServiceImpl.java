package com.raf.cedaandreja.KorisnickiServis.service.impl;

import com.raf.cedaandreja.KorisnickiServis.notification.NotificationApi;
import com.raf.cedaandreja.KorisnickiServis.domain.Admin;
import com.raf.cedaandreja.KorisnickiServis.dto.AdminDto;
import com.raf.cedaandreja.KorisnickiServis.dto.NotificationDto;
import com.raf.cedaandreja.KorisnickiServis.dto.TokenRequestDto;
import com.raf.cedaandreja.KorisnickiServis.dto.TokenResponseDto;
import com.raf.cedaandreja.KorisnickiServis.exception.NotFoundException;
import com.raf.cedaandreja.KorisnickiServis.repository.AdminRepository;
import com.raf.cedaandreja.KorisnickiServis.security.service.TokenService;
import com.raf.cedaandreja.KorisnickiServis.service.AdminService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private TokenService tokenService;
    private NotificationApi notificationApi;
    //mapper

    public AdminServiceImpl(AdminRepository adminRepository, TokenService tokenService, NotificationApi notificationApi) {
        this.adminRepository = adminRepository;
        this.tokenService = tokenService;
        this.notificationApi = notificationApi;
    }

    @Override
    public AdminDto findAdmin(String ime, String prezime) {
        return null;
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {

        //Try to find active user for specified credentials
        Admin admin = adminRepository
                .findAdminByUsernameAndPassword(tokenRequestDto.getUsername(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        //ubacimo if koje proverava da li je iskljucen, ako jeste salje neku poruku(error)
        Claims claims = Jwts.claims();
        claims.put("id", admin.getId());
        claims.put("role", "Admin");
        //Generate token
        TokenResponseDto tr = new TokenResponseDto(tokenService.generate(claims));
        NotificationDto nt = new NotificationDto();
        nt.setKorisnik(admin.getEmail());
        nt.setType("login");
        nt.setLink(tr.getToken());
        notificationApi.sendNotification(nt);
        return tr;
    }
}
