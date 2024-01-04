package com.raf.cedaandreja.ZakazivanjeServis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raf.cedaandreja.ZakazivanjeServis.domain.FiskulturnaSala;
import com.raf.cedaandreja.ZakazivanjeServis.domain.TipTreninga;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TerminDto {

    private Long id;
    @JsonProperty("fiskulturnaSala")
    private FiskulturnaSalaDto fiskulturnaSalaDto;
    private TipTreninga tipTreninga;
    private LocalDate datum;
    private LocalTime vremeOd;
    private LocalTime vremeDo;
    private boolean zauzet;
    private int maxBrojOsoba;

    private int trenutanBrojOsoba;

    private String dan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FiskulturnaSalaDto getFiskulturnaSalaDto() {
        return fiskulturnaSalaDto;
    }

    public void setFiskulturnaSalaDto(FiskulturnaSalaDto fiskulturnaSalaDto) {
        this.fiskulturnaSalaDto = fiskulturnaSalaDto;
    }

    public TipTreninga getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(TipTreninga tipTreninga) {
        this.tipTreninga = tipTreninga;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getVremeOd() {
        return vremeOd;
    }

    public void setVremeOd(LocalTime vremeOd) {
        this.vremeOd = vremeOd;
    }

    public LocalTime getVremeDo() {
        return vremeDo;
    }

    public void setVremeDo(LocalTime vremeDo) {
        this.vremeDo = vremeDo;
    }

    public boolean isZauzet() {
        return zauzet;
    }

    public void setZauzet(boolean zauzet) {
        this.zauzet = zauzet;
    }

    public int getMaxBrojOsoba() {
        return maxBrojOsoba;
    }

    public void setMaxBrojOsoba(int maxBrojOsoba) {
        this.maxBrojOsoba = maxBrojOsoba;
    }

    public int getTrenutanBrojOsoba() {
        return trenutanBrojOsoba;
    }

    public void setTrenutanBrojOsoba(int trenutanBrojOsoba) {
        this.trenutanBrojOsoba = trenutanBrojOsoba;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }
}
