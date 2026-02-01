package com.school_management.service;

import com.school_management.model.Seance;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public interface SeanceService {
    public void ajouter(Seance seance) throws SQLException, IOException;
    public void modifier(Seance seance) throws SQLException, IOException;
    public void supprimer(Long id) throws SQLException, IOException;
    public List<Seance> lireTous() throws SQLException, IOException;
    public List<Seance> lireParClasse(Long classeId) throws SQLException, IOException;
    public List<Seance> lireParEmploiDuTemps(Long emploiDuTempsId) throws SQLException, IOException;
    public List<Seance> lireParMatiere(Long matiereId) throws SQLException, IOException;
    public List<Seance> lireParEnseignant(Long enseignantId) throws SQLException, IOException;
    public List<Seance> lireParEtudiant(Long etudiantId) throws SQLException, IOException;
    public List<Seance> lireParJour(String jour) throws SQLException, IOException;
    public List<Seance> lireParSalle(String salle) throws SQLException, IOException;
    public List<Seance> lireParHeureDebut(LocalTime heureDebut) throws SQLException, IOException;
    public List<Seance> lireParHeureFin(LocalTime heureFin) throws SQLException, IOException;
    public Seance lireParId(Long id) throws SQLException, IOException;
}
