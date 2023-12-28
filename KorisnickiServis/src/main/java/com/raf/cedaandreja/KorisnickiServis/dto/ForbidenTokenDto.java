package com.raf.cedaandreja.KorisnickiServis.dto;

public class ForbidenTokenDto {

    private String username;
    private String forbiden;

    public ForbidenTokenDto(String username, String forbiden) {
        this.username = username;
        this.forbiden = forbiden;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getForbiden() {
        return forbiden;
    }

    public void setForbiden(String forbiden) {
        this.forbiden = forbiden;
    }
}
