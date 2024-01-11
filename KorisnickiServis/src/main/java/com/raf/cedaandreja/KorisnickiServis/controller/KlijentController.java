package com.raf.cedaandreja.KorisnickiServis.controller;


import com.raf.cedaandreja.KorisnickiServis.dto.*;
import com.raf.cedaandreja.KorisnickiServis.security.CheckSecurity;
import com.raf.cedaandreja.KorisnickiServis.service.KlijentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @CheckSecurity(roles={"Klijent","Admin"})
    public ResponseEntity<Page<KlijentDto>> getAll(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(klijentService.findAllKlijents(pageable), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<KlijentDto> getKlijent(@RequestParam String ime, @RequestParam String prezime) {
        return new ResponseEntity<>(klijentService.findKlijent(ime, prezime), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<KlijentDto> addKlijent(@RequestBody KlijentCreateDto klijentCreateDto) {
        return new ResponseEntity<>(klijentService.addKlijent(klijentCreateDto), HttpStatus.OK);
    }

    @PutMapping("/update")
    @CheckSecurity(roles={"Admin","Klijent"})
    public ResponseEntity<KlijentDto> updateKlijent(@RequestHeader("Authorization") String authorization, @RequestBody KlijentUpdateDto klijentUpdateDto) {
        return new ResponseEntity<>(klijentService.updateKlijent(klijentUpdateDto), HttpStatus.OK);
    }

    @PutMapping("/forbiden")
    @CheckSecurity(roles={"Admin"})
    public ResponseEntity<KlijentDto> setForbidenKlijent(@RequestHeader("Authorization") String authorization, @RequestBody ForbidenTokenDto forbidenTokenDto) {
        Boolean x;
        if(forbidenTokenDto.getForbiden().matches("true"))x = true;
        else x = false;
        return new ResponseEntity<>(klijentService.setForbidenKlijent(forbidenTokenDto.getUsername(), x), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<KlijentDto> saveKlijent(@RequestBody KlijentCreateDto klijentCreateDto) {
        return new ResponseEntity<>(klijentService.addKlijent(klijentCreateDto), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginKlijent(@RequestBody TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(klijentService.login(tokenRequestDto), HttpStatus.OK);
    }

    @PostMapping("/{klijentId}/povecajBrojZakazanihTermina")
    public ResponseEntity<Void> povecajBrojZakazanihTermina(@PathVariable Long klijentId) {
        klijentService.povecajBrojZakazanihTermina(klijentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{klijentId}/smanjiBrojZakazanihTermina")
    public ResponseEntity<Void> smanjiBrojZakazanihTermina(@PathVariable Long klijentId) {
        klijentService.smanjiBrojZakazanihTermina(klijentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{klijentId}/brojZakazanihTermina")
    public ResponseEntity<Integer> getBrojZakazanihTermina(@PathVariable Long klijentId) {
        int brojZakazanihTermina = klijentService.getBrojZakazanihTermina(klijentId);
        return ResponseEntity.ok(brojZakazanihTermina);
    }
}
