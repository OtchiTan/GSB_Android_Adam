package com.gsb.modele;

import java.util.ArrayList;

public class Echantillon {

    private String code;
    private String libelle;
    private String quantite;
    private ArrayList<Composant> composants;

    public Echantillon(String code, String libelle, String quantite) {
        this.code = code;
        this.libelle = libelle;
        this.quantite = quantite;
        this.composants = new ArrayList<>();
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

    public ArrayList<Composant> getComposants() {
        return composants;
    }

    public void setComposants(ArrayList<Composant> composants) {
        this.composants = composants;
    }
}
