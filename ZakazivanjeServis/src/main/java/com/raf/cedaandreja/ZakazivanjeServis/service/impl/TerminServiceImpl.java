package com.raf.cedaandreja.ZakazivanjeServis.service.impl;

import com.raf.cedaandreja.ZakazivanjeServis.domain.Termin;
import com.raf.cedaandreja.ZakazivanjeServis.dto.TerminCreateDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.TerminDto;
import com.raf.cedaandreja.ZakazivanjeServis.exception.NotFoundException;
import com.raf.cedaandreja.ZakazivanjeServis.mapper.TerminMapper;
import com.raf.cedaandreja.ZakazivanjeServis.repository.TerminRepository;
import com.raf.cedaandreja.ZakazivanjeServis.service.TerminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TerminServiceImpl implements TerminService {

    private TerminRepository terminRepository;
    private TerminMapper terminMapper;

    public TerminServiceImpl(TerminRepository terminRepository, TerminMapper terminMapper) {
        this.terminRepository = terminRepository;
        this.terminMapper = terminMapper;
    }

    @Override
    public Page<TerminDto> findAll(Pageable pageable) {
        return terminRepository.findAll(pageable)
                .map(terminMapper::terminToTerminDto);
    }

    @Override
    public TerminDto findById(Long id) {
        return terminRepository.findById(id)
                .map(terminMapper::terminToTerminDto)
                .orElseThrow(() -> new NotFoundException(String.format("Termin with id: %d does not exists.", id)));
    }

    @Override
    public TerminDto add(TerminCreateDto terminCreateDto) {
        Termin termin = terminMapper.terminCreateDtoToTermin(terminCreateDto);
        terminRepository.save(termin);
        return terminMapper.terminToTerminDto(termin);
    }

    @Override
    public TerminDto findDan(String dan) {
        return terminRepository.findTerminByDan(dan).map(terminMapper::terminToTerminDto).orElseThrow(()-> new NotFoundException(String.format("Nema termina tog dana")));
    }

    @Override
    public void deleteById(Long id) {
        terminRepository.deleteById(id);
    }

    @Override
    public TerminDto findTip(String individualni) {
        return terminRepository.findTerminByTip(individualni).map(terminMapper::terminToTerminDto).orElseThrow(()-> new NotFoundException(String.format("Nema tog tipa")));
    }

    @Override
    public Page<TerminDto> findAllSorted(Pageable pageable) {
        return terminRepository.findAllByOrderByVremeOd(pageable)
                .map(terminMapper::terminToTerminDto);
    }
}
