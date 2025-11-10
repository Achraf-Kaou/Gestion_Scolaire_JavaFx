package com.school_management.dao;

import com.school_management.model.Etudiant;

import java.sql.SQLException;
import java.util.List;

public interface EtudiantDAO {
    void ajouter(Etudiant etudiant) throws SQLException;
    List<Etudiant> lireTous() throws SQLException;
    List<Etudiant> lireParClasse() throws SQLException;
    List<Etudiant> lireParNiveauAnnee() throws SQLException;
    List<Etudiant> lireParTypeDiplome() throws SQLException;
    List<Etudiant> lireParTypeDiplomeEtNiveauAnnee() throws SQLException;
    Etudiant lireParId(Long id) throws SQLException;
    void modifier(Etudiant etudiant) throws SQLException;
    void supprimer(Long id) throws SQLException;
}
