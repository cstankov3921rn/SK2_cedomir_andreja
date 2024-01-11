package com.raf.cedaandreja.ZakazivanjeServis.mapper;

import com.raf.cedaandreja.ZakazivanjeServis.domain.FiskulturnaSala;
import com.raf.cedaandreja.ZakazivanjeServis.domain.TipTreninga;
import com.raf.cedaandreja.ZakazivanjeServis.dto.FiskulturnaSalaDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.FiskulturnaSalaUpdateDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class FiskulturnaSalaMapper {
    public FiskulturnaSalaDto fiskulturnaSalaToFiskulturnaSalaDto(FiskulturnaSala fiskulturnaSala){

        FiskulturnaSalaDto fiskulturnaSalaDto = new FiskulturnaSalaDto();
        fiskulturnaSalaDto.setId(fiskulturnaSala.getId());
        fiskulturnaSalaDto.setIme(fiskulturnaSala.getIme());
        fiskulturnaSalaDto.setOpis(fiskulturnaSala.getOpis());
        fiskulturnaSalaDto.setBrojTrenera(fiskulturnaSala.getBrojTrenera());
        fiskulturnaSalaDto.setTipTreninga(fiskulturnaSalaDto.getTipTreninga());
        fiskulturnaSalaDto.setBesplatanTrening(fiskulturnaSalaDto.getBesplatanTrening());
        return fiskulturnaSalaDto;
    }

    public FiskulturnaSala fiskulturnaSalaDtoToFiskulturnaSala(FiskulturnaSalaDto fiskulturnaSalaDto){
        FiskulturnaSala fiskulturnaSala = new FiskulturnaSala();
        fiskulturnaSala.setId(fiskulturnaSalaDto.getId());
        fiskulturnaSala.setIme(fiskulturnaSalaDto.getIme());
        fiskulturnaSala.setOpis(fiskulturnaSalaDto.getOpis());
        fiskulturnaSala.setBrojTrenera(fiskulturnaSalaDto.getBrojTrenera());
        fiskulturnaSala.setTipTreninga(fiskulturnaSalaDto.getTipTreninga());
        fiskulturnaSala.setBesplatanTrening(fiskulturnaSalaDto.getBesplatanTrening());
        return fiskulturnaSala;
    }

    public FiskulturnaSala fiskulturnaSalaUpdateDtoToFiskulturnaSala(FiskulturnaSala fiskulturnaSala, FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto){
        if(fiskulturnaSalaUpdateDto.getBrojTrenera()!=null)
            fiskulturnaSala.setBrojTrenera(fiskulturnaSalaUpdateDto.getBrojTrenera());
        if(fiskulturnaSalaUpdateDto.getIme()!=null)
            fiskulturnaSala.setIme(fiskulturnaSalaUpdateDto.getIme());
        if(fiskulturnaSalaUpdateDto.getOpis()!=null)
            fiskulturnaSala.setOpis(fiskulturnaSalaUpdateDto.getOpis());
        if(fiskulturnaSalaUpdateDto.getBesplatanTrening()!=null)
            fiskulturnaSala.setBesplatanTrening(fiskulturnaSalaUpdateDto.getBesplatanTrening());
//        if (fiskulturnaSalaUpdateDto.getTipTreninga() != null) {
//            fiskulturnaSala.getTipTreninga().clear();
//            fiskulturnaSala.setTipTreninga(fiskulturnaSalaUpdateDto.getTipTreninga());
//        }
        return fiskulturnaSala;
    }

}
