package com.school_management.dao;

import com.school_management.model.Enseignant;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface EnseignantDAO {
    void ajouter(Enseignant enseignant) throws SQLException, IOException;
    List<Enseignant> lireTous() throws SQLException, IOException;
    Enseignant lireParEmail(String Email) throws SQLException, IOException;
    List<Enseignant> lireParGrade(String grade) throws SQLException, IOException;
    Enseignant lireParSeance(Long SeanceId) throws SQLException, IOException;
    List<Enseignant> lireParMatiere(Long matiereId) throws SQLException, IOException;
    Enseignant lireParId(Long id) throws SQLException, IOException;
    List<Enseignant> lireParSpecialite (String specialite) throws SQLException, IOException;
    void modifier(Enseignant enseignant) throws SQLException, IOException;
    void supprimer(Long id) throws SQLException, IOException;
}
