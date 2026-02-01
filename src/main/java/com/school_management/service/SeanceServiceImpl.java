package com.school_management.service;

import com.school_management.dao.*;
import com.school_management.model.Seance;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public class SeanceServiceImpl implements SeanceService {

    private final SeanceDAO seanceDAO;
    private final MatiereDAO matiereDAO;
    private final EnseignantDAO enseignantDAO;
    private final EmploiDuTempsDAO emploiDuTempsDAO;

    public SeanceServiceImpl() {
        this.seanceDAO = new SeanceDAOImpl();
        this.matiereDAO = new MatiereDAOImpl();
        this.enseignantDAO = new EnseignantDAOImpl();
        this.emploiDuTempsDAO = new EmploiDuTempsDAOImpl();
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
        List<Seance> seances = seanceDAO.lireTous();
        loadRelatedObjects(seances);
        return seances;
    }

    public List<Seance> lireParClasse(Long classeId) throws SQLException, IOException {
        List<Seance> seances = seanceDAO.lireParClasse(classeId);
        loadRelatedObjects(seances);
        return seances;
    }

    public List<Seance> lireParEmploiDuTemps(Long emploiDuTempsId) throws SQLException, IOException {
        List<Seance> seances = seanceDAO.lireParEmploiDuTemps(emploiDuTempsId);
        loadRelatedObjects(seances);
        return seances;
    }

    public List<Seance> lireParMatiere(Long matiereId) throws SQLException, IOException {
        List<Seance> seances = seanceDAO.lireParMatiere(matiereId);
        loadRelatedObjects(seances);
        return seances;
    }

    public List<Seance> lireParEnseignant(Long enseignantId) throws SQLException, IOException {
        List<Seance> seances = seanceDAO.lireParEnseignant(enseignantId);
        loadRelatedObjects(seances);
        return seances;
    }

    public List<Seance> lireParEtudiant(Long etudiantId) throws SQLException, IOException {
        List<Seance> seances = seanceDAO.lireParEtudiant(etudiantId);
        loadRelatedObjects(seances);
        return seances;
    }

    public List<Seance> lireParJour(String jour) throws SQLException, IOException {
        List<Seance> seances = seanceDAO.lireParJour(jour);
        loadRelatedObjects(seances);
        return seances;
    }

    public List<Seance> lireParSalle(String salle) throws SQLException, IOException {
        List<Seance> seances = seanceDAO.lireParSalle(salle);
        loadRelatedObjects(seances);
        return seances;
    }

    public List<Seance> lireParHeureDebut(LocalTime heureDebut) throws SQLException, IOException {
        List<Seance> seances = seanceDAO.lireParHeureDebut(heureDebut);
        loadRelatedObjects(seances);
        return seances;
    }

    public List<Seance> lireParHeureFin(LocalTime heureFin) throws SQLException, IOException {
        List<Seance> seances = seanceDAO.lireParHeureFin(heureFin);
        loadRelatedObjects(seances);
        return seances;
    }

    public Seance lireParId(Long id) throws SQLException, IOException {
        Seance seance = seanceDAO.lireParId(id);
        if (seance != null) {
            loadRelatedObjects(List.of(seance));
        }
        return seance;
    }

    private void loadRelatedObjects(List<Seance> seances) throws SQLException, IOException {
        for (Seance seance : seances) {
            if (seance.getMatiereId() != null) {
                seance.setMatiere(matiereDAO.lireParId(seance.getMatiereId()));
            }
            if (seance.getEnseignantId() != null) {
                seance.setEnseignant(enseignantDAO.lireParId(seance.getEnseignantId()));
            }
            if (seance.getEmploiDuTempsId() != null) {
                seance.setEmploiDuTemps(emploiDuTempsDAO.lireParId(seance.getEmploiDuTempsId()));
            }
        }
    }
}
