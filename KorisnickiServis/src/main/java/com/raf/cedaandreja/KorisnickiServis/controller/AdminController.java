package com.raf.cedaandreja.KorisnickiServis.controller;

import com.raf.cedaandreja.KorisnickiServis.dto.AdminDto;
import com.raf.cedaandreja.KorisnickiServis.dto.KlijentDto;
import com.raf.cedaandreja.KorisnickiServis.service.AdminService;
import com.raf.cedaandreja.KorisnickiServis.service.KlijentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/login")
    public ResponseEntity<AdminDto> getAdmin(@RequestParam String ime, @RequestParam String prezime) {
        return new ResponseEntity<>(adminService.findAdmin(ime, prezime), HttpStatus.OK);
    }
}
