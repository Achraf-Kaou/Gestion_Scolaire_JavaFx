package com.school_management.service;

import com.school_management.exception.EmailNotFoundException;
import com.school_management.exception.IncorrectPasswordException;
import com.school_management.model.Etudiant;
import com.school_management.model.enums.NiveauAnnee;
import com.school_management.model.enums.TypeDiplome;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface EtudiantService {
    public void ajouter(Etudiant e) throws SQLException, IOException;
    public void modifier(Etudiant e) throws SQLException, IOException;
    public void supprimer(Long id) throws SQLException, IOException;
    public List<Etudiant> lireTous() throws SQLException, IOException;
    public List<Etudiant> lireParClasse(Long classeId) throws SQLException, IOException;
    public List<Etudiant> lireParNiveauAnnee(NiveauAnnee niveauAnnee) throws SQLException, IOException;
    public List<Etudiant> lireParTypeDiplome(TypeDiplome typeDiplome) throws SQLException, IOException;
    public List<Etudiant> lireParTypeDiplomeEtNiveauAnnee(TypeDiplome typeDiplome, NiveauAnnee niveauAnnee) throws SQLException, IOException;
    public List<Etudiant> lireParSpecialite(Long specialiteId) throws SQLException, IOException;
    public Etudiant lireParId(Long id) throws SQLException, IOException;
    public Etudiant seConnecter (String email, String password) throws EmailNotFoundException, IncorrectPasswordException, SQLException, IOException;
}
