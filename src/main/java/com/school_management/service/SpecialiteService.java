package com.school_management.service;

import com.school_management.model.Matiere;
import com.school_management.model.Specialite;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface SpecialiteService {
    public void ajouter(Specialite specialite) throws SQLException, IOException;
    public void supprimer(Long specialiteId) throws SQLException, IOException;
    public void modifier(Specialite specialite) throws SQLException, IOException;
    public List<Specialite> listeSpecialites() throws SQLException, IOException;
    public Specialite listeSpecialitesId(Long id) throws SQLException, IOException;
    public Set<Matiere> getMatieresBySpecialite(Long specialiteId) throws SQLException, IOException;
    public void ajouterMatiereDansSpecialite(Long specialiteId, Matiere matiere) throws SQLException, IOException;
    public void modifierMatieresSpecialite(Long specialiteId, Set<Matiere> matieres) throws SQLException, IOException;
    public void modifierAvecMatieres(Specialite specialite) throws SQLException, IOException;
    public void ajouterAvecMatieres(Specialite specialite) throws SQLException, IOException;
}
