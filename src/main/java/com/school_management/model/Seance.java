package com.school_management.model;

import java.time.Duration;
import java.time.LocalTime;

public class Seance {
    private Long id;
    private String jour;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String salle;
    private Long matiereId;
    private Matiere matiere;
    private Long enseignantId;
    private Enseignant enseignant;
    private Long emploiDuTempsId;
    private EmploiDuTemps emploiDuTemps;

    public Seance() {}
    public Seance(String jour, LocalTime heureDebut, LocalTime heureFin, String salle, Long matiereId, Long enseignantId, Long emploiDuTempsId) {
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.salle = salle;
        this.matiereId = matiereId;
        this.enseignantId = enseignantId;
        this.emploiDuTempsId = emploiDuTempsId;
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

    public String getJour() {return jour;}
    public void setJour(String jour) {this.jour = jour;}

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

    public Long getMatiereId() {return matiereId;}
    public void setMatiereId(Long matiereId) {this.matiereId = matiereId;}

    public Long getEnseignantId() {return enseignantId;}
    public void setEnseignantId(Long enseignantId) {this.enseignantId = enseignantId;}

    public Long getEmploiDuTempsId() {return emploiDuTempsId;}
    public void setEmploiDuTempsId(Long emploiDuTempsId) {this.emploiDuTempsId = emploiDuTempsId;}
}
