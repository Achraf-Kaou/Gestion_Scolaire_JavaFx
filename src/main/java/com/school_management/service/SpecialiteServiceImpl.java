package com.school_management.service;

import com.school_management.dao.SpecialiteDAO;
import com.school_management.dao.SpecialiteDAOImpl;
import com.school_management.dao.SpecialiteMatiereDAO;
import com.school_management.dao.SpecialiteMatiereDAOImpl;
import com.school_management.model.Matiere;
import com.school_management.model.Specialite;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpecialiteServiceImpl implements SpecialiteService{

    private final SpecialiteDAO specialiteDAO;
    private final SpecialiteMatiereDAO liaisonDAO = new SpecialiteMatiereDAOImpl();

    public SpecialiteServiceImpl() {
        this.specialiteDAO = new SpecialiteDAOImpl();
    }

    @Override
    public void ajouter(Specialite specialite) throws SQLException, IOException {
        specialiteDAO.ajouter(specialite);
    }

    @Override
    public void supprimer(Long specialiteId) throws SQLException, IOException {
        specialiteDAO.supprimer(specialiteId);

    }

    @Override
    public void modifier(Specialite specialite) throws SQLException, IOException {
        specialiteDAO.modifier(specialite);
    }

    @Override
    public List<Specialite> listeSpecialites() throws SQLException, IOException {
        List<Specialite> specialites = specialiteDAO.lireTous();
        // Load associated matieres for each specialite
        MatiereService matiereService = new MatiereServiceImpl();
        for (Specialite specialite : specialites) {
            List<Matiere> matieres = matiereService.listeMatieresParSpecilite(specialite.getId());
            specialite.setMatieres(new java.util.HashSet<>(matieres));
        }
        return specialites;
    }

    @Override
    public Specialite listeSpecialitesId(Long id) throws SQLException, IOException {
        return specialiteDAO.lireParId(id);
    }

    @Override
    public Set<Matiere> getMatieresBySpecialite(Long specialiteId) throws SQLException, IOException {
        Specialite spec = this.listeSpecialitesId(specialiteId);
        return spec.getMatieres();
    }

    @Override
    public void ajouterMatiereDansSpecialite(Long specialiteId, Matiere matiere) throws SQLException, IOException {
        Specialite spec = this.listeSpecialitesId(specialiteId);
        spec.getMatieres().add(matiere);
        modifier(spec);
    }

    @Override
    public void modifierMatieresSpecialite(Long specialiteId, Set<Matiere> matieres) throws SQLException, IOException {
        Specialite spec = this.listeSpecialitesId(specialiteId);
        spec.setMatieres(matieres);
        modifier(spec);
    }

    public void ajouterAvecMatieres(Specialite specialite) throws SQLException, IOException {
        // Ajout Specialite + récupération ID
        specialiteDAO.ajouter(specialite);
        // Ajout liaisons
        if (specialite.getMatieres() != null) {
            for (Matiere m : specialite.getMatieres()) {
                liaisonDAO.associer(m.getId(), specialite.getId());
            }
        }
    }

    @Override
    public void modifierAvecMatieres(Specialite specialite) throws SQLException, IOException {
        specialiteDAO.modifier(specialite);
        liaisonDAO.supprimerAssociationsSpecialite(specialite.getId());
        if (specialite.getMatieres() != null) {
            for (Matiere m : specialite.getMatieres()) {
                liaisonDAO.associer(m.getId(), specialite.getId());
            }
        }
    }
}
