package com.school_management.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Specialite {
    private Long id;
    private String code;
    private String nom;
    private Set<Matiere> matieres = new HashSet<>();


    public Specialite() {}
    public Specialite(String code, String nom) {
        this.code = code;
        this.nom = nom;
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

    public Set<Matiere> getMatieres() {return matieres;}
    public void setMatieres(Set<Matiere> matieres) {this.matieres = matieres;}
}
