package com.example.etudiantdsi.gestrans.Model;

import java.io.Serializable;

public class Bus {
    private String id;
    private String immatriculation;
    private String pseudo;
    private String nb_places;
    private String created_at;
    private String updated_at;

    public Bus() {
    }

    public Bus(String id) {
        this.id = id;
    }

    public Bus(String id, String immatriculation, String pseudo, String nb_places, String created_at, String updated_at) {
        this.id = id;
        this.immatriculation = immatriculation;
        this.pseudo = pseudo;
        this.nb_places = nb_places;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Info Bus : \n Immatriculation :"+immatriculation+"\n Pseudo :"+pseudo+"\n Nombre de places :"+nb_places+"\n";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNb_places() {
        return nb_places;
    }

    public void setNb_places(String nb_places) {
        this.nb_places = nb_places;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
