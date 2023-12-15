package com.raf.cedaandreja.KorisnickiServis.controller;

import com.raf.cedaandreja.KorisnickiServis.dto.KlijentDto;
import com.raf.cedaandreja.KorisnickiServis.service.KlijentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/klijent")
public class KlijentController {
    private KlijentService klijentService;

    public KlijentController(KlijentService klijentService){
        this.klijentService = klijentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<KlijentDto>> getAll() {
        return new ResponseEntity<>(klijentService.findAllKlijents(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<KlijentDto> getKlijent(@RequestParam String ime, @RequestParam String prezime) {
        return new ResponseEntity<>(klijentService.findKlijent(ime, prezime), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<KlijentDto> addKlijent(@RequestBody KlijentDto klijentDto) {
        return new ResponseEntity<>(klijentService.addKlijent(klijentDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<KlijentDto> updateKlijent(@RequestBody KlijentDto klijentDto) {
        return new ResponseEntity<>(klijentService.updateKlijent(klijentDto), HttpStatus.OK);
    }
}
