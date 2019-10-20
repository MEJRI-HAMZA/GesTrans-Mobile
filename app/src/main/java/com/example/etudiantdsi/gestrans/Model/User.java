package com.example.etudiantdsi.gestrans.Model;

public class User {
    private int id;
    private int employee_id;
    private String role;
    private String email;
    private String email_verified_at;
    private String created_at;
    private String updated_at;
    private Employee employee;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, int employee_id, String role, String email, String email_verified_at, String created_at, String updated_at, Employee e) {
        this.id = id;
        this.employee_id = employee_id;
        this.role = role;
        this.email = email;
        this.email_verified_at = email_verified_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.employee = e;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
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
        return employee;
    }

    public void setE(Employee e) {
        this.employee = e;
    }
}
