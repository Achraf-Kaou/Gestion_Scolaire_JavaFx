package com.school_management.service;

import com.school_management.model.EmploiDuTemps;
import com.school_management.model.enums.Semestre;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface EmploiDuTempsService {
    public void ajouter(EmploiDuTemps edt) throws SQLException, IOException;
    public void modifier(EmploiDuTemps edt) throws SQLException, IOException;
    public void supprimer(Long id) throws SQLException, IOException;
    public List<EmploiDuTemps> lireTous() throws SQLException, IOException;
    public EmploiDuTemps lireParClasse(Long classeId) throws SQLException, IOException;
    public EmploiDuTemps lireParSeance(Long seanceId) throws SQLException, IOException;
    public EmploiDuTemps lireParId(Long id) throws SQLException, IOException;
    public List<EmploiDuTemps> lireParAnneeScolaire(String anneeScolaire) throws SQLException, IOException;
    public List<EmploiDuTemps> lireParSemestre(Semestre semestre) throws SQLException, IOException;
    public List<EmploiDuTemps> lireParDateDebut(LocalDate dateDebut) throws SQLException, IOException;
    public List<EmploiDuTemps> lireParDateFin(LocalDate dateFin) throws SQLException, IOException;
    public List<EmploiDuTemps> lireParPeriode(LocalDate debut, LocalDate fin) throws SQLException, IOException;

}
