package com.school_management.dao;

import com.school_management.model.Specialite;
import com.school_management.config.DatabaseConfig;
import com.school_management.utils.SqlLoader;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialiteDAOImpl implements SpecialiteDAO {

    @Override
    public void ajouter(Specialite specialite) throws SQLException, IOException {
        String sql = SqlLoader.load("database/specialite/insert.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, specialite.getCode());
            pstmt.setString(2, specialite.getNom());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    specialite.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    @Override
    public List<Specialite> lireTous() throws SQLException, IOException {
        String sql = SqlLoader.load("database/specialite/selectAll.sql");
        List<Specialite> specialites = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                specialites.add(mapToSpecialite(rs));
            }
        }
        return specialites;
    }

    @Override
    public Specialite lireParClasse(Long classeId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/specialite/selectByClasse.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, classeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToSpecialite(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Specialite lireParId(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/specialite/selectById.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToSpecialite(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Specialite> lireParMatiere(Long matiereId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/specialite/selectByMatiere.sql");
        List<Specialite> specialites = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, matiereId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    specialites.add(mapToSpecialite(rs));
                }
            }
        }
        return specialites;
    }

    @Override
    public void modifier(Specialite specialite) throws SQLException, IOException {
        String sql = SqlLoader.load("database/specialite/update.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, specialite.getCode());
            pstmt.setString(2, specialite.getNom());
            pstmt.setLong(3, specialite.getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void supprimer(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/specialite/delete.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode utilitaire pour mapper ResultSet vers Specialite
    private Specialite mapToSpecialite(ResultSet rs) throws SQLException {
        Specialite s = new Specialite();
        s.setId(rs.getLong("id"));
        s.setCode(rs.getString("code"));
        s.setNom(rs.getString("nom"));
        return s;
    }
}