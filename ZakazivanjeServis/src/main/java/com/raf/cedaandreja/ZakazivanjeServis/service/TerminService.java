package com.raf.cedaandreja.ZakazivanjeServis.service;

import com.raf.cedaandreja.ZakazivanjeServis.dto.TerminCreateDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.TerminDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TerminService {
    Page<TerminDto> findAll(Pageable pageable);

    TerminDto findById(Long id);

    TerminDto add(TerminCreateDto projectionCreateDto);

    Page<TerminDto> findDan(String dan,Pageable pageable);

    void deleteById(Long id);

    Page<TerminDto> findTip(String individualni,Pageable pageable);

    Page<TerminDto> findAllSorted(Pageable pageable);
}
