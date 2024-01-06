package com.raf.cedaandreja.ZakazivanjeServis.dto;

public class RezervacijaCreateDto {
    private Long userId;
    private Long terminId;

    private String cena;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTerminId() {
        return terminId;
    }

    public void setTerminId(Long terminId) {
        this.terminId = terminId;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }
}
