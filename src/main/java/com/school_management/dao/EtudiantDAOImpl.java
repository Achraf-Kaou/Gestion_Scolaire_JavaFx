package com.school_management.dao;

import com.school_management.model.Etudiant;
import com.school_management.config.DatabaseConfig;
import com.school_management.model.enums.NiveauAnnee;
import com.school_management.model.enums.TypeDiplome;
import com.school_management.utils.SqlLoader;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAOImpl implements EtudiantDAO {

    @Override
    public void ajouter(Etudiant etudiant) throws SQLException, IOException {
        String sql = SqlLoader.load("database/etudiant/insert.sql");
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, etudiant.getNom());
            pstmt.setString(2, etudiant.getPrenom());
            pstmt.setString(3, etudiant.getAdresse());
            pstmt.setString(4, etudiant.getTelephone());
            pstmt.setString(5, etudiant.getEmail());
            pstmt.setString(6, etudiant.getPassword());
            pstmt.setDate(7, etudiant.getBirthDate() != null ? Date.valueOf(etudiant.getBirthDate()) : null);
            pstmt.setBytes(8, etudiant.getPhoto() != null ? convertByteArray(etudiant.getPhoto()) : null);
            pstmt.setString(9, etudiant.getNumeroEtudiant());
            pstmt.setDate(10, etudiant.getDateInscription() != null ? Date.valueOf(etudiant.getDateInscription()) : null);
            pstmt.setLong(11, etudiant.getClasseId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Etudiant> lireTous() throws SQLException, IOException {
        String sql = SqlLoader.load("database/etudiant/selectAll.sql");
        List<Etudiant> etudiants = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) etudiants.add(map(rs));
        }
        return etudiants;
    }

    @Override
    public List<Etudiant> lireParClasse(Long classeId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/etudiant/selectByClass.sql");
        List<Etudiant> etudiants = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, classeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) etudiants.add(map(rs));
            }
        }
        return etudiants;
    }

    @Override
    public Etudiant lireParEmail(String email) throws SQLException, IOException {
        String sql = SqlLoader.load("database/etudiant/selectByEmail.sql");
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    @Override
    public List<Etudiant> lireParNiveauAnnee(NiveauAnnee niveauAnnee) throws SQLException, IOException {
        String sql = SqlLoader.load("database/etudiant/selectByNiveauAnnee.sql");
        List<Etudiant> etudiants = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, niveauAnnee.name());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) etudiants.add(map(rs));
            }
        }
        return etudiants;
    }

    @Override
    public List<Etudiant> lireParTypeDiplome(TypeDiplome typeDiplome) throws SQLException, IOException {
        String sql = SqlLoader.load("database/etudiant/selectByTypeDiplome.sql");
        List<Etudiant> etudiants = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, typeDiplome.name());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) etudiants.add(map(rs));
            }
        }
        return etudiants;
    }

    @Override
    public List<Etudiant> lireParTypeDiplomeEtNiveauAnnee(TypeDiplome typeDiplome, NiveauAnnee niveauAnnee) throws SQLException, IOException {
        String sql = SqlLoader.load("database/etudiant/SelectByTypeDiplomeEtNiveauAnnee.sql");
        List<Etudiant> etudiants = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, typeDiplome.name());
            pstmt.setString(2, niveauAnnee.name());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) etudiants.add(map(rs));
            }
        }
        return etudiants;
    }

    @Override
    public List<Etudiant> lireParSpeacialité(Long specialiteId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/etudiant/selectBySpecilite.sql");
        List<Etudiant> etudiants = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, specialiteId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) etudiants.add(map(rs));
            }
        }
        return etudiants;
    }

    @Override
    public Etudiant lireParId(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/etudiant/selectById.sql");
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println(rs.getLong("id"));
                    System.out.println(rs.getString("nom"));
                    System.out.println(rs.getString("prenom"));
                    return map(rs);
                };
            }
        }
        return null;
    }

    @Override
    public void modifier(Etudiant etudiant) throws SQLException, IOException {
        String sql = SqlLoader.load("database/etudiant/update.sql");
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, etudiant.getNom());
            pstmt.setString(2, etudiant.getPrenom());
            pstmt.setString(3, etudiant.getAdresse());
            pstmt.setString(4, etudiant.getTelephone());
            pstmt.setString(5, etudiant.getEmail());
            pstmt.setString(6, etudiant.getPassword());
            pstmt.setDate(7, etudiant.getBirthDate() != null ? Date.valueOf(etudiant.getBirthDate()) : null);
            pstmt.setBytes(8, etudiant.getPhoto() != null ? convertByteArray(etudiant.getPhoto()) : null);
            pstmt.setString(9, etudiant.getNumeroEtudiant());
            pstmt.setDate(10, etudiant.getDateInscription() != null ? Date.valueOf(etudiant.getDateInscription()) : null);
            pstmt.setLong(11, etudiant.getClasse().getId());
            pstmt.setLong(12, etudiant.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void supprimer(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/etudiant/delete.sql");
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    // Utilitaires
    private Etudiant map(ResultSet rs) throws SQLException {
        Etudiant e = new Etudiant();
        e.setId(rs.getLong("id"));
        e.setNom(rs.getString("nom"));
        e.setPrenom(rs.getString("prenom"));
        e.setAdresse(rs.getString("adresse"));
        e.setTelephone(rs.getString("telephone"));
        e.setEmail(rs.getString("email"));
        e.setPassword(rs.getString("password"));
        e.setBirthDate(rs.getDate("birthDate") != null ? rs.getDate("birthDate").toLocalDate() : null);
        e.setPhoto(rs.getBytes("photo"));
        e.setNumeroEtudiant(rs.getString("numeroEtudiant"));
        e.setDateInscription(rs.getDate("dateInscription") != null ? rs.getDate("dateInscription").toLocalDate() : null);
        e.setClasseId(rs.getLong("classeId"));
        return e;
    }

    private byte[] convertByteArray(byte[] bytes) {
        if (bytes == null) return null;
        byte[] result = new byte[bytes.length];
        System.arraycopy(bytes, 0, result, 0, bytes.length);
        return result;
    }
}
