package com.raf.cedaandreja.KorisnickiServis.controller;

import com.raf.cedaandreja.KorisnickiServis.dto.ManagerCreateDto;
import com.raf.cedaandreja.KorisnickiServis.dto.ManagerDto;
import com.raf.cedaandreja.KorisnickiServis.service.ManagerService;
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
    public ResponseEntity<List<ManagerDto>> getAll() {
        return new ResponseEntity<>(managerService.findAllManagers(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ManagerDto> getManager(@RequestParam String ime, @RequestParam String prezime) {
        return new ResponseEntity<>(managerService.findManager(ime, prezime), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ManagerDto> saveManager(@RequestBody ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(managerService.addManager(managerCreateDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ManagerDto> updateManager(@RequestBody ManagerDto managerDto) {
        return new ResponseEntity<>(managerService.updateManager(managerDto), HttpStatus.OK);
    }
}
