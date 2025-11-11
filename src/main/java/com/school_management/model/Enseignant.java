package com.school_management.model;

import java.time.LocalDate;
import java.util.List;

public class Enseignant extends Personne{
    private String numeroEnseignant;
    private String specialite;
    private LocalDate dateRecrutement;
    private String grade;

    public Enseignant(String nom, String prenom, String adresse, String telephone, String email, String password, String numeroEnseignant, String specialite, LocalDate dateRecrutement) {
        super(nom, prenom, adresse, telephone, email, password);
        this.numeroEnseignant = numeroEnseignant;
        this.specialite = specialite;
        this.dateRecrutement = dateRecrutement;
    }

    public int getChargeHoraire(){
        return 1;
    }

    public List<String> getMatieresEnseignees() {
        return List.of("math", "physique");
    }

    public boolean estDisponible(LocalDate tempsVoulu){
        return true;
    }

    @Override
    public void consulterEmploiDuTemps() {
        System.out.println("Enseignant consulte Emploi Du Temps");
    }

    public String getNumeroEnseignant() {return numeroEnseignant;}
    public void setNumeroEnseignant(String numeroEnseignant) {this.numeroEnseignant = numeroEnseignant;}

    public String getSpecialite() {return specialite;}
    public void setSpecialite(String specialite) {this.specialite = specialite;}

    public LocalDate getDateRecrutement() {return dateRecrutement;}
    public void setDateRecrutement(LocalDate dateRecrutement) {this.dateRecrutement = dateRecrutement;}

    public String getGrade() {return grade;}
    public void setGrade(String grade) {this.grade = grade;}
}
