package com.raf.cedaandreja.ZakazivanjeServis.mapper;

import com.raf.cedaandreja.ZakazivanjeServis.domain.Termin;
import com.raf.cedaandreja.ZakazivanjeServis.domain.TipTreninga;
import com.raf.cedaandreja.ZakazivanjeServis.dto.TerminCreateDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.TerminDto;
import com.raf.cedaandreja.ZakazivanjeServis.exception.NotFoundException;
import com.raf.cedaandreja.ZakazivanjeServis.repository.FiskulturnaSalaRepository;
import com.raf.cedaandreja.ZakazivanjeServis.repository.TipTreningaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TerminMapper {
    private FiskulturnaSalaRepository fiskulturnaSalaRepository;
    private TipTreningaRepository tipTreningaRepository;
    private FiskulturnaSalaMapper fiskulturnaSalaMapper;

    public TerminMapper(FiskulturnaSalaRepository fiskulturnaSalaRepository, TipTreningaRepository tipTreningaRepository, FiskulturnaSalaMapper fiskulturnaSalaMapper) {
        this.fiskulturnaSalaRepository = fiskulturnaSalaRepository;
        this.tipTreningaRepository = tipTreningaRepository;
        this.fiskulturnaSalaMapper = fiskulturnaSalaMapper;
    }

    public TerminDto terminToTerminDto(Termin termin){
        TerminDto terminDto = new TerminDto();
        terminDto.setId(termin.getId());
        terminDto.setTipTreninga(termin.getTipTreninga());
        terminDto.setFiskulturnaSalaDto(fiskulturnaSalaMapper.fiskulturnaSalaToFiskulturnaSalaDto(termin.getFiskulturnaSala()));
        terminDto.setDatum(termin.getDatum());
        terminDto.setVremeOd(termin.getVremeOd());
        terminDto.setVremeDo(termin.getVremeDo());
        terminDto.setZauzet(termin.isZauzet());
        terminDto.setMaxBrojOsoba(termin.getMaxBrojOsoba());
        return terminDto;
    }

    public Termin terminCreateDtoToTermin(TerminCreateDto terminCreateDto){
        Termin termin = new Termin();
        termin.setTipTreninga(tipTreningaRepository.findById(terminCreateDto.getTipTreningaId())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Movie with id: %d does not exists.", terminCreateDto.getTipTreningaId()))));
        termin.setFiskulturnaSala(fiskulturnaSalaRepository.findById(terminCreateDto.getFiskulturnaSalaId())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Screen with id: %d does not exists.", terminCreateDto.getFiskulturnaSalaId()))));
        termin.setDatum(terminCreateDto.getDatum());
        termin.setVremeOd(terminCreateDto.getVremeOd());
        termin.setVremeDo(terminCreateDto.getVremeDo());
        termin.setMaxBrojOsoba(terminCreateDto.getMaxBrojOsoba());
        termin.setTrenutanBrojOsoba(0);
        termin.setDan(terminCreateDto.getDatum().getDayOfWeek().name());
        Optional<TipTreninga> tipTreninga = tipTreningaRepository.findById(terminCreateDto.getTipTreningaId());
        termin.setTip(tipTreninga.get().getTip());
        return termin;
    }
}
