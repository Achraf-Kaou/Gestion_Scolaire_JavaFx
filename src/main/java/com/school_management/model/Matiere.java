package com.school_management.model;

import java.util.ArrayList;
import java.util.List;

public class Matiere {
    private Long id;
    private String nom;
    private String code;
    private int nbHeures;
    private int nbCredits;
    private List<Enseignant> enseignants;

    public Matiere() {this.enseignants = new ArrayList<>();}
    public Matiere(String code, String nom, int nbHeures, int nbCredits) {
        this.code = code;
        this.nom = nom;
        this.nbHeures = nbHeures;
        this.nbCredits = nbCredits;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public String getCode() {return code;}
    public void setCode(String code) {this.code = code;}

    public int getNbHeures() {return nbHeures;}
    public void setNbHeures(int nbHeures) {this.nbHeures = nbHeures;}

    public int getNbCredits() {return nbCredits;}
    public void setNbCredits(int nbCredits) {this.nbCredits = nbCredits;}
}
