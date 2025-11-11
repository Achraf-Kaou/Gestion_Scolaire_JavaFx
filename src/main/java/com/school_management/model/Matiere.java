package com.school_management.model;

import java.util.ArrayList;
import java.util.List;

public class Matiere {
    private Long id;
    private String nom;
    private String code;
    private int nbHeuresCours;
    private int nbHeuresTd;
    private int nbHeuresTp;
    private int nbCredits;
    private List<Enseignant> enseignants;

    public Matiere() {this.enseignants = new ArrayList<>();}
    public Matiere(String nom, String code, int nbHeuresCours, int nbHeuresTd, int nb_heures_tp, int nbCredits) {
        this.nom = nom;
        this.code = code;
        this.nbHeuresCours = nbHeuresCours;
        this.nbHeuresTd = nbHeuresTd;
        this.nbHeuresTp = nb_heures_tp;
        this.nbCredits = nbCredits;
        this.enseignants = new ArrayList<>();
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public String getCode() {return code;}
    public void setCode(String code) {this.code = code;}

    public int getNbHeuresCours() {return nbHeuresCours;}
    public void setNbHeuresCours(int nbHeuresCours) {this.nbHeuresCours = nbHeuresCours;}

    public int getNbHeuresTd() {return nbHeuresTd;}
    public void setNbHeuresTd(int nbHeuresTd) {this.nbHeuresTd = nbHeuresTd;}

    public int getNbHeuresTp() {return nbHeuresTp;}
    public void setNbHeuresTp(int nbHeuresTp) {this.nbHeuresTp = nbHeuresTp;}

    public int getNbCredits() {return nbCredits;}
    public void setNbCredits(int nbCredits) {this.nbCredits = nbCredits;}
}
