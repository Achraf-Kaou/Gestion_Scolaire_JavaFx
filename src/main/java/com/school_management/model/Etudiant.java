package com.school_management.model;

import java.time.LocalDate;

public class Etudiant extends Personne {
    private String numeroEtudiant;
    private LocalDate dateInscription;
    private Classe classe;

    public Etudiant() {
        super();
    }
    public Etudiant(String nom, String prenom, String adresse, String telephone, String email, String password, String numeroEtudiant, LocalDate dateInscription, Classe classe) {
        super(nom, prenom, adresse, telephone, email, password);
        this.numeroEtudiant = numeroEtudiant;
        this.dateInscription = dateInscription;
        this.classe = classe;
    }

    @Override
    public void consulterEmploiDuTemps() {
        System.out.println("Etudiant consulter emploi du temps");
    }

    public String getNumeroEtudiant() {return numeroEtudiant;}
    public void setNumeroEtudiant(String numeroEtudiant) {this.numeroEtudiant = numeroEtudiant;}

    public LocalDate getDateInscription() {return dateInscription;}
    public void setDateInscription(LocalDate dateInscription) {this.dateInscription = dateInscription;}

    public Classe getClasse() {return classe;}
    public void setClasse(Classe classe) {this.classe = classe;}
}
