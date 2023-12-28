package com.raf.cedaandreja.KorisnickiServis.service;

import com.raf.cedaandreja.KorisnickiServis.dto.AdminDto;
import com.raf.cedaandreja.KorisnickiServis.dto.TokenRequestDto;
import com.raf.cedaandreja.KorisnickiServis.dto.TokenResponseDto;

public interface AdminService {
    AdminDto findAdmin(String ime, String prezime);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
