package com.raf.cedaandreja.ZakazivanjeServis.controller;

import com.raf.cedaandreja.ZakazivanjeServis.dto.FiskulturnaSalaDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.RezervacijaCreateDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.RezervacijaDto;
import com.raf.cedaandreja.ZakazivanjeServis.exception.NotFoundException;
import com.raf.cedaandreja.ZakazivanjeServis.security.CheckSecurity;
import com.raf.cedaandreja.ZakazivanjeServis.service.RezervacijaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {
    private RezervacijaService rezervacijaService;

    public RezervacijaController(RezervacijaService rezervacijaService) {
        this.rezervacijaService = rezervacijaService;
    }

    @PostMapping("/rezervisi")
    public ResponseEntity<RezervacijaDto> addRezervacija(@RequestBody RezervacijaCreateDto rezervacijaCreateDtoDto) {
        return new ResponseEntity<>(rezervacijaService.rezervisi(rezervacijaCreateDtoDto), HttpStatus.CREATED);
    }

    @PostMapping("/otkazimanager/{id}")
    public ResponseEntity<Void> otkaziRezervacijuManager(@PathVariable Long id) {
        try {
            rezervacijaService.otkaziManager(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/otkaziklijent/{id}")
    public ResponseEntity<Void> otkaziRezervacijuKlijent(@PathVariable Long id) {
        try {
            rezervacijaService.otkaziKlijent(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
