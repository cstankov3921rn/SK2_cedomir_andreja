package com.raf.cedaandreja.KorisnickiServis.controller;

import com.raf.cedaandreja.KorisnickiServis.domain.Manager;
import com.raf.cedaandreja.KorisnickiServis.dto.ManagerCreateDto;
import com.raf.cedaandreja.KorisnickiServis.dto.ManagerDto;
import com.raf.cedaandreja.KorisnickiServis.dto.TokenRequestDto;
import com.raf.cedaandreja.KorisnickiServis.dto.TokenResponseDto;
import com.raf.cedaandreja.KorisnickiServis.security.CheckSecurity;
import com.raf.cedaandreja.KorisnickiServis.service.ManagerService;
import org.hibernate.annotations.Check;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private ManagerService managerService;

    public ManagerController(ManagerService managerService){
        this.managerService = managerService;
    }

    @GetMapping("/all")
    @CheckSecurity(roles={"Admin","Manager"})
    public ResponseEntity<Page<ManagerDto>> getAll(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(managerService.findAllManagers(pageable), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ManagerDto> getManager(@RequestParam String ime, @RequestParam String prezime) {
        return new ResponseEntity<>(managerService.findManager(ime, prezime), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ManagerDto> saveManager(@RequestBody ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(managerService.addManager(managerCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginManager(@RequestBody TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(managerService.login(tokenRequestDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ManagerDto> updateManager(@RequestBody ManagerDto managerDto) {
        return new ResponseEntity<>(managerService.updateManager(managerDto), HttpStatus.OK);
    }
}
