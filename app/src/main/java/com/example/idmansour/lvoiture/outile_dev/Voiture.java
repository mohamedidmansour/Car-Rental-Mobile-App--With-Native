package com.example.idmansour.lvoiture.outile_dev;

/**
 * Created by IDMANSOUR on 07/04/2018.
 */

public class Voiture {
    private String Immatricule;
    private String photo;
    private String nom;
    private String prix;
    private String note;
    private String personne_nb;
    private String etat_climatiseur;
    private String type_boit_vitesse;
    private String type_carburant;
    private String disponible;
    private String porte_nb;
    private String remise;
    private String nombre_options;

    public Voiture(String immatricule, String photo, String nom, String prix,String note, String personne_nb, String etat_climatiseur, String type_boit_vitesse, String type_carburant, String disponible, String porte_nb, String remise, String nombre_options) {
        Immatricule = immatricule;
        this.photo = photo;
        this.nom = nom;
        this.prix = prix;
        this.note = note;
        this.personne_nb = personne_nb;
        this.etat_climatiseur = etat_climatiseur;
        this.type_boit_vitesse = type_boit_vitesse;
        this.type_carburant = type_carburant;
        this.disponible = disponible;
        this.porte_nb = porte_nb;
        this.remise = remise;
        this.nombre_options = nombre_options;
    }

    public String getImmatricule() {
        return Immatricule;
    }

    public String getPhoto() {
        return photo;
    }

    public String getNom() {
        return nom;
    }

    public String getPrix() {
        return prix;
    }

    public String getPersonne_nb() {
        return personne_nb;
    }

    public String getEtat_climatiseur() {
        return etat_climatiseur;
    }

    public String getType_boit_vitesse() {
        return type_boit_vitesse;
    }

    public String getType_carburant() {
        return type_carburant;
    }

    public String getDisponible() {
        return disponible;
    }

    public String getPorte_nb() {
        return porte_nb;
    }

    public String getRemise() {
        return remise;
    }

    public String getNombre_options() {
        return nombre_options;
    }

    public String getNote() {
        return note;
    }
}
