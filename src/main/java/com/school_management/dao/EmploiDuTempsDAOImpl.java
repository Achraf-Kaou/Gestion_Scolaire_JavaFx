package com.school_management.dao;

import com.school_management.model.EmploiDuTemps;
import com.school_management.config.DatabaseConfig;
import com.school_management.model.enums.Semestre;
import com.school_management.utils.SqlLoader;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmploiDuTempsDAOImpl implements EmploiDuTempsDAO {
    @Override
    public void ajouter(EmploiDuTemps emploiDuTemps) throws SQLException, IOException {
        String sql = SqlLoader.load("database/emploiDuTemps/insert.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setLong(1, emploiDuTemps.getClasseId());
            pstmt.setString(2, emploiDuTemps.getAnneeScolaire());
            pstmt.setString(3, emploiDuTemps.getSemestre().name());
            pstmt.setDate(4, emploiDuTemps.getDateDebut() != null ? Date.valueOf(emploiDuTemps.getDateDebut()) : null);
            pstmt.setDate(5, emploiDuTemps.getDateFin() != null ? Date.valueOf(emploiDuTemps.getDateFin()) : null);

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    emploiDuTemps.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    @Override
    public List<EmploiDuTemps> lireTous() throws SQLException, IOException {
        String sql = SqlLoader.load("database/emploiDuTemps/selectAll.sql");
        List<EmploiDuTemps> emploisDuTemps = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                emploisDuTemps.add(mapToEmploiDuTemps(rs));
            }
        }
        return emploisDuTemps;
    }

    @Override
    public EmploiDuTemps lireParClasse(Long classeId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/emploiDuTemps/selectByClasse.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, classeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToEmploiDuTemps(rs);
                }
            }
        }
        return null;
    }

    @Override
    public EmploiDuTemps lireParSeance(Long seanceId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/emploiDuTemps/selectBySeance.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, seanceId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToEmploiDuTemps(rs);
                }
            }
        }
        return null;
    }

    @Override
    public EmploiDuTemps lireParId(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/emploiDuTemps/selectById.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToEmploiDuTemps(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<EmploiDuTemps> lireParAnneeScolaire(String anneeScolaire) throws SQLException, IOException {
        String sql = SqlLoader.load("database/emploiDuTemps/selectByAnneeScolaire.sql");
        List<EmploiDuTemps> emploisDuTemps = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, anneeScolaire);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    emploisDuTemps.add(mapToEmploiDuTemps(rs));
                }
            }
        }
        return emploisDuTemps;
    }

    @Override
    public List<EmploiDuTemps> lireParSemestre(Semestre semestre) throws SQLException, IOException {
        String sql = SqlLoader.load("database/emploiDuTemps/selectBySemestre.sql");
        List<EmploiDuTemps> emploisDuTemps = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, semestre.name());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    emploisDuTemps.add(mapToEmploiDuTemps(rs));
                }
            }
        }
        return emploisDuTemps;
    }

    @Override
    public void modifier(EmploiDuTemps emploiDuTemps) throws SQLException, IOException {
        String sql = SqlLoader.load("database/emploiDuTemps/update.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, emploiDuTemps.getClasseId());
            pstmt.setString(2, emploiDuTemps.getAnneeScolaire());
            pstmt.setString(3, emploiDuTemps.getSemestre().name());
            pstmt.setDate(4, emploiDuTemps.getDateDebut() != null ? Date.valueOf(emploiDuTemps.getDateDebut()) : null);
            pstmt.setDate(5, emploiDuTemps.getDateFin() != null ? Date.valueOf(emploiDuTemps.getDateFin()) : null);
            pstmt.setLong(6, emploiDuTemps.getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void supprimer(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/emploiDuTemps/delete.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<EmploiDuTemps> lireParDateDebut(LocalDate dateDebut) throws SQLException, IOException {
        String sql = SqlLoader.load("database/emploiDuTemps/selectByDateDebut.sql");
        List<EmploiDuTemps> emploisDuTemps = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(dateDebut));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    emploisDuTemps.add(mapToEmploiDuTemps(rs));
                }
            }
        }
        return emploisDuTemps;
    }

    @Override
    public List<EmploiDuTemps> lireParDateFin(LocalDate dateFin) throws SQLException, IOException {
        String sql = SqlLoader.load("database/emploiDuTemps/selectByDateFin.sql");
        List<EmploiDuTemps> emploisDuTemps = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(dateFin));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    emploisDuTemps.add(mapToEmploiDuTemps(rs));
                }
            }
        }
        return emploisDuTemps;
    }

    @Override
    public List<EmploiDuTemps> lireParPeriode(LocalDate dateDebut, LocalDate dateFin) throws SQLException, IOException {
        String sql = SqlLoader.load("database/emploiDuTemps/selectByPeriode.sql");
        List<EmploiDuTemps> emploisDuTemps = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, Date.valueOf(dateDebut));
            pstmt.setDate(2, Date.valueOf(dateFin));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    emploisDuTemps.add(mapToEmploiDuTemps(rs));
                }
            }
        }
        return emploisDuTemps;
    }
        // Méthode utilitaire pour mapper ResultSet vers EmploiDuTemps
    private EmploiDuTemps mapToEmploiDuTemps(ResultSet rs) throws SQLException {
        EmploiDuTemps edt = new EmploiDuTemps();
        edt.setId(rs.getLong("id"));
        edt.setClasseId(rs.getLong("classeId"));
        edt.setAnneeScolaire(rs.getString("anneeScolaire"));
        edt.setSemestre(Semestre.valueOf(rs.getString("semestre")));
        edt.setDateDebut(rs.getDate("dateDebut") != null ? rs.getDate("dateDebut").toLocalDate() : null);
        edt.setDateFin(rs.getDate("dateFin") != null ? rs.getDate("dateFin").toLocalDate() : null);
        return edt;
    }
}
