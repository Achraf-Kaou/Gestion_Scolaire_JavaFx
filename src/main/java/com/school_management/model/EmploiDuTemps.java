package com.school_management.model;

import java.util.ArrayList;
import java.util.List;

public class EmploiDuTemps {
    private Long id;
    private int semestre;
    private String anneeScolaire;
    private List<Seance>  seances;

    public EmploiDuTemps() {this.seances = new ArrayList<>();}
    public EmploiDuTemps(int semestre, String anneeScolaire) {
        this.semestre = semestre;
        this.anneeScolaire = anneeScolaire;
        this.seances = new ArrayList<>();
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

    public int getSemestre() {return semestre;}
    public void setSemestre(int semestre) {this.semestre = semestre;}

    public String getAnneeScolaire() {return anneeScolaire;}
    public void setAnneeScolaire(String anneeScolaire) {this.anneeScolaire = anneeScolaire;}

    public List<Seance> getSeances() {return seances;}
    public void setSeances(List<Seance> seances) {this.seances = seances;}
}
