package com.school_management.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Seance {
    private Long id;
    private LocalDate jour;
    private LocalTime debut;
    private LocalTime fin;
    private String salle;
    private Matiere matiere;
    private Enseignant enseignant;
    private Classe classe;

    public Seance(LocalDate jour, LocalTime debut, LocalTime fin, String salle, Matiere matiere, Enseignant enseignant, Classe classe) {
        this.jour = jour;
        this.debut = debut;
        this.fin = fin;
        this.salle = salle;
        this.matiere = matiere;
        this.enseignant = enseignant;
        this.classe = classe;
    }

    public Integer getDuree() {
        try {
            return (int) Duration.between(debut, fin).toMinutes();
        } catch (Exception e) {
            return 0;
        }
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public LocalDate getJour() {return jour;}
    public void setJour(LocalDate jour) {this.jour = jour;}

    public LocalTime getDebut() {return debut;}
    public void setDebut(LocalTime debut) {this.debut = debut;}

    public LocalTime getFin() {return fin;}
    public void setFin(LocalTime fin) {this.fin = fin;}

    public String getSalle() {return salle;}
    public void setSalle(String salle) {this.salle = salle;}

    public Matiere getMatiere() {return matiere;}
    public void setMatiere(Matiere matiere) {this.matiere = matiere;}

    public Enseignant getEnseignant() {return enseignant;}
    public void setEnseignant(Enseignant enseignant) {this.enseignant = enseignant;}

    public Classe getClasse() {return classe;}
    public void setClasse(Classe classe) {this.classe = classe;}
}
