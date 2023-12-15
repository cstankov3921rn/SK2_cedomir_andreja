package com.raf.cedaandreja.KorisnickiServis.service;

import com.raf.cedaandreja.KorisnickiServis.dto.AdminDto;

public interface AdminService {
    AdminDto findAdmin(String ime, String prezime);
}
