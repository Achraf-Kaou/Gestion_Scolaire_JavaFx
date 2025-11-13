package com.school_management.dao;

import com.school_management.model.EmploiDuTemps;
import com.school_management.model.enums.Semestre;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface EmploiDuTempsDAO {
    void ajouter(EmploiDuTemps emploiDuTemps) throws SQLException, IOException;
    List<EmploiDuTemps> lireTous() throws SQLException, IOException;
    EmploiDuTemps lireParClasse(Long classeId) throws SQLException, IOException;
    EmploiDuTemps lireParSeance(Long seanceId) throws SQLException, IOException;
    EmploiDuTemps lireParId(Long id) throws SQLException, IOException;
    List<EmploiDuTemps> lireParAnneeScolaire(String anneeScolaire) throws SQLException, IOException;
    List<EmploiDuTemps> lireParSemestre(Semestre semestre) throws SQLException, IOException;
    List<EmploiDuTemps> lireParDateDebut(LocalDate dateDebut) throws SQLException, IOException;
    List<EmploiDuTemps> lireParDateFin(LocalDate dateFin) throws SQLException, IOException;
    List<EmploiDuTemps> lireParPeriode(LocalDate dateDebut, LocalDate dateFin) throws SQLException, IOException;
    void modifier(EmploiDuTemps emploiDuTemps) throws SQLException, IOException;
    void supprimer(Long id) throws SQLException, IOException;
}