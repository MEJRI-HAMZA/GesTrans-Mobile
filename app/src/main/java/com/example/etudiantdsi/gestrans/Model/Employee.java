package com.example.etudiantdsi.gestrans.Model;

public class Employee {
    private int id;
    private String matricule;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String fonction;
    private String motorise;
    private String coordonnees_gps;
    private int regime_id;
    private String created_at;
    private String upadted_at;

    public Employee() {
    }

    public Employee(int id) {
        this.id = id;
    }

    public Employee(int id, String matricule, String nom, String prenom, String adresse, String tel, String fonction, String motorise, String coordonnees_gps, int regime) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.fonction = fonction;
        this.motorise = motorise;
        this.coordonnees_gps = coordonnees_gps;
        this.regime_id = regime;
    }

    public Employee(int id, String matricule, String nom, String prenom, String adresse, String tel, String fonction, String motorise, String coordonnees_gps, int regime, String created_at, String upadted_at) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.fonction = fonction;
        this.motorise = motorise;
        this.coordonnees_gps = coordonnees_gps;
        this.regime_id = regime;
        this.created_at = created_at;
        this.upadted_at = upadted_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getMotorise() {
        return motorise;
    }

    public void setMotorise(String motorise) {
        this.motorise = motorise;
    }

    public String getCoordonnees_gps() {
        return coordonnees_gps;
    }

    public void setCoordonnees_gps(String coordonnees_gps) {
        this.coordonnees_gps = coordonnees_gps;
    }

    public int getRegime_id() {
        return regime_id;
    }

    public void setRegime_id(int regime_id) {
        this.regime_id = regime_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpadted_at() {
        return upadted_at;
    }

    public void setUpadted_at(String upadted_at) {
        this.upadted_at = upadted_at;
    }

    @Override
    public String toString() {
        return "Driver  : "+nom+ " "+prenom ;
    }
}
