package com.raf.cedaandreja.KorisnickiServis.mapper;

import com.raf.cedaandreja.KorisnickiServis.domain.Manager;
import com.raf.cedaandreja.KorisnickiServis.domain.User;
import com.raf.cedaandreja.KorisnickiServis.dto.ManagerCreateDto;
import com.raf.cedaandreja.KorisnickiServis.dto.ManagerDto;
import com.raf.cedaandreja.KorisnickiServis.dto.ManagerUpdateDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ManagerMapper {

    public ManagerDto managerToManagerDto(Manager manager){

        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setEmail(manager.getEmail());
        managerDto.setIme(manager.getIme());
        managerDto.setPrezime(manager.getPrezime());
        managerDto.setUsername(manager.getUsername());
        managerDto.setNazivFiskulturneSale(manager.getNazivFiskulturneSale());
        return managerDto;
    }

    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto){
        User manager = new Manager();
        manager.setDatumRodjenja(managerCreateDto.getDatumRodjenja());
        manager.setEmail(managerCreateDto.getEmail());
        manager.setIme(managerCreateDto.getIme());
        manager.setPrezime(managerCreateDto.getPrezime());
        manager.setUsername(managerCreateDto.getUsername());
        manager.setPassword(managerCreateDto.getPassword());
        ((Manager)manager).setDatumZaposljavanja(LocalDate.now());
        ((Manager)manager).setNazivFiskulturneSale(managerCreateDto.getNazivFiskulturneSale());
        return (Manager) manager;
    }

    public Manager managerUpdateDtoToManager(Manager manager, ManagerUpdateDto managerUpdateDto){
        if(managerUpdateDto.getEmail()!=null)
            manager.setEmail(managerUpdateDto.getEmail());
        if(managerUpdateDto.getIme()!=null)
            manager.setIme(managerUpdateDto.getIme());
        if(managerUpdateDto.getPrezime()!=null)
            manager.setPrezime(managerUpdateDto.getPrezime());
        if(managerUpdateDto.getUsername()!=null)
            manager.setUsername(managerUpdateDto.getUsername());
        if(managerUpdateDto.getPassword()!=null)
            manager.setPassword(managerUpdateDto.getPassword());
        if(managerUpdateDto.getDatumRodjenja()!=null)
            manager.setDatumRodjenja(managerUpdateDto.getDatumRodjenja());
        if(managerUpdateDto.getNazivFiskulturneSale()!=null)
            manager.setNazivFiskulturneSale(managerUpdateDto.getNazivFiskulturneSale());
        return manager;
    }
}
