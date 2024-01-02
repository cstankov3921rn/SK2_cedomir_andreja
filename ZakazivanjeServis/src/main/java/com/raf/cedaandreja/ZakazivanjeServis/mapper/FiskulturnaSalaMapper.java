package com.raf.cedaandreja.ZakazivanjeServis.mapper;

import com.raf.cedaandreja.ZakazivanjeServis.domain.FiskulturnaSala;
import com.raf.cedaandreja.ZakazivanjeServis.dto.FiskulturnaSalaDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.FiskulturnaSalaUpdateDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FiskulturnaSalaMapper {
    public FiskulturnaSalaDto fiskulturnaSalaToFiskulturnaSalaDto(FiskulturnaSala fiskulturnaSala){

        FiskulturnaSalaDto fiskulturnaSalaDto = new FiskulturnaSalaDto();
        fiskulturnaSalaDto.setId(fiskulturnaSala.getId());
        fiskulturnaSalaDto.setIme(fiskulturnaSala.getIme());
        fiskulturnaSalaDto.setCena(fiskulturnaSala.getCena());
        fiskulturnaSalaDto.setOpis(fiskulturnaSala.getOpis());
        fiskulturnaSalaDto.setBrojTrenera(fiskulturnaSala.getBrojTrenera());
        fiskulturnaSalaDto.setTipTreninga(fiskulturnaSala.getTipTreninga());
        return fiskulturnaSalaDto;
    }

    public FiskulturnaSala fiskulturnaSalaDtoToFiskulturnaSala(FiskulturnaSalaDto fiskulturnaSalaDto){
        FiskulturnaSala fiskulturnaSala = new FiskulturnaSala();
        fiskulturnaSala.setId(fiskulturnaSalaDto.getId());
        fiskulturnaSala.setIme(fiskulturnaSalaDto.getIme());
        fiskulturnaSala.setCena(fiskulturnaSalaDto.getCena());
        fiskulturnaSala.setOpis(fiskulturnaSalaDto.getOpis());
        fiskulturnaSala.setBrojTrenera(fiskulturnaSalaDto.getBrojTrenera());
        fiskulturnaSala.setTipTreninga(fiskulturnaSalaDto.getTipTreninga());
        return fiskulturnaSala;
    }

    public FiskulturnaSala fiskulturnaSalaUpdateDtoToFiskulturnaSala(FiskulturnaSala fiskulturnaSala, FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto){
        if(fiskulturnaSalaUpdateDto.getBrojTrenera()!=null)
            fiskulturnaSala.setBrojTrenera(fiskulturnaSalaUpdateDto.getBrojTrenera());
        if(fiskulturnaSalaUpdateDto.getIme()!=null)
            fiskulturnaSala.setIme(fiskulturnaSalaUpdateDto.getIme());
        if(fiskulturnaSalaUpdateDto.getCena()!=null)
            fiskulturnaSala.setCena(fiskulturnaSalaUpdateDto.getCena());
        if(fiskulturnaSalaUpdateDto.getOpis()!=null)
            fiskulturnaSala.setOpis(fiskulturnaSalaUpdateDto.getOpis());
        if(fiskulturnaSalaUpdateDto.getTipTreninga()!=null)
            fiskulturnaSala.setTipTreninga(fiskulturnaSalaUpdateDto.getTipTreninga());
        return fiskulturnaSala;
    }

}
