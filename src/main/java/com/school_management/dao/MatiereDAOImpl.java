package com.school_management.dao;

import com.school_management.model.Matiere;
import com.school_management.config.DatabaseConfig;
import com.school_management.utils.SqlLoader;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatiereDAOImpl implements  MatiereDAO {
    @Override
    public void ajouter(Matiere matiere) throws SQLException, IOException {
        String sql = SqlLoader.load("database/matiere/insert.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, matiere.getCode());
            pstmt.setString(2, matiere.getNom());
            pstmt.setInt(3, matiere.getNbHeuresCours());
            pstmt.setInt(4, matiere.getNbHeuresTd());
            pstmt.setInt(5, matiere.getNbHeuresTp());
            pstmt.setInt(6, matiere.getNbCredits());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    matiere.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    @Override
    public List<Matiere> lireTous() throws SQLException, IOException {
        String sql = SqlLoader.load("database/matiere/selectAll.sql");
        List<Matiere> matieres = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                matieres.add(mapToMatiere(rs));
            }
        }
        return matieres;
    }

    @Override
    public List<Matiere> lireParSpecialite(Long specialiteId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/matiere/selectBySpecialite.sql");
        List<Matiere> matieres = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, specialiteId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    matieres.add(mapToMatiere(rs));
                }
            }
        }
        return matieres;
    }

    @Override
    public Matiere lireParSeance(Long seanceId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/matiere/selectBySeance.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, seanceId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToMatiere(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Matiere> lireParEnseignant(Long enseignantId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/matiere/selectByEnseignant.sql");
        List<Matiere> matieres = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, enseignantId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    matieres.add(mapToMatiere(rs));
                }
            }
        }
        return matieres;
    }

    @Override
    public Matiere lireParId(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/matiere/selectById.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToMatiere(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void modifier(Matiere matiere) throws SQLException, IOException {
        String sql = SqlLoader.load("database/matiere/update.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, matiere.getCode());
            pstmt.setString(2, matiere.getNom());
            pstmt.setInt(3, matiere.getNbHeuresCours());
            pstmt.setInt(4, matiere.getNbHeuresTd());
            pstmt.setInt(5, matiere.getNbHeuresTp());
            pstmt.setInt(6, matiere.getNbCredits());
            pstmt.setLong(7, matiere.getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void supprimer(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/matiere/delete.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode utilitaire pour mapper ResultSet vers Matiere
    private Matiere mapToMatiere(ResultSet rs) throws SQLException {
        Matiere m = new Matiere();
        m.setId(rs.getLong("id"));
        m.setCode(rs.getString("code"));
        m.setNom(rs.getString("nom"));
        m.setNbHeuresCours(rs.getInt("nbHeuresCours"));
        m.setNbHeuresTd(rs.getInt("nbHeuresTd"));
        m.setNbHeuresTp(rs.getInt("nbHeuresTp"));
        m.setNbCredits(rs.getInt("nbCredits"));
        return m;
    }
}
