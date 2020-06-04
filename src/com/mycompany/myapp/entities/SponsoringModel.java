/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Khoubaib
 */
public class SponsoringModel {
  private int idSpons;
    private String description;
    private String type;
    private String nomCandidat;  
    private String image;
    private String date_debut ;
    private String date_fin ;

    public SponsoringModel() {
    }

    public SponsoringModel(int idSpons, String description, String type, String nomCandidat, String image, String date_debut, String date_fin) {
     this.idSpons = idSpons;
        this.description = description;
        this.type = type;
        this.nomCandidat = nomCandidat;
        this.image = image;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getIdSpons() {
        return idSpons;
    }

    public void setIdSpons(int idSpons) {
        this.idSpons = idSpons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNomCandidat() {
        return nomCandidat;
    }

    public void setNomCandidat(String nomCandidat) {
        this.nomCandidat = nomCandidat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "SponsoringModel{" + "idSpons=" + idSpons + ", description=" + description + ", type=" + type + ", nomCandidat=" + nomCandidat + ", image=" + image + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
    
    
}
