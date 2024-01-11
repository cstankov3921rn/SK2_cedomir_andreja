package com.raf.cedaandreja.ZakazivanjeServis.dto;

import com.raf.cedaandreja.ZakazivanjeServis.domain.TipTreninga;

import java.util.ArrayList;
import java.util.List;

public class FiskulturnaSalaUpdateDto {

    private Long id;
    private String staroIme;
    private String ime;
    private String opis;
    private String brojTrenera;

    private String besplatanTrening;

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

    public String getStaroIme() {
        return staroIme;
    }

    public void setStaroIme(String staroIme) {
        this.staroIme = staroIme;
    }

    public String getBesplatanTrening() {
        return besplatanTrening;
    }

    public void setBesplatanTrening(String besplatanTrening) {
        this.besplatanTrening = besplatanTrening;
    }
}
