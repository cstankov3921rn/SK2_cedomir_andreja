package com.raf.cedaandreja.ZakazivanjeServis.dto;

import com.fasterxml.jackson.databind.ObjectMapper;


public class NotificationDto {
    private String korisnik; // mail
    private String type; // jedan od onih 6 komada
    private String link; // dodatni podaci

    public NotificationDto() {
        // Dodajte inicijalizaciju ako je potrebno
    }
    public NotificationDto(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            NotificationDto notificationDto = objectMapper.readValue(json, NotificationDto.class);
            this.korisnik = notificationDto.getKorisnik();
            this.type = notificationDto.getType();
            this.link = notificationDto.getLink();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "koirisnik='" + korisnik + '\'' +
                ", type='" + type + '\'' +
                ", link=" + link +
                '}';
    }
}
