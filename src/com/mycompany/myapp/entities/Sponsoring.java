/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import com.codename1.ui.Form;

/**
 *
 * @author Khoubaib
 */
public class Sponsoring {
    private int id;
    private String description;
    private String type;
    private String image;
    private int Sponsor_id;
    private int candidat_id;
    private String date_debut;
    private String date_fin;

    public Sponsoring() {
    }
    
    public Sponsoring(String description, String type, String image, int Sponsor_id, int candidat_id, String date_debut, String date_fin) {
        this.description = description;
        this.type = type;
        this.image = image;
        this.Sponsor_id = Sponsor_id;
        this.candidat_id = candidat_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Sponsoring(int id, String description, String type, String image, int Sponsor_id, int candidat_id, String date_debut, String date_fin) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.image = image;
        this.Sponsor_id = Sponsor_id;
        this.candidat_id = candidat_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSponsor_id() {
        return Sponsor_id;
    }

    public void setSponsor_id(int Sponsor_id) {
        this.Sponsor_id = Sponsor_id;
    }

    public int getCandidat_id() {
        return candidat_id;
    }

    public void setCandidat_id(int candidat_id) {
        this.candidat_id = candidat_id;
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
        return "Sponsoring{" + "id=" + id + ", description=" + description + ", type=" + type + ", image=" + image + ", Sponsor_id=" + Sponsor_id + ", candidat_id=" + candidat_id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }

  
    
}
