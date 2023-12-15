package com.raf.cedaandreja.KorisnickiServis.service.impl;

import com.raf.cedaandreja.KorisnickiServis.dto.AdminDto;
import com.raf.cedaandreja.KorisnickiServis.repository.AdminRepository;
import com.raf.cedaandreja.KorisnickiServis.repository.KlijentRepository;
import com.raf.cedaandreja.KorisnickiServis.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    //mapper

    public AdminServiceImpl(AdminRepository adminRepository){
        this.adminRepository=adminRepository;
    }
    @Override
    public AdminDto findAdmin(String ime, String prezime) {
        return null;
    }
}
