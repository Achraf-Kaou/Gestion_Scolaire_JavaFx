package com.school_management.dao;

import com.school_management.model.Seance;
import com.school_management.config.DatabaseConfig;
import com.school_management.utils.SqlLoader;

import java.io.IOException;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAOImpl implements SeanceDAO {

    @Override
    public void ajouter(Seance seance) throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/insert.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, seance.getJour());
            pstmt.setTime(2, Time.valueOf(seance.getHeureDebut()));
            pstmt.setTime(3, Time.valueOf(seance.getHeureFin()));
            pstmt.setString(4, seance.getSalle());
            pstmt.setLong(5, seance.getMatiereId());
            pstmt.setLong(6, seance.getEnseignantId());
            pstmt.setLong(7, seance.getEmploiDuTempsId());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    seance.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    @Override
    public List<Seance> lireTous() throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/selectAll.sql");
        List<Seance> seances = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                seances.add(mapToSeance(rs));
            }
        }
        return seances;
    }

    @Override
    public List<Seance> lireParClasse(Long classeId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/selectByClasse.sql");
        List<Seance> seances = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, classeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    seances.add(mapToSeance(rs));
                }
            }
        }
        return seances;
    }

    @Override
    public List<Seance> lireParEmploiDuTemps(Long emploiDuTempsId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/selectByEmploiDuTemps.sql");
        List<Seance> seances = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, emploiDuTempsId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    seances.add(mapToSeance(rs));
                }
            }
        }
        return seances;
    }

    @Override
    public List<Seance> lireParMatiere(Long matiereId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/selectByMatiere.sql");
        List<Seance> seances = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, matiereId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    seances.add(mapToSeance(rs));
                }
            }
        }
        return seances;
    }

    @Override
    public List<Seance> lireParEnseignant(Long enseignantId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/selectByEnseignant.sql");
        List<Seance> seances = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, enseignantId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    seances.add(mapToSeance(rs));
                }
            }
        }
        return seances;
    }

    @Override
    public List<Seance> lireParEtudiant(Long etudiantId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/selectByEtudiant.sql");
        List<Seance> seances = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, etudiantId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    seances.add(mapToSeance(rs));
                }
            }
        }
        return seances;
    }

    @Override
    public List<Seance> lireParJour(String jour) throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/selectByJour.sql");
        List<Seance> seances = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, jour);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    seances.add(mapToSeance(rs));
                }
            }
        }
        return seances;
    }

    @Override
    public List<Seance> lireParSalle(String salle) throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/selectBySalle.sql");
        List<Seance> seances = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, salle);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    seances.add(mapToSeance(rs));
                }
            }
        }
        return seances;
    }

    @Override
    public List<Seance> lireParHeureDebut(LocalTime heureDebut) throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/selectByHeureDebut.sql");
        List<Seance> seances = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTime(1, Time.valueOf(heureDebut));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    seances.add(mapToSeance(rs));
                }
            }
        }
        return seances;
    }

    @Override
    public List<Seance> lireParHeureFin(LocalTime heureFin) throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/selectByHeureFin.sql");
        List<Seance> seances = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setTime(1, Time.valueOf(heureFin));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    seances.add(mapToSeance(rs));
                }
            }
        }
        return seances;
    }

    @Override
    public Seance lireParId(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/selectById.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToSeance(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void modifier(Seance seance) throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/update.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, seance.getJour());
            pstmt.setTime(2, Time.valueOf(seance.getHeureDebut()));
            pstmt.setTime(3, Time.valueOf(seance.getHeureFin()));
            pstmt.setString(4, seance.getSalle());
            pstmt.setLong(5, seance.getMatiereId());
            pstmt.setLong(6, seance.getEnseignantId());
            pstmt.setLong(7, seance.getEmploiDuTempsId());
            pstmt.setLong(8, seance.getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void supprimer(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/seance/delete.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode utilitaire pour mapper ResultSet vers Seance
    private Seance mapToSeance(ResultSet rs) throws SQLException {
        Seance s = new Seance();
        s.setId(rs.getLong("id"));
        s.setJour(rs.getString("jour"));
        s.setHeureDebut(rs.getTime("heureDebut").toLocalTime());
        s.setHeureFin(rs.getTime("heureFin").toLocalTime());
        s.setSalle(rs.getString("salle"));
        s.setMatiereId(rs.getLong("matiereId"));
        s.setEnseignantId(rs.getLong("enseignantId"));
        s.setEmploiDuTempsId(rs.getLong("emploiDuTempsId"));
        return s;
    }
}