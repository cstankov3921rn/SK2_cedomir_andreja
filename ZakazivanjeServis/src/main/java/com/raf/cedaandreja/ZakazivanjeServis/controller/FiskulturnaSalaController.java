package com.raf.cedaandreja.ZakazivanjeServis.controller;

import com.raf.cedaandreja.ZakazivanjeServis.dto.FiskulturnaSalaDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.FiskulturnaSalaUpdateDto;
import com.raf.cedaandreja.ZakazivanjeServis.security.CheckSecurity;
import com.raf.cedaandreja.ZakazivanjeServis.service.FiskulturnaSalaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
public class FiskulturnaSalaController {
    private FiskulturnaSalaService fiskulturnaSalaService;

    public FiskulturnaSalaController(FiskulturnaSalaService fiskulturnaSalaService) {
        this.fiskulturnaSalaService = fiskulturnaSalaService;
    }

    @PostMapping("/add")
    @CheckSecurity(roles={"Manager"})
    public ResponseEntity<FiskulturnaSalaDto> saveSala(@RequestHeader("Authorization") String authorization, @RequestBody FiskulturnaSalaDto fiskulturnaSalaDto) {
        return new ResponseEntity<>(fiskulturnaSalaService.addSala(fiskulturnaSalaDto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @CheckSecurity(roles={"Manager"})
    public ResponseEntity<FiskulturnaSalaDto> updateSala(@RequestHeader("Authorization") String authorization, @RequestBody FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto) {
        return new ResponseEntity<>(fiskulturnaSalaService.updateSala(fiskulturnaSalaUpdateDto), HttpStatus.OK);
    }

    @GetMapping("/sale")
    @CheckSecurity(roles={"Manager"})
    public ResponseEntity<Page<FiskulturnaSalaDto>> getSaleOdManagera(@RequestHeader("Authorization") String authorization, @RequestParam String managerId,Pageable pageable) {
        return new ResponseEntity<>(fiskulturnaSalaService.findSaleOdManagera(managerId,pageable), HttpStatus.OK);
    }
}
