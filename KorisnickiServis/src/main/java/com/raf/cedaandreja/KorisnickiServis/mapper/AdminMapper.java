package com.raf.cedaandreja.KorisnickiServis.mapper;

import com.raf.cedaandreja.KorisnickiServis.domain.Admin;
import com.raf.cedaandreja.KorisnickiServis.domain.Klijent;
import com.raf.cedaandreja.KorisnickiServis.domain.User;
import com.raf.cedaandreja.KorisnickiServis.dto.AdminDto;
import com.raf.cedaandreja.KorisnickiServis.dto.KlijentDto;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    public AdminDto adminToAdminDto(Admin admin){
        AdminDto adminDto = new AdminDto();
        return adminDto;
    }

    public Admin adminDtoToAdmin(AdminDto adminDto){
        User admin = new Admin();
        return (Admin) admin;
    }
}
