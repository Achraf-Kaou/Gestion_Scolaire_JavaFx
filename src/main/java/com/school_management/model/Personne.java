package com.school_management.model;

import java.time.LocalDate;
import java.time.Period;

public abstract class Personne {
    protected Long id;
    protected String nom;
    protected String prenom;
    protected String adresse;
    protected String telephone;
    protected String email;
    protected String password;
    protected LocalDate birthDate;
    protected byte[] photo;

    public Personne() {}
    public Personne (String nom, String prenom, String adresse, String telephone, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.birthDate = LocalDate.now();
    }
    public Personne(String nom, String prenom, String adresse, String telephone, String email, String password, LocalDate birthDate, byte[] photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.photo = photo;
    }

    public int getAge() {
        if  (this.birthDate != null) {
            return Period.between(this.birthDate, LocalDate.now()).getYears();
        }
        return 0;
    }

    public abstract void consulterEmploiDuTemps();

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}

    public String getAdresse() {return adresse;}
    public void setAdresse(String adresse) {this.adresse = adresse;}

    public String getTelephone() {return telephone;}
    public void setTelephone(String telephone) {this.telephone = telephone;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public LocalDate getBirthDate() {return birthDate;}
    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}

    public byte[] getPhoto() {return photo;}
    public void setPhoto(byte[] photo) {this.photo = photo;}
}
