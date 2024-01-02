package com.raf.cedaandreja.ZakazivanjeServis.service.impl;

import com.raf.cedaandreja.ZakazivanjeServis.domain.FiskulturnaSala;
import com.raf.cedaandreja.ZakazivanjeServis.dto.FiskulturnaSalaDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.FiskulturnaSalaUpdateDto;
import com.raf.cedaandreja.ZakazivanjeServis.exception.NotFoundException;
import com.raf.cedaandreja.ZakazivanjeServis.mapper.FiskulturnaSalaMapper;
import com.raf.cedaandreja.ZakazivanjeServis.repository.FiskulturnaSalaRepository;
import com.raf.cedaandreja.ZakazivanjeServis.security.service.TokenService;
import com.raf.cedaandreja.ZakazivanjeServis.service.FiskulturnaSalaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FiskulturnaSalaImpl implements FiskulturnaSalaService {

    private FiskulturnaSalaRepository fiskulturnaSalaRepository;
    private FiskulturnaSalaMapper fiskulturnaSalaMapper;
    private TokenService tokenService;

    public FiskulturnaSalaImpl(FiskulturnaSalaRepository fiskulturnaSalaRepository, FiskulturnaSalaMapper fiskulturnaSalaMapper, TokenService tokenService) {
        this.fiskulturnaSalaRepository = fiskulturnaSalaRepository;
        this.fiskulturnaSalaMapper = fiskulturnaSalaMapper;
        this.tokenService = tokenService;
    }

    @Override
    public Page<FiskulturnaSalaDto> findAllSale(Pageable pageable) {
        return null;
    }

    @Override
    public FiskulturnaSalaDto findSala(String ime) {
        return null;
    }

    @Override
    public FiskulturnaSalaDto addSala(FiskulturnaSalaDto fiskulturnaSalaDto) {
        FiskulturnaSala fiskulturnaSala = fiskulturnaSalaMapper.fiskulturnaSalaDtoToFiskulturnaSala(fiskulturnaSalaDto);
        fiskulturnaSalaRepository.save(fiskulturnaSala);
        return fiskulturnaSalaMapper.fiskulturnaSalaToFiskulturnaSalaDto(fiskulturnaSala);
    }

    @Override
    public FiskulturnaSalaDto updateSala(FiskulturnaSalaUpdateDto fiskulturnaSalaUpdateDto) {
        FiskulturnaSala fiskulturnaSala = fiskulturnaSalaRepository.findFiskulturnaSalaByIme(fiskulturnaSalaUpdateDto.getStaroIme()).orElseThrow(()->new NotFoundException(String.format("User with username %s not found",fiskulturnaSalaUpdateDto.getStaroIme())));
        fiskulturnaSala = fiskulturnaSalaMapper.fiskulturnaSalaUpdateDtoToFiskulturnaSala(fiskulturnaSala, fiskulturnaSalaUpdateDto);
        fiskulturnaSala = fiskulturnaSalaRepository.save(fiskulturnaSala);
        return fiskulturnaSalaMapper.fiskulturnaSalaToFiskulturnaSalaDto(fiskulturnaSala);
    }
}
