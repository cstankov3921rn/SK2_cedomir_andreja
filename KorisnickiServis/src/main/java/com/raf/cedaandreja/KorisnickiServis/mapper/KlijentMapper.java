package com.raf.cedaandreja.KorisnickiServis.mapper;

import com.raf.cedaandreja.KorisnickiServis.domain.Klijent;
import com.raf.cedaandreja.KorisnickiServis.domain.Manager;
import com.raf.cedaandreja.KorisnickiServis.domain.User;
import com.raf.cedaandreja.KorisnickiServis.dto.*;
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


    public Klijent klijentUpdateDtoToKlijent(Klijent klijent, KlijentUpdateDto klijentUpdateDto){
        if(klijentUpdateDto.getEmail()!=null)
            klijent.setEmail(klijentUpdateDto.getEmail());
        if(klijentUpdateDto.getIme()!=null)
            klijent.setIme(klijentUpdateDto.getIme());
        if(klijentUpdateDto.getPrezime()!=null)
            klijent.setPrezime(klijentUpdateDto.getPrezime());
        if(klijentUpdateDto.getUsername()!=null)
            klijent.setUsername(klijentUpdateDto.getUsername());
        if(klijentUpdateDto.getPassword()!=null)
            klijent.setPassword(klijentUpdateDto.getPassword());
        if(klijentUpdateDto.getDatumRodjenja()!=null)
            klijent.setDatumRodjenja(klijentUpdateDto.getDatumRodjenja());
        if(Integer.toString(klijentUpdateDto.getBrojClanskeKarte())!=null)
            klijent.setBrojClanskeKarte(klijentUpdateDto.getBrojClanskeKarte());
        return klijent;
    }
}
