package com.raf.cedaandreja.ZakazivanjeServis.controller;

import com.raf.cedaandreja.ZakazivanjeServis.dto.TerminCreateDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.TerminDto;
import com.raf.cedaandreja.ZakazivanjeServis.security.CheckSecurity;
import com.raf.cedaandreja.ZakazivanjeServis.service.TerminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/termin")
public class TerminController {

    private TerminService terminService;

    public TerminController(TerminService terminService) {
        this.terminService = terminService;
    }

    @GetMapping("/all")
    @CheckSecurity(roles={"Klijent","Manager"})
    public ResponseEntity<Page<TerminDto>> findAll(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(terminService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/sorted")
    @CheckSecurity(roles={"Klijent","Manager"})
    public ResponseEntity<Page<TerminDto>> findAllSorted(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(terminService.findAllSorted(pageable), HttpStatus.OK);
    }

    @GetMapping("/dan")
    @CheckSecurity(roles={"Klijent","Manager"})
    public ResponseEntity<Page<TerminDto>> findPoDanu(@RequestHeader("Authorization") String authorization, @RequestParam String dan,Pageable pageable) {
        return new ResponseEntity<>(terminService.findDan(dan,pageable), HttpStatus.OK);
    }

    @GetMapping("/tip")
    @CheckSecurity(roles={"Klijent","Manager"})
    public ResponseEntity<Page<TerminDto>> findPoTipu(@RequestHeader("Authorization") String authorization, @RequestParam String tip,Pageable pageable) {
        return new ResponseEntity<>(terminService.findTip(tip,pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<TerminDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(terminService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    @CheckSecurity(roles={"Manager"})
    public ResponseEntity<TerminDto> add(@RequestHeader("Authorization") String authorization, @RequestBody TerminCreateDto terminCreateDto) {
        return new ResponseEntity<>(terminService.add(terminCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        terminService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
