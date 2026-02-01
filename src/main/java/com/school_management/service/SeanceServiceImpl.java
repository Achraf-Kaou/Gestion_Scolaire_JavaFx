package com.school_management.service;

import com.school_management.dao.SeanceDAO;
import com.school_management.dao.SeanceDAOImpl;
import com.school_management.model.Seance;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public class SeanceServiceImpl implements SeanceService {

    private final SeanceDAO seanceDAO;

    public SeanceServiceImpl() {
        this.seanceDAO = new SeanceDAOImpl();
    }

    public void ajouter(Seance seance) throws SQLException, IOException {
        seanceDAO.ajouter(seance);
    }

    public void modifier(Seance seance) throws SQLException, IOException {
        seanceDAO.modifier(seance);
    }

    public void supprimer(Long id) throws SQLException, IOException {
        seanceDAO.supprimer(id);
    }

    public List<Seance> lireTous() throws SQLException, IOException {
        return seanceDAO.lireTous();
    }

    public List<Seance> lireParClasse(Long classeId) throws SQLException, IOException {
        return seanceDAO.lireParClasse(classeId);
    }

    public List<Seance> lireParEmploiDuTemps(Long emploiDuTempsId) throws SQLException, IOException {
        return seanceDAO.lireParEmploiDuTemps(emploiDuTempsId);
    }

    public List<Seance> lireParMatiere(Long matiereId) throws SQLException, IOException {
        return seanceDAO.lireParMatiere(matiereId);
    }

    public List<Seance> lireParEnseignant(Long enseignantId) throws SQLException, IOException {
        return seanceDAO.lireParEnseignant(enseignantId);
    }

    public List<Seance> lireParEtudiant(Long etudiantId) throws SQLException, IOException {
        return seanceDAO.lireParEtudiant(etudiantId);
    }

    public List<Seance> lireParJour(String jour) throws SQLException, IOException {
        return seanceDAO.lireParJour(jour);
    }

    public List<Seance> lireParSalle(String salle) throws SQLException, IOException {
        return seanceDAO.lireParSalle(salle);
    }

    public List<Seance> lireParHeureDebut(LocalTime heureDebut) throws SQLException, IOException {
        return seanceDAO.lireParHeureDebut(heureDebut);
    }

    public List<Seance> lireParHeureFin(LocalTime heureFin) throws SQLException, IOException {
        return seanceDAO.lireParHeureFin(heureFin);
    }

    public Seance lireParId(Long id) throws SQLException, IOException {
        return seanceDAO.lireParId(id);
    }
}
