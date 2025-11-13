package com.school_management.model;

import com.school_management.model.enums.NiveauAnnee;
import com.school_management.model.enums.TypeDiplome;

import java.util.ArrayList;
import java.util.List;

public class Classe {
    private Long id;
    private String code;
    private String nom;
    private int capaciteMax;
    private String anneeScolaire;
    private TypeDiplome typeDiplome;
    private NiveauAnnee  niveauAnnee;
    private Long specialiteId;
    private Specialite  specialite;
//    private EmploiDuTemps emploiDuTemps;
    private List<Etudiant> etudiants;

    public Classe() {this.etudiants = new ArrayList<>();}
    public Classe(String code, String nom, int capaciteMax, String anneeScolaire, TypeDiplome typeDiplome, NiveauAnnee  niveauAnnee, Specialite  specialite) {
        this.code = code;
        this.nom = nom;
        this.capaciteMax = capaciteMax;
        this.anneeScolaire = anneeScolaire;
        this.typeDiplome = typeDiplome;
        this.niveauAnnee = niveauAnnee;
        this.specialite = specialite;
        this.etudiants = new ArrayList<>();
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        if (etudiants.size() < capaciteMax) {
            etudiants.add(etudiant);
        } else {
            System.out.println("Capacité maximale atteinte");
        }
    }

    public Integer getEffectif() {return etudiants.size();}

    // Getters and Setters
    public String getCode() {return code;}
    public void setCode(String code) {this.code = code;}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}

    public int getCapaciteMax() {return capaciteMax;}
    public void setCapaciteMax(int capaciteMax) {this.capaciteMax = capaciteMax;}

    public String getAnneeScolaire() {return anneeScolaire;}
    public void setAnneeScolaire(String anneeScolaire) {this.anneeScolaire = anneeScolaire;}

    public List<Etudiant> getEtudiants() {return etudiants;}
    public void setEtudiants(List<Etudiant> etudiants) {this.etudiants = etudiants;}

    public TypeDiplome getTypeDiplome() {return typeDiplome;}
    public void setTypeDiplome(TypeDiplome typeDiplome) {this.typeDiplome = typeDiplome;}

    public NiveauAnnee getNiveauAnnee() {return niveauAnnee;}
    public void setNiveauAnnee(NiveauAnnee niveauAnnee) {this.niveauAnnee = niveauAnnee;}

    public Long getSpecialiteId() {return specialiteId;}
    public void setSpecialiteId(Long specialiteId) {this.specialiteId = specialiteId;}

    public Specialite getSpecialite() {return specialite;}
    public void setSpecialite(Specialite specialite) {this.specialite = specialite;}

//    public EmploiDuTemps getEmploiDuTemps() {return emploiDuTemps;}
//    public void setEmploiDuTemps(EmploiDuTemps emploiDuTemps) {this.emploiDuTemps = emploiDuTemps;}
}
