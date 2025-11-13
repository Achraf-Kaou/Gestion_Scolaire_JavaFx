package com.school_management.model;

import com.school_management.model.enums.Semestre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmploiDuTemps {
    private Long id;
    private Semestre semestre;
    private String anneeScolaire;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Long classeId;
    private Classe classe;
    private List<Enseignant> enseignants;
    private List<Seance>  seances;

    public EmploiDuTemps() {
        this.seances = new ArrayList<>();
        this.enseignants = new ArrayList<>();
    }
    public EmploiDuTemps(Semestre semestre, String anneeScolaire, LocalDate dateDebut, LocalDate dateFin, Long classeId) {
        this.semestre = semestre;
        this.anneeScolaire = anneeScolaire;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.classeId = classeId;
        this.seances = new ArrayList<>();
        this.enseignants = new ArrayList<>();
    }

    public void ajouterSeance(Seance seance) {
        seances.add(seance);
    }

    public void exporterPDF() {
        System.out.println("PDF");
    }

    //Getters and Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Semestre getSemestre() {return semestre;}
    public void setSemestre(Semestre semestre) {this.semestre = semestre;}

    public String getAnneeScolaire() {return anneeScolaire;}
    public void setAnneeScolaire(String anneeScolaire) {this.anneeScolaire = anneeScolaire;}

    public List<Seance> getSeances() {return seances;}
    public void setSeances(List<Seance> seances) {this.seances = seances;}

    public LocalDate getDateDebut() {return dateDebut;}
    public void setDateDebut(LocalDate dateDebut) {this.dateDebut = dateDebut;}

    public LocalDate getDateFin() {return dateFin;}
    public void setDateFin(LocalDate dateFin) {this.dateFin = dateFin;}

    public Classe getClasse() {return this.classe;}
    public void setClasse(Classe classe) {this.classe = classe;}

    public Long getClasseId() {return classeId;}
    public void setClasseId(Long classId) {this.classeId = classId;}

    public List<Enseignant> getEnseignants() {return enseignants;}
    public void setEnseignants(List<Enseignant> enseignants) {this.enseignants = enseignants;}
}
