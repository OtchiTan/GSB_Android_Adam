package com.gsb.modele;

public class Echantillon {

    private String code;
    private String libelle;
    private String quantite;

    public Echantillon(String code, String libelle, String quantite) {
        this.code = code;
        this.libelle = libelle;
        this.quantite = quantite;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }
}
