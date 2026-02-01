package com.school_management.service;

import com.school_management.dao.EtudiantDAOImpl;
import com.school_management.exception.EmailNotFoundException;
import com.school_management.exception.IncorrectPasswordException;
import com.school_management.model.Etudiant;
import com.school_management.model.enums.NiveauAnnee;
import com.school_management.model.enums.TypeDiplome;
import com.school_management.dao.EtudiantDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EtudiantServiceImpl implements EtudiantService {
    private final EtudiantDAO etudiantDAO;

    public EtudiantServiceImpl() {
        this.etudiantDAO = new EtudiantDAOImpl();
    }

    public void ajouter(Etudiant e) throws SQLException, IOException {
        etudiantDAO.ajouter(e);
    }

    public void modifier(Etudiant e) throws SQLException, IOException {
        etudiantDAO.modifier(e);
    }

    public void supprimer(Long id) throws SQLException, IOException {
        etudiantDAO.supprimer(id);
    }

    public List<Etudiant> lireTous() throws SQLException, IOException {
        return etudiantDAO.lireTous();
    }

    public List<Etudiant> lireParClasse(Long classeId) throws SQLException, IOException {
        return etudiantDAO.lireParClasse(classeId);
    }

    public List<Etudiant> lireParNiveauAnnee(NiveauAnnee niveauAnnee) throws SQLException, IOException {
        return etudiantDAO.lireParNiveauAnnee(niveauAnnee);
    }

    public List<Etudiant> lireParTypeDiplome(TypeDiplome typeDiplome) throws SQLException, IOException {
        return etudiantDAO.lireParTypeDiplome(typeDiplome);
    }

    public List<Etudiant> lireParTypeDiplomeEtNiveauAnnee(TypeDiplome typeDiplome, NiveauAnnee niveauAnnee) throws SQLException, IOException {
        return etudiantDAO.lireParTypeDiplomeEtNiveauAnnee(typeDiplome, niveauAnnee);
    }

    public List<Etudiant> lireParSpecialite(Long specialiteId) throws SQLException, IOException {
        return etudiantDAO.lireParSpeacialité(specialiteId);
    }

    public Etudiant lireParId(Long id) throws SQLException, IOException {
        return etudiantDAO.lireParId(id);
    }

    public Etudiant seConnecter(String email, String password) throws EmailNotFoundException, IncorrectPasswordException, SQLException, IOException {
        Etudiant etu = etudiantDAO.lireParEmail(email);
        if (etu == null) {
            throw new EmailNotFoundException("Aucun compte trouvé avec cet email : " + email);
        }
        if (!etu.getPassword().equals(password)) {
            throw new IncorrectPasswordException("Mot de passe incorrect !");
        }
        return etu;
    }
}