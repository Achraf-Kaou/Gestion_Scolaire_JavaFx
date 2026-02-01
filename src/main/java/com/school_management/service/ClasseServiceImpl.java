package com.school_management.service;

import com.school_management.dao.ClasseDAO;
import com.school_management.dao.ClasseDAOImpl;
import com.school_management.model.Classe;
import com.school_management.model.enums.NiveauAnnee;
import com.school_management.model.enums.TypeDiplome;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ClasseServiceImpl implements ClasseService {

    private final ClasseDAO classeDAO;

    public ClasseServiceImpl() {
        this.classeDAO = new ClasseDAOImpl();
    }

    public void ajouter(Classe classe) throws SQLException, IOException {
        classeDAO.ajouter(classe);
    }

    public void modifier(Classe classe) throws SQLException, IOException {
        classeDAO.modifier(classe);
    }

    public void supprimer(Long id) throws SQLException, IOException {
        classeDAO.supprimer(id);
    }

    public List<Classe> lireTous() throws SQLException, IOException {
        return classeDAO.lireTous();
    }

    public Classe lireParEtudiant(Long etudiantId) throws SQLException, IOException {
        return classeDAO.lireParEtudiant(etudiantId);
    }

    public List<Classe> lireParSpecialite(Long specialiteId) throws SQLException, IOException {
        return classeDAO.lireParSpecialite(specialiteId);
    }

    public Classe lireParEmploiDuTemps(Long emploiDuTempsId) throws SQLException, IOException {
        return classeDAO.lireParEmploiDuTemps(emploiDuTempsId);
    }

    public Classe lireParSeance(Long seanceId) throws SQLException, IOException {
        return classeDAO.lireParSeance(seanceId);
    }

    public Classe lireParId(Long id) throws SQLException, IOException {
        return classeDAO.lireParId(id);
    }

    public List<Classe> lireParAnneeScolaire(String anneeScolaire) throws SQLException, IOException {
        return classeDAO.lireParAnneeScolaire(anneeScolaire);
    }

    public List<Classe> lireParTypeDiplome(TypeDiplome typeDiplome) throws SQLException, IOException {
        return classeDAO.lireParTypeDiplome(typeDiplome);
    }

    public List<Classe> lireParNiveauAnnee(NiveauAnnee niveauAnnee) throws SQLException, IOException {
        return classeDAO.lireParNiveauAnnee(niveauAnnee);
    }
}
