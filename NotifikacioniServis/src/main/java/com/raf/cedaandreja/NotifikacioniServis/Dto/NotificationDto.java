package com.raf.cedaandreja.NotifikacioniServis.Dto;

import java.util.Map;

public class NotificationDto {
    private String korisnik;
    private String type;
    private Map<String,String> extra;

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "koirisnik='" + korisnik + '\'' +
                ", type='" + type + '\'' +
                ", extra=" + extra +
                '}';
    }
}
