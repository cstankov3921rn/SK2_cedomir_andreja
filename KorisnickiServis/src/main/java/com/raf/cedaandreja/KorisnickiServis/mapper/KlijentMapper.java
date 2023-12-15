package com.raf.cedaandreja.KorisnickiServis.mapper;

import com.raf.cedaandreja.KorisnickiServis.domain.Klijent;
import com.raf.cedaandreja.KorisnickiServis.domain.Manager;
import com.raf.cedaandreja.KorisnickiServis.domain.User;
import com.raf.cedaandreja.KorisnickiServis.dto.KlijentCreateDto;
import com.raf.cedaandreja.KorisnickiServis.dto.KlijentDto;
import com.raf.cedaandreja.KorisnickiServis.dto.ManagerDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class KlijentMapper {
    public KlijentDto klijentToKlijentDto(Klijent klijent){
        KlijentDto klijentDto = new KlijentDto();
        klijentDto.setId(klijent.getId());
        klijentDto.setEmail(klijent.getEmail());
        klijentDto.setUsername(klijent.getUsername());
        klijentDto.setIme(klijent.getIme());
        klijentDto.setPrezime(klijent.getPrezime());
        klijentDto.setBrojClanskeKarte(klijent.getBrojClanskeKarte());
        klijentDto.setBrojZakazanihTreninga(klijent.getBrojZakazanihTreninga());
        return klijentDto;
    }

    public Klijent klijentCreateDtoToKlijent(KlijentCreateDto klijentCreateDto){
        User klijent = new Klijent();
        klijent.setDatumRodjenja(klijentCreateDto.getDatumRodjenja());
        klijent.setEmail(klijentCreateDto.getEmail());
        klijent.setIme(klijentCreateDto.getIme());
        klijent.setPrezime(klijentCreateDto.getPrezime());
        klijent.setUsername(klijentCreateDto.getUsername());
        klijent.setPassword(klijentCreateDto.getPassword());
        ((Klijent)klijent).setBrojClanskeKarte(klijentCreateDto.getBrojClanskeKarte());
        ((Klijent)klijent).setBrojZakazanihTreninga(0);
        return (Klijent) klijent;
    }
}
