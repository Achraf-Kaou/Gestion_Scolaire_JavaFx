package com.school_management.model;

import java.util.ArrayList;
import java.util.List;

public class Specialite {
    private Long id;
    private String code;
    private String nom;
    private List<Matiere> matieres;

    public Specialite() {this.matieres = new ArrayList<>();}
    public Specialite(String code, String nom) {
        this.code = code;
        this.nom = nom;
        this.matieres = new ArrayList<>();
    }

    public void ajouterMatiere(Matiere matiere) {
        if (!matieres.contains(matiere)) {
            matieres.add(matiere);
        }
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getCode() {return code;}
    public void setCode(String code) {this.code = code;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
}
