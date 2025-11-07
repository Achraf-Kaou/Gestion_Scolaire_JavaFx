package com.school_management.model;

import java.time.LocalDate;

public class Etudiant extends Personne {
    private String numeroEtudiant;
    private LocalDate dateInscription;

    public Etudiant(String nom, String prenom, String adresse, String telephone, String email, String password, String numeroEtudiant, LocalDate dateInscription) {
        super(nom, prenom, adresse, telephone, email, password);
        this.numeroEtudiant = numeroEtudiant;
        this.dateInscription = dateInscription;
    }

    @Override
    public void consulterEmploiDuTemps() {
        System.out.println("Etudiant consulter emploi du temps");
    }

    public String getNumeroEtudiant() {return numeroEtudiant;}
    public void setNumeroEtudiant(String numeroEtudiant) {this.numeroEtudiant = numeroEtudiant;}

    public LocalDate getDateInscription() {return dateInscription;}
    public void setDateInscription(LocalDate dateInscription) {this.dateInscription = dateInscription;}
}
