package com.school_management.dao;

import com.school_management.model.Enseignant;
import com.school_management.config.DatabaseConfig;
import com.school_management.utils.SqlLoader;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnseignantDAOImpl implements EnseignantDAO {

    @Override
    public void ajouter(Enseignant enseignant) throws SQLException, IOException {
        String sql = SqlLoader.load("database/enseignant/insert.sql");
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, enseignant.getNom());
            pstmt.setString(2, enseignant.getPrenom());
            pstmt.setString(3, enseignant.getAdresse());
            pstmt.setString(4, enseignant.getTelephone());
            pstmt.setString(5, enseignant.getEmail());
            pstmt.setString(6, enseignant.getPassword());
            pstmt.setDate(7, enseignant.getBirthDate() != null ? Date.valueOf(enseignant.getBirthDate()) : null);
            pstmt.setBytes(8, enseignant.getPhoto() != null ? convertByteArray(enseignant.getPhoto()) : null);
            pstmt.setString(9, enseignant.getNumeroEnseignant());
            pstmt.setString(10, enseignant.getSpecialite());
            pstmt.setString(11, enseignant.getGrade());
            pstmt.setDate(12, enseignant.getDateRecrutement() != null ? Date.valueOf(enseignant.getDateRecrutement()) : null);

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    enseignant.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    @Override
    public List<Enseignant> lireTous() throws SQLException, IOException {
        String sql = SqlLoader.load("database/enseignant/selectAll.sql");
        List<Enseignant> enseignants = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                enseignants.add(mapToEnseignant(rs));
            }
        }
        return enseignants;
    }

    @Override
    public Enseignant lireParEmail(String email) throws SQLException, IOException {
        String sql = SqlLoader.load("database/enseignant/selectByEmail.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToEnseignant(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Enseignant> lireParGrade(String grade) throws SQLException, IOException {
        String sql = SqlLoader.load("database/enseignant/selectByGrade.sql");
        List<Enseignant> enseignants = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, grade);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    enseignants.add(mapToEnseignant(rs));
                }
            }
        }
        return enseignants;
    }

    @Override
    public Enseignant lireParSeance(Long seanceId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/enseignant/selectBySeance.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, seanceId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToEnseignant(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Enseignant> lireParMatiere(Long matiereId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/enseignant/selectByMatiere.sql");
        List<Enseignant> enseignants = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, matiereId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    enseignants.add(mapToEnseignant(rs));
                }
            }
        }
        return enseignants;
    }

    @Override
    public Enseignant lireParId(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/enseignant/selectById.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToEnseignant(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Enseignant> lireParSpecialite(String specialite) throws SQLException, IOException {
        String sql = SqlLoader.load("database/enseignant/selectByMatiere.sql");
        List<Enseignant> enseignants = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, specialite);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    enseignants.add(mapToEnseignant(rs));
                }
            }
        }
        return enseignants;
    }

    @Override
    public void modifier(Enseignant enseignant) throws SQLException, IOException {
        String sql = SqlLoader.load("database/enseignant/update.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, enseignant.getNom());
            pstmt.setString(2, enseignant.getPrenom());
            pstmt.setString(3, enseignant.getAdresse());
            pstmt.setString(4, enseignant.getTelephone());
            pstmt.setString(5, enseignant.getEmail());
            pstmt.setString(6, enseignant.getPassword());
            pstmt.setDate(7, enseignant.getBirthDate() != null ? Date.valueOf(enseignant.getBirthDate()) : null);
            pstmt.setBytes(8, enseignant.getPhoto() != null ? convertByteArray(enseignant.getPhoto()) : null);
            pstmt.setString(9, enseignant.getNumeroEnseignant());
            pstmt.setString(10, enseignant.getSpecialite());
            pstmt.setString(11, enseignant.getGrade());
            pstmt.setDate(12, enseignant.getDateRecrutement() != null ? Date.valueOf(enseignant.getDateRecrutement()) : null);
            pstmt.setLong(13, enseignant.getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void supprimer(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/enseignant/delete.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode utilitaire pour mapper ResultSet vers Enseignant
    private Enseignant mapToEnseignant(ResultSet rs) throws SQLException {
        Enseignant e = new Enseignant();
        e.setId(rs.getLong("id"));
        e.setNom(rs.getString("nom"));
        e.setPrenom(rs.getString("prenom"));
        e.setAdresse(rs.getString("adresse"));
        e.setTelephone(rs.getString("telephone"));
        e.setEmail(rs.getString("email"));
        e.setPassword(rs.getString("password"));
        e.setBirthDate(rs.getDate("birthDate") != null ? rs.getDate("birthDate").toLocalDate() : null);
        e.setPhoto(rs.getBytes("photo"));
        e.setNumeroEnseignant(rs.getString("numeroEnseignant"));
        e.setSpecialite(rs.getString("specialite"));
        e.setGrade(rs.getString("grade"));
        e.setDateRecrutement(rs.getDate("dateRecrutement") != null ? rs.getDate("dateRecrutement").toLocalDate() : null);
        return e;
    }

    private byte[] convertByteArray(byte[] bytes) {
        if (bytes == null) return null;
        byte[] result = new byte[bytes.length];
        System.arraycopy(bytes, 0, result, 0, bytes.length);
        return result;
    }
}
