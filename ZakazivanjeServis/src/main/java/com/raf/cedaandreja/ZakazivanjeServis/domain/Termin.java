package com.raf.cedaandreja.ZakazivanjeServis.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne
    private FiskulturnaSala fiskulturnaSala;
    @JsonIgnore
    @ManyToOne
    private TipTreninga tipTreninga;
    private LocalDate datum;
    private LocalTime vremeOd;
    private LocalTime vremeDo;

    private String dan;
    private boolean zauzet;
    private int maxBrojOsoba;

    private int trenutanBrojOsoba;

    private String tip;
//    @ManyToOne
//    private Long SalaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

//    public Long getSalaId() {
//        return SalaId;
//    }
//
//    public void setSalaId(Long salaId) {
//        SalaId = salaId;
//    }

    public int getMaxBrojOsoba() {
        return maxBrojOsoba;
    }

    public void setMaxBrojOsoba(int maxBrojOsoba) {
        this.maxBrojOsoba = maxBrojOsoba;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public FiskulturnaSala getFiskulturnaSala() {
        return fiskulturnaSala;
    }

    public void setFiskulturnaSala(FiskulturnaSala fiskulturnaSala) {
        this.fiskulturnaSala = fiskulturnaSala;
    }

    public TipTreninga getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(TipTreninga tipTreninga) {
        this.tipTreninga = tipTreninga;
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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
