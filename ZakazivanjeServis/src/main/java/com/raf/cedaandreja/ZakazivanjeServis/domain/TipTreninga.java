package com.raf.cedaandreja.ZakazivanjeServis.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class TipTreninga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;
    private String cena;

    private String tip;
    @JsonIgnore
    @ManyToOne
    private FiskulturnaSala fiskulturnaSala;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public FiskulturnaSala getFiskulturnaSala() {
        return fiskulturnaSala;
    }

    public void setFiskulturnaSala(FiskulturnaSala fiskulturnaSala) {
        this.fiskulturnaSala = fiskulturnaSala;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
