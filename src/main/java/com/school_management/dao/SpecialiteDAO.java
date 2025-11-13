package com.school_management.dao;

import com.school_management.model.Specialite;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface SpecialiteDAO {
    void ajouter(Specialite specialite) throws SQLException, IOException;
    List<Specialite> lireTous() throws SQLException, IOException;
    Specialite lireParClasse(Long classeId) throws SQLException, IOException;
    Specialite lireParId(Long id) throws SQLException, IOException;
    List<Specialite> lireParMatiere(Long matiereId) throws SQLException, IOException;
    void modifier(Specialite specialite) throws SQLException, IOException;
    void supprimer(Long id) throws SQLException, IOException;
}
