package com.raf.cedaandreja.ZakazivanjeServis.service;

import com.raf.cedaandreja.ZakazivanjeServis.dto.TerminCreateDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.TerminDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TerminService {
    Page<TerminDto> findAll(Pageable pageable);

    TerminDto findById(Long id);

    TerminDto add(TerminCreateDto projectionCreateDto);

    TerminDto findDan(String dan);

    void deleteById(Long id);

    TerminDto findTip(String individualni);

    Page<TerminDto> findAllSorted(Pageable pageable);
}
