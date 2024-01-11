package com.raf.cedaandreja.ZakazivanjeServis.userservice.dto;

public class KlijentDto {
    private Long id;
    private String username;
    private String email;
    private String ime;
    private String prezime;
    private int brojClanskeKarte;
    private int brojZakazanihTreninga;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getBrojClanskeKarte() {
        return brojClanskeKarte;
    }

    public void setBrojClanskeKarte(int brojClanskeKarte) {
        this.brojClanskeKarte = brojClanskeKarte;
    }

    public int getBrojZakazanihTreninga() {
        return brojZakazanihTreninga;
    }

    public void setBrojZakazanihTreninga(int brojZakazanihTreninga) {
        this.brojZakazanihTreninga = brojZakazanihTreninga;
    }
}
