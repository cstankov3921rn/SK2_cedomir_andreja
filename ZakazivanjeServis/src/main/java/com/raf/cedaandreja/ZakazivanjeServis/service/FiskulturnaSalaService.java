package com.raf.cedaandreja.ZakazivanjeServis.service;

import com.raf.cedaandreja.ZakazivanjeServis.dto.FiskulturnaSalaDto;
import com.raf.cedaandreja.ZakazivanjeServis.dto.FiskulturnaSalaUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FiskulturnaSalaService {
    Page<FiskulturnaSalaDto> findAllSale(Pageable pageable);

    FiskulturnaSalaDto findSala(String ime);

    FiskulturnaSalaDto addSala(FiskulturnaSalaDto fiskulturnaSalaDto);

    //FiskulturnaSalaDto addManager(ManagerCreateDto managerDto);

    FiskulturnaSalaDto updateSala(FiskulturnaSalaUpdateDto fiskulturnaSalaDto);
}
