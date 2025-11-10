package com.school_management.dao;

import com.school_management.model.Etudiant;
import com.school_management.config.DatabaseConfig;

import java.sql.*;
import java.util.List;

public class EtudiantDAOImpl implements EtudiantDAO {

    @Override
    public void ajouter(Etudiant etudiant) throws SQLException {
        String INSERT_ETUDIANT = SqlLoader.load("database/etudiant/insert.sql");
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_ETUDIANT, Statement.RETURN_GENERATED_KEYS)) {

            // Remplir les paramètres du PreparedStatement
            pstmt.setString(1, etudiant.getNumeroEtudiant());
            pstmt.setString(2, etudiant.getNom());
            pstmt.setString(3, etudiant.getPrenom());
            pstmt.setString(4, etudiant.getEmail());

            // Gestion de la date (peut être null)
            if (etudiant.getBirthDate() != null) {
                pstmt.setDate(5, Date.valueOf(etudiant.getBirthDate()));
            } else {
                pstmt.setNull(5, Types.DATE);
            }

            // Exécuter l'insertion
            int rowsAffected = pstmt.executeUpdate();

            // Vérifier que l'insertion a réussi
            if (rowsAffected == 0) {
                throw new SQLException("L'ajout de l'étudiant a échoué, aucune ligne affectée");
            }

            // Récupérer l'ID généré automatiquement
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    etudiant.setId(generatedKeys.getLong(1));
                    System.out.println("✓ Étudiant ajouté avec l'ID: " + etudiant.getId());
                }
            }

        } catch (SQLException e) {
            // Gestion spécifique des erreurs SQL
            if (e.getErrorCode() == 1062) { // Code MySQL pour duplication
                throw new SQLException("Un étudiant avec ce numéro existe déjà: " + etudiant.getNumeroEtudiant(), e);
            }
            throw new SQLException("Erreur lors de l'ajout de l'étudiant: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Etudiant> lireTous() throws SQLException {
        return List.of();
    }

    @Override
    public List<Etudiant> lireParClasse() throws SQLException {
        return List.of();
    }

    @Override
    public List<Etudiant> lireParNiveauAnnee() throws SQLException {
        return List.of();
    }

    @Override
    public List<Etudiant> lireParTypeDiplome() throws SQLException {
        return List.of();
    }

    @Override
    public List<Etudiant> lireParTypeDiplomeEtNiveauAnnee() throws SQLException {
        return List.of();
    }

    @Override
    public Etudiant lireParId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void modifier(Etudiant etudiant) throws SQLException {

    }

    @Override
    public void supprimer(Long id) throws SQLException {

    }
}
