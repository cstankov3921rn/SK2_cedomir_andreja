package com.raf.cedaandreja.ZakazivanjeServis.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime vremeOd;
    private LocalDateTime vremeDo;
    private List<String> listaKlijenta;
    private boolean zauzet;
    private int maxBrojOsoba;
    @ManyToOne
    private Long SalaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getVremeOd() {
        return vremeOd;
    }

    public void setVremeOd(LocalDateTime vremeOd) {
        this.vremeOd = vremeOd;
    }

    public LocalDateTime getVremeDo() {
        return vremeDo;
    }

    public void setVremeDo(LocalDateTime vremeDo) {
        this.vremeDo = vremeDo;
    }

    public List<String> getListaKlijenta() {
        return listaKlijenta;
    }

    public void setListaKlijenta(List<String> listaKlijenta) {
        this.listaKlijenta = listaKlijenta;
    }

    public boolean isZauzet() {
        return zauzet;
    }

    public void setZauzet(boolean zauzet) {
        this.zauzet = zauzet;
    }

    public Long getSalaId() {
        return SalaId;
    }

    public void setSalaId(Long salaId) {
        SalaId = salaId;
    }

    public int getMaxBrojOsoba() {
        return maxBrojOsoba;
    }

    public void setMaxBrojOsoba(int maxBrojOsoba) {
        this.maxBrojOsoba = maxBrojOsoba;
    }
}
