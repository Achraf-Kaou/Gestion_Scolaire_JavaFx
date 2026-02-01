package com.school_management.service;

import com.school_management.dao.MatiereDAO;
import com.school_management.dao.MatiereDAOImpl;
import com.school_management.model.Matiere;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MatiereServiceImpl implements MatiereService{
    private final MatiereDAO matiereDAO;

    public MatiereServiceImpl() {
        this.matiereDAO = new MatiereDAOImpl();
    }

    @Override
    public void ajouter(Matiere matiere) throws IOException, SQLException {
        matiereDAO.ajouter(matiere);
    }

    @Override
    public void supprimer(long matiereId) throws IOException, SQLException {
        matiereDAO.supprimer(matiereId);
    }

    @Override
    public void modifier(Matiere matiere) throws IOException, SQLException {
        matiereDAO.modifier(matiere);
    }

    @Override
    public List<Matiere> listeMatieres() throws SQLException, IOException {
        return matiereDAO.lireTous();
    }

    @Override
    public Matiere listeMatieresId(long id) throws SQLException, IOException {
        return matiereDAO.lireParId(id);
    }

    @Override
    public List<Matiere> listeMatieresParEnseignant(long id) throws SQLException, IOException {
        return matiereDAO.lireParEnseignant(id);
    }

    @Override
    public List<Matiere> listeMatieresParSpecilite(long id) throws SQLException, IOException {
        return matiereDAO.lireParSpecialite(id);
    }
}
