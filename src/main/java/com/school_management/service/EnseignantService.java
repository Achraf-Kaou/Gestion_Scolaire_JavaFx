package com.school_management.service;

import com.school_management.exception.EmailNotFoundException;
import com.school_management.exception.IncorrectPasswordException;
import com.school_management.model.Enseignant;
import com.school_management.model.Etudiant;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface EnseignantService {
    public void ajouter(Enseignant e) throws SQLException, IOException;
    public void modifier(Enseignant e) throws SQLException, IOException;
    public void supprimer(Long id) throws SQLException, IOException;
    public List<Enseignant> lireTous() throws SQLException, IOException;
    public Enseignant lireParEmail(String email) throws SQLException, IOException;
    public List<Enseignant> lireParGrade(String grade) throws SQLException, IOException;
    public Enseignant lireParSeance(Long seanceId) throws SQLException, IOException;
    public List<Enseignant> lireParMatiere(Long matiereId) throws SQLException, IOException;
    public Enseignant lireParId(Long id) throws SQLException, IOException;
    public List<Enseignant> lireParSpecialite(String specialite) throws SQLException, IOException;
    public Enseignant seConnecter (String email, String password) throws EmailNotFoundException, IncorrectPasswordException, SQLException, IOException;

}
