package com.school_management.dao;

import com.school_management.model.Classe;
import com.school_management.model.enums.NiveauAnnee;
import com.school_management.model.enums.TypeDiplome;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ClasseDAO {
    void ajouter(Classe classe) throws SQLException, IOException;
    List<Classe> lireTous() throws SQLException, IOException;
    Classe lireParEtudiant(Long etudiantId) throws SQLException, IOException;
    List<Classe> lireParSpecialite(Long specialiteId) throws SQLException, IOException;
    Classe lireParEmploiDuTemps(Long emploiDuTempsId) throws SQLException, IOException;
    Classe lireParSeance(Long seanceId) throws SQLException, IOException;
    Classe lireParId(Long id) throws SQLException, IOException;
    List<Classe> lireParAnneeScolaire (String anneeScolaire) throws SQLException, IOException;
    List<Classe> lireParTypeDiplome (TypeDiplome typeDiplome) throws SQLException, IOException;
    List<Classe> lireParNiveauAnnee (NiveauAnnee niveauAnnee) throws SQLException, IOException;
    void modifier(Classe classe) throws SQLException, IOException;
    void supprimer(Long id) throws SQLException, IOException;
}
