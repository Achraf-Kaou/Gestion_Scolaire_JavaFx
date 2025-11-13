package com.school_management.dao;

import com.school_management.model.Enseignant;
import com.school_management.model.Matiere;
import com.school_management.model.enums.NiveauAnnee;
import com.school_management.model.enums.TypeDiplome;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface MatiereDAO {
    void ajouter(Matiere matiere) throws SQLException, IOException;
    List<Matiere> lireTous() throws SQLException, IOException;
    List<Matiere> lireParSpecialite(Long specialiteId) throws SQLException, IOException;
    Matiere lireParSeance(Long Id) throws SQLException, IOException;
    List<Matiere> lireParEnseignant(Long enseignantId) throws SQLException, IOException;
    Matiere lireParId(Long id) throws SQLException, IOException;
    void modifier(Matiere matiere) throws SQLException, IOException;
    void supprimer(Long id) throws SQLException, IOException;
}
