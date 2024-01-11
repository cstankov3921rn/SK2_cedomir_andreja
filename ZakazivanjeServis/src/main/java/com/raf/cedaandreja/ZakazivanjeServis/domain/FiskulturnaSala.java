package com.raf.cedaandreja.ZakazivanjeServis.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FiskulturnaSala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ime;
    private String opis;
    private String brojTrenera;

    private String besplatanTrening;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fiskulturnaSala", orphanRemoval = true)
    private List<TipTreninga> tipTreninga=new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getBrojTrenera() {
        return brojTrenera;
    }

    public void setBrojTrenera(String brojTrenera) {
        this.brojTrenera = brojTrenera;
    }

    public List<TipTreninga> getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(List<TipTreninga> tipTreninga) {
        this.tipTreninga = tipTreninga;
    }

    public String getBesplatanTrening() {
        return besplatanTrening;
    }

    public void setBesplatanTrening(String besplatanTrening) {
        this.besplatanTrening = besplatanTrening;
    }
}
