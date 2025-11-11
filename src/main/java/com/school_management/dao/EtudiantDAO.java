package com.school_management.dao;

import com.school_management.model.Etudiant;
import com.school_management.model.enums.NiveauAnnee;
import com.school_management.model.enums.TypeDiplome;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface EtudiantDAO {
    void ajouter(Etudiant etudiant) throws SQLException, IOException;
    List<Etudiant> lireTous() throws SQLException, IOException;
    List<Etudiant> lireParClasse(Long classeId) throws SQLException, IOException;
    List<Etudiant> lireParEmail(String Email) throws SQLException, IOException;
    List<Etudiant> lireParNiveauAnnee(NiveauAnnee niveauAnnee) throws SQLException, IOException;
    List<Etudiant> lireParTypeDiplome(TypeDiplome typeDiplome) throws SQLException, IOException;
    List<Etudiant> lireParTypeDiplomeEtNiveauAnnee(TypeDiplome typeDiplome, NiveauAnnee niveauAnnee) throws SQLException, IOException;
    List<Etudiant> lireParSpeacialité(Long specialitéId) throws SQLException, IOException;
    Etudiant lireParId(Long id) throws SQLException, IOException;
    void modifier(Etudiant etudiant) throws SQLException, IOException;
    void supprimer(Long id) throws SQLException, IOException;
}
