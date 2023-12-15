package com.raf.cedaandreja.KorisnickiServis.mapper;

import com.raf.cedaandreja.KorisnickiServis.domain.Klijent;
import com.raf.cedaandreja.KorisnickiServis.domain.Manager;
import com.raf.cedaandreja.KorisnickiServis.domain.User;
import com.raf.cedaandreja.KorisnickiServis.dto.KlijentDto;
import com.raf.cedaandreja.KorisnickiServis.dto.ManagerDto;
import org.springframework.stereotype.Component;

@Component
public class KlijentMapper {
    public KlijentDto klijentToKlijentDto(Klijent klijent){
        KlijentDto klijentDto = new KlijentDto();
        return klijentDto;
    }

    public Klijent klijentDtoToKlijent(KlijentDto klijentDto){
        User klijent = new Klijent();
        return (Klijent) klijent;
    }
}
