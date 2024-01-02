package com.raf.cedaandreja.ZakazivanjeServis.dto;

public class FiskulturnaSalaDto {
    private Long id;
    private String ime;
    private String opis;
    private String brojTrenera;
    private String tipTreninga;
    private String cena;

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

    public String getTipTreninga() {
        return tipTreninga;
    }

    public void setTipTreninga(String tipTreninga) {
        this.tipTreninga = tipTreninga;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }
}
