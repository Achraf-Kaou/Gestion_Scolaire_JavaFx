package com.school_management.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Seance {
    private Long id;
    private LocalDate jour;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String salle;
    private Matiere matiere;
    private Enseignant enseignant;
    private EmploiDuTemps emploiDuTemps;

    public Seance(LocalDate jour, LocalTime heureDebut, LocalTime heureFin, String salle, Matiere matiere, Enseignant enseignant, EmploiDuTemps emploiDuTemps) {
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.salle = salle;
        this.matiere = matiere;
        this.enseignant = enseignant;
        this.emploiDuTemps = emploiDuTemps;
    }

    public Integer getDuree() {
        try {
            return (int) Duration.between(heureDebut, heureFin).toMinutes();
        } catch (Exception e) {
            return 0;
        }
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public LocalDate getJour() {return jour;}
    public void setJour(LocalDate jour) {this.jour = jour;}

    public LocalTime getHeureDebut() {return heureDebut;}
    public void setHeureDebut(LocalTime heureDebut) {this.heureDebut = heureDebut;}

    public LocalTime getHeureFin() {return heureFin;}
    public void setHeureFin(LocalTime heureFin) {this.heureFin = heureFin;}

    public String getSalle() {return salle;}
    public void setSalle(String salle) {this.salle = salle;}

    public Matiere getMatiere() {return matiere;}
    public void setMatiere(Matiere matiere) {this.matiere = matiere;}

    public Enseignant getEnseignant() {return enseignant;}
    public void setEnseignant(Enseignant enseignant) {this.enseignant = enseignant;}

    public EmploiDuTemps getEmploiDuTemps() {return emploiDuTemps;}
    public void setEmploiDuTemps(EmploiDuTemps emploiDuTemps) {this.emploiDuTemps = emploiDuTemps;}
}
