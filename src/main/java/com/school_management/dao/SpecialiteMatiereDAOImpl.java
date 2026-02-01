package com.school_management.dao;

import com.school_management.config.DatabaseConfig;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialiteMatiereDAOImpl implements SpecialiteMatiereDAO {

    @Override
    public void associer(Long matiereId, Long specialiteId) throws SQLException, IOException {
        String sql = "INSERT INTO specialitematiere (matiere_id, specialite_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, matiereId);
            ps.setLong(2, specialiteId);
            ps.executeUpdate();
        }
    }

    @Override
    public void supprimerAssociationsMatiere(Long matiereId) throws SQLException, IOException {
        String sql = "DELETE FROM specialitematiere WHERE matiere_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, matiereId);
            ps.executeUpdate();
        }
    }

    @Override
    public void supprimerAssociationsSpecialite(Long specialiteId) throws SQLException, IOException {
        String sql = "DELETE FROM specialitematiere WHERE specialite_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, specialiteId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Long> getSpecialiteIdsParMatiere(Long matiereId) throws SQLException, IOException {
        String sql = "SELECT specialite_id FROM specialitematiere WHERE matiere_id = ?";
        List<Long> specialiteIds = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, matiereId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    specialiteIds.add(rs.getLong(1));
                }
            }
        }
        return specialiteIds;
    }

    @Override
    public List<Long> getMatiereIdsParSpecialite(Long specialiteId) throws SQLException, IOException {
        String sql = "SELECT matiere_id FROM specialitematiere WHERE specialite_id = ?";
        List<Long> matiereIds = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, specialiteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    matiereIds.add(rs.getLong(1));
                }
            }
        }
        return matiereIds;
    }
}
