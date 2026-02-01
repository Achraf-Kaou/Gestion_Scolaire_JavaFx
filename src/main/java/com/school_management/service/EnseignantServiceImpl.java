package com.school_management.service;

import com.school_management.dao.EnseignantDAO;
import com.school_management.dao.EnseignantDAOImpl;
import com.school_management.exception.EmailNotFoundException;
import com.school_management.exception.IncorrectPasswordException;
import com.school_management.model.Enseignant;
import com.school_management.model.Etudiant;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EnseignantServiceImpl implements EnseignantService {

    private final EnseignantDAO enseignantDAO;

    public EnseignantServiceImpl() {
        this.enseignantDAO = new EnseignantDAOImpl();
    }

    public void ajouter(Enseignant e) throws SQLException, IOException {
        enseignantDAO.ajouter(e);
    }

    public void modifier(Enseignant e) throws SQLException, IOException {
        enseignantDAO.modifier(e);
    }

    public void supprimer(Long id) throws SQLException, IOException {
        enseignantDAO.supprimer(id);
    }

    public List<Enseignant> lireTous() throws SQLException, IOException {
        return enseignantDAO.lireTous();
    }

    public Enseignant lireParEmail(String email) throws SQLException, IOException {
        return enseignantDAO.lireParEmail(email);
    }

    public List<Enseignant> lireParGrade(String grade) throws SQLException, IOException {
        return enseignantDAO.lireParGrade(grade);
    }

    public Enseignant lireParSeance(Long seanceId) throws SQLException, IOException {
        return enseignantDAO.lireParSeance(seanceId);
    }

    public List<Enseignant> lireParMatiere(Long matiereId) throws SQLException, IOException {
        return enseignantDAO.lireParMatiere(matiereId);
    }

    public Enseignant lireParId(Long id) throws SQLException, IOException {
        return enseignantDAO.lireParId(id);
    }

    public List<Enseignant> lireParSpecialite(String specialite) throws SQLException, IOException {
        return enseignantDAO.lireParSpecialite(specialite);
    }

    public Enseignant seConnecter(String email, String password) throws EmailNotFoundException, IncorrectPasswordException, SQLException, IOException {
        Enseignant ens = enseignantDAO.lireParEmail(email);
        if (ens == null) {
            throw new EmailNotFoundException("Aucun compte trouvé avec cet email : " + email);
        }
        if (!ens.getPassword().equals(password)) {
            throw new IncorrectPasswordException("Mot de passe incorrect !");
        }
        return ens;
    }

}
