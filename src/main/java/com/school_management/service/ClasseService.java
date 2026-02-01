package com.school_management.service;

import com.school_management.model.Classe;
import com.school_management.model.Classe;
import com.school_management.model.enums.NiveauAnnee;
import com.school_management.model.enums.TypeDiplome;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public interface ClasseService {
    public void ajouter(Classe classe) throws SQLException, IOException;
    public void modifier(Classe classe) throws SQLException, IOException;
    public void supprimer(Long id) throws SQLException, IOException;
    public List<Classe> lireTous() throws SQLException, IOException;
    public Classe lireParEtudiant(Long etudiantId) throws SQLException, IOException;
    public List<Classe> lireParSpecialite(Long specialiteId) throws SQLException, IOException;
    public Classe lireParEmploiDuTemps(Long emploiDuTempsId) throws SQLException, IOException;
    public Classe lireParSeance(Long seanceId) throws SQLException, IOException;
    public Classe lireParId(Long id) throws SQLException, IOException;
    public List<Classe> lireParAnneeScolaire(String anneeScolaire) throws SQLException, IOException;
    public List<Classe> lireParTypeDiplome(TypeDiplome typeDiplome) throws SQLException, IOException;
    public List<Classe> lireParNiveauAnnee(NiveauAnnee niveauAnnee) throws SQLException, IOException;

}
