package com.raf.cedaandreja.KorisnickiServis.controller;

import com.raf.cedaandreja.KorisnickiServis.domain.Manager;
import com.raf.cedaandreja.KorisnickiServis.dto.*;
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

    @GetMapping("/{managerId}")
    public ResponseEntity<ManagerDto> getManagerId(@PathVariable Long managerId) {
        return new ResponseEntity<>(managerService.findManagerId(managerId), HttpStatus.OK);
    }

    @GetMapping("/manager")
    @CheckSecurity(roles={"Manager","Admin"})
    public ResponseEntity<ManagerDto> getManager(@RequestHeader("Authorization") String authorization, @RequestParam String username) {
        return new ResponseEntity<>(managerService.findManagerPoUsername(username), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ManagerDto> saveManager(@RequestBody ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(managerService.addManager(managerCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginManager(@RequestBody TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(managerService.login(tokenRequestDto), HttpStatus.OK);
    }

    @PutMapping("/update")
    @CheckSecurity(roles={"Admin","Manager"})
    public ResponseEntity<ManagerDto> updateManager(@RequestHeader("Authorization") String authorization, @RequestBody ManagerUpdateDto managerUpdateDto) {
        return new ResponseEntity<>(managerService.updateManager(managerUpdateDto), HttpStatus.OK);
    }

    @PutMapping("/updatepasword")
    @CheckSecurity(roles={"Admin","Manager"})
    public ResponseEntity<ManagerDto> updatePaswordManager(@RequestHeader("Authorization") String authorization, @RequestBody UpdatePaswordDto updatePaswordDto) {
        return new ResponseEntity<>(managerService.updatePasswordMagager(updatePaswordDto), HttpStatus.OK);
    }

    @PutMapping("/forbiden")
    @CheckSecurity(roles={"Admin"})
    public ResponseEntity<ManagerDto> setForbidenManager(@RequestHeader("Authorization") String authorization, @RequestBody ForbidenTokenDto forbidenTokenDto) {
        Boolean x;
        if(forbidenTokenDto.getForbiden().matches("true"))x = true;
        else x = false;
        return new ResponseEntity<>(managerService.setForbidenManager(forbidenTokenDto.getUsername(), x), HttpStatus.OK);
    }
}
