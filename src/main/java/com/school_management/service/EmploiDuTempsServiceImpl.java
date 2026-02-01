package com.school_management.service;

import com.school_management.dao.EmploiDuTempsDAO;
import com.school_management.dao.EmploiDuTempsDAOImpl;
import com.school_management.model.EmploiDuTemps;
import com.school_management.model.enums.Semestre;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class EmploiDuTempsServiceImpl implements EmploiDuTempsService {

    private final EmploiDuTempsDAO emploiDuTempsDAO;

    public EmploiDuTempsServiceImpl() {
        this.emploiDuTempsDAO = new EmploiDuTempsDAOImpl();
    }

    public void ajouter(EmploiDuTemps edt) throws SQLException, IOException {
        emploiDuTempsDAO.ajouter(edt);
    }

    public void modifier(EmploiDuTemps edt) throws SQLException, IOException {
        emploiDuTempsDAO.modifier(edt);
    }

    public void supprimer(Long id) throws SQLException, IOException {
        emploiDuTempsDAO.supprimer(id);
    }

    public List<EmploiDuTemps> lireTous() throws SQLException, IOException {
        return emploiDuTempsDAO.lireTous();
    }

    public EmploiDuTemps lireParClasse(Long classeId) throws SQLException, IOException {
        return emploiDuTempsDAO.lireParClasse(classeId);
    }

    public EmploiDuTemps lireParSeance(Long seanceId) throws SQLException, IOException {
        return emploiDuTempsDAO.lireParSeance(seanceId);
    }

    public EmploiDuTemps lireParId(Long id) throws SQLException, IOException {
        return emploiDuTempsDAO.lireParId(id);
    }

    public List<EmploiDuTemps> lireParAnneeScolaire(String anneeScolaire) throws SQLException, IOException {
        return emploiDuTempsDAO.lireParAnneeScolaire(anneeScolaire);
    }

    public List<EmploiDuTemps> lireParSemestre(Semestre semestre) throws SQLException, IOException {
        return emploiDuTempsDAO.lireParSemestre(semestre);
    }

    public List<EmploiDuTemps> lireParDateDebut(LocalDate dateDebut) throws SQLException, IOException {
        return emploiDuTempsDAO.lireParDateDebut(dateDebut);
    }

    public List<EmploiDuTemps> lireParDateFin(LocalDate dateFin) throws SQLException, IOException {
        return emploiDuTempsDAO.lireParDateFin(dateFin);
    }

    public List<EmploiDuTemps> lireParPeriode(LocalDate debut, LocalDate fin) throws SQLException, IOException {
        return emploiDuTempsDAO.lireParPeriode(debut, fin);
    }
}
