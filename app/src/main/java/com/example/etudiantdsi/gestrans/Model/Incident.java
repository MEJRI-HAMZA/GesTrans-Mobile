package com.example.etudiantdsi.gestrans.Model;

public class Incident {

    private String id;
    private String travel_id;
    private String motif;
    private String deb_incident;
    private String fin_incident;
    private String description ;
    private String created_at;
    private String updated_at;

    public Incident() {
    }

    public Incident(String id) {
        this.id = id;
    }

    public Incident(String id, String id_travel, String motif, String deb_incident, String fin_incident, String description) {
        this.id = id;
        this.travel_id = id_travel;
        this.motif = motif;
        this.deb_incident = deb_incident;
        this.fin_incident = fin_incident;
        this.description = description;
    }

    public Incident(String id, String id_travel, String motif, String deb_incident, String fin_incident, String description, String created_at, String updated_at) {
        this.id = id;
        this.travel_id = id_travel;
        this.motif = motif;
        this.deb_incident = deb_incident;
        this.fin_incident = fin_incident;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_travel() {
        return travel_id;
    }

    public void setId_travel(String id_travel) {
        this.travel_id = id_travel;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDeb_incident() {
        return deb_incident;
    }

    public void setDeb_incident(String deb_incident) {
        this.deb_incident = deb_incident;
    }

    public String getFin_incident() {
        return fin_incident;
    }

    public void setFin_incident(String fin_incident) {
        this.fin_incident = fin_incident;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
