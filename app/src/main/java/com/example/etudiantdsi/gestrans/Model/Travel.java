package com.example.etudiantdsi.gestrans.Model;


import java.util.List;

public class Travel {
    private int id;
    private String type;
    private String deb_voyage;
    private String deb_voyage_reel;
    private String fin_voyage;
    private String fin_voyage_reel;
    private int kilometrage_estime;
    private String etat;
    private int chauffeur_id;
    private int bus_id;
    private String created_at;
    private String updated_at;
    private List<Employee> employees;
    private Bus bus;
    private Driver driver;
    private List<Incident> incidents;


    public Travel() {
    }

    public Travel(int id) {
        this.id = id;
    }

    public Travel(int id, String type, String deb_voyage, String deb_voyage_reel, String fin_voyage, String fin_voyage_reel, int kilometrage_estime, String etat, int chauffeur_id, int bus_id, String created_at, String updated_at, List<Employee> employees, Bus bus, Driver driver, List<Incident> incidents) {
        this.id = id;
        this.type = type;
        this.deb_voyage = deb_voyage;
        this.deb_voyage_reel = deb_voyage_reel;
        this.fin_voyage = fin_voyage;
        this.fin_voyage_reel = fin_voyage_reel;
        this.kilometrage_estime = kilometrage_estime;
        this.etat = etat;
        this.chauffeur_id = chauffeur_id;
        this.bus_id = bus_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.employees = employees;
        this.bus = bus;
        this.driver = driver;
        this.incidents = incidents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeb_voyage() {
        return deb_voyage;
    }

    public void setDeb_voyage(String deb_voyage) {
        this.deb_voyage = deb_voyage;
    }

    public String getDeb_voyage_reel() {
        return deb_voyage_reel;
    }

    public void setDeb_voyage_reel(String deb_voyage_reel) {
        this.deb_voyage_reel = deb_voyage_reel;
    }

    public String getFin_voyage() {
        return fin_voyage;
    }

    public void setFin_voyage(String fin_voyage) {
        this.fin_voyage = fin_voyage;
    }

    public String getFin_voyage_reel() {
        return fin_voyage_reel;
    }

    public void setFin_voyage_reel(String fin_voyage_reel) {
        this.fin_voyage_reel = fin_voyage_reel;
    }

    public int getKilometrage_estime() {
        return kilometrage_estime;
    }

    public void setKilometrage_estime(int kilometrage_estime) {
        this.kilometrage_estime = kilometrage_estime;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getChauffeur_id() {
        return chauffeur_id;
    }

    public void setChauffeur_id(int chauffeur_id) {
        this.chauffeur_id = chauffeur_id;
    }

    public int getBus_id() {
        return bus_id;
    }

    public void setBus_id(int bus_id) {
        this.bus_id = bus_id;
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }
}
