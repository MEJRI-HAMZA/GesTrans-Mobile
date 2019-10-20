package com.example.etudiantdsi.gestrans.Model;

public class Driver  {
    private int id;
    private int employee_id;
    private int num_permis;
    private String categorie_permis;
    private String fin_validite_permis;
    private String created_at;
    private String updated_at;
    private Employee e;

    public Driver() { }

    public Driver(int id) {
        this.id = id;
    }

    public Driver(int id, int employee_id, int num_permis, String categorie_permis, String fin_validite_permis, String created_at, String updated_at) {
        this.id = id;
        this.employee_id = employee_id;
        this.num_permis = num_permis;
        this.categorie_permis = categorie_permis;
        this.fin_validite_permis = fin_validite_permis;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getNum_permis() {
        return num_permis;
    }

    public void setNum_permis(int num_permis) {
        this.num_permis = num_permis;
    }

    public String getCategorie_permis() {
        return categorie_permis;
    }

    public void setCategorie_permis(String categorie_permis) {
        this.categorie_permis = categorie_permis;
    }

    public String getFin_validite_permis() {
        return fin_validite_permis;
    }

    public void setFin_validite_permis(String fin_validite_permis) {
        this.fin_validite_permis = fin_validite_permis;
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

    public Employee getE() {
        return e;
    }

    public void setE(Employee e) {
        this.e = e;
    }
}
