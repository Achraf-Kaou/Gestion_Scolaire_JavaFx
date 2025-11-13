package com.school_management.dao;

import com.school_management.model.Seance;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public interface SeanceDAO {
    void ajouter(Seance seance) throws SQLException, IOException;
    List<Seance> lireTous() throws SQLException, IOException;
    List<Seance> lireParClasse(Long classeId) throws SQLException, IOException;
    List<Seance> lireParEmploiDuTemps(Long emploiDuTempsId) throws SQLException, IOException;
    List<Seance> lireParMatiere(Long matiereId) throws SQLException, IOException;
    List<Seance> lireParEnseignant(Long enseignantId) throws SQLException, IOException;
    List<Seance> lireParEtudiant(Long etudiantId) throws SQLException, IOException;
    List<Seance> lireParJour(String jour) throws SQLException, IOException;
    List<Seance> lireParSalle(String salle) throws SQLException, IOException;
    List<Seance> lireParHeureDebut(LocalTime heureDebut) throws SQLException, IOException;
    List<Seance> lireParHeureFin(LocalTime heureFin) throws SQLException, IOException;
    Seance lireParId(Long id) throws SQLException, IOException;
    void modifier(Seance seance) throws SQLException, IOException;
    void supprimer(Long id) throws SQLException, IOException;
}
