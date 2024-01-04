package com.raf.cedaandreja.ZakazivanjeServis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raf.cedaandreja.ZakazivanjeServis.domain.TipTreninga;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TerminCreateDto {

    private Long fiskulturnaSalaId;
    private Long tipTreningaId;
    private LocalDate datum;
    private LocalTime vremeOd;
    private LocalTime vremeDo;
    private int maxBrojOsoba;

    private String tip;

    public Long getFiskulturnaSalaId() {
        return fiskulturnaSalaId;
    }

    public void setFiskulturnaSalaId(Long fiskulturnaSalaId) {
        this.fiskulturnaSalaId = fiskulturnaSalaId;
    }

    public Long getTipTreningaId() {
        return tipTreningaId;
    }

    public void setTipTreningaId(Long tipTreningaId) {
        this.tipTreningaId = tipTreningaId;
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

    public int getMaxBrojOsoba() {
        return maxBrojOsoba;
    }

    public void setMaxBrojOsoba(int maxBrojOsoba) {
        this.maxBrojOsoba = maxBrojOsoba;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
