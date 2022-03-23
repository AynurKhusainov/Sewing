package com.example.myapplication.DB;

public class Employee {
    public int role;
    public String mail, password;
    public Employee(){}

    public Employee(int role, String mail, String password) {
        this.role = role;
        this.mail = mail;
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
