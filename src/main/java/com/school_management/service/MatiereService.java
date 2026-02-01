package com.school_management.service;

import com.school_management.model.Matiere;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface MatiereService {
    public void ajouter(Matiere matiere) throws IOException, SQLException;
    public void supprimer(long matiereId) throws IOException, SQLException;
    public void modifier(Matiere matiere) throws IOException, SQLException;
    public List<Matiere> listeMatieres() throws SQLException, IOException;
    public Matiere listeMatieresId(long id) throws SQLException, IOException;
    public List<Matiere> listeMatieresParEnseignant(long id) throws SQLException, IOException;
    public List<Matiere> listeMatieresParSpecilite(long id) throws SQLException, IOException;
}
