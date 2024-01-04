package com.raf.cedaandreja.NotifikacioniServis.Dto;

import java.util.Map;

public class NotificationDto {
    private String koirisnik;
    private String type;
    private Map<String,String> extra;

    public String getKoirisnik() {
        return koirisnik;
    }

    public void setKoirisnik(String koirisnik) {
        this.koirisnik = koirisnik;
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
}
