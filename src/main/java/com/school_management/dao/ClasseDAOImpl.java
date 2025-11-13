package com.school_management.dao;

import com.school_management.model.Classe;
import com.school_management.config.DatabaseConfig;
import com.school_management.model.enums.NiveauAnnee;
import com.school_management.model.enums.TypeDiplome;
import com.school_management.utils.SqlLoader;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClasseDAOImpl implements ClasseDAO {
    @Override
    public void ajouter(Classe classe) throws SQLException, IOException {
        String sql = SqlLoader.load("database/classe/insert.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, classe.getCode());
            pstmt.setString(2, classe.getNom());
            pstmt.setInt(3, classe.getCapaciteMax());
            pstmt.setString(4, classe.getAnneeScolaire());
            pstmt.setString(5, classe.getTypeDiplome().name());
            pstmt.setString(6, classe.getNiveauAnnee().name());
            pstmt.setLong(7, classe.getSpecialiteId());

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    classe.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    @Override
    public List<Classe> lireTous() throws SQLException, IOException {
        String sql = SqlLoader.load("database/classe/selectAll.sql");
        List<Classe> classes = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                classes.add(mapToClasse(rs));
            }
        }
        return classes;
    }

    @Override
    public Classe lireParEtudiant(Long etudiantId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/classe/selectByEtudiant.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, etudiantId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToClasse(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Classe> lireParSpecialite(Long specialiteId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/classe/selectBySpecialite.sql");
        List<Classe> classes = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, specialiteId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    classes.add(mapToClasse(rs));
                }
            }
        }
        return classes;
    }

    @Override
    public Classe lireParEmploiDuTemps(Long emploiDuTempsId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/classe/selectByEmploiDuTemps.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, emploiDuTempsId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToClasse(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Classe lireParSeance(Long seanceId) throws SQLException, IOException {
        String sql = SqlLoader.load("database/classe/selectBySeance.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, seanceId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToClasse(rs);
                }
            }
        }
        return null;
    }

    @Override
    public Classe lireParId(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/classe/selectById.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapToClasse(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Classe> lireParAnneeScolaire(String anneeScolaire) throws SQLException, IOException {
        String sql = SqlLoader.load("database/classe/selectByAnneeScolaire.sql");
        List<Classe> classes = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, anneeScolaire);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    classes.add(mapToClasse(rs));
                }
            }
        }
        return classes;
    }

    @Override
    public List<Classe> lireParTypeDiplome(TypeDiplome typeDiplome) throws SQLException, IOException {
        String sql = SqlLoader.load("database/classe/selectByTypeDiplome.sql");
        List<Classe> classes = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, typeDiplome.name());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    classes.add(mapToClasse(rs));
                }
            }
        }
        return classes;
    }

    @Override
    public List<Classe> lireParNiveauAnnee(NiveauAnnee niveauAnnee) throws SQLException, IOException {
        String sql = SqlLoader.load("database/classe/selectByNiveauAnnee.sql");
        List<Classe> classes = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, niveauAnnee.name());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    classes.add(mapToClasse(rs));
                }
            }
        }
        return classes;
    }

    @Override
    public void modifier(Classe classe) throws SQLException, IOException {
        String sql = SqlLoader.load("database/classe/update.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, classe.getCode());
            pstmt.setString(2, classe.getNom());
            pstmt.setInt(3, classe.getCapaciteMax());
            pstmt.setString(4, classe.getAnneeScolaire());
            pstmt.setString(5, classe.getTypeDiplome().name());
            pstmt.setString(6, classe.getNiveauAnnee().name());
            pstmt.setLong(7, classe.getSpecialiteId());
            pstmt.setLong(8, classe.getId());

            pstmt.executeUpdate();
        }
    }

    @Override
    public void supprimer(Long id) throws SQLException, IOException {
        String sql = SqlLoader.load("database/classe/delete.sql");

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode utilitaire pour mapper ResultSet vers Classe
    private Classe mapToClasse(ResultSet rs) throws SQLException {
        Classe c = new Classe();
        c.setId(rs.getLong("id"));
        c.setCode(rs.getString("code"));
        c.setNom(rs.getString("nom"));
        c.setCapaciteMax(rs.getInt("capaciteMax"));
        c.setAnneeScolaire(rs.getString("anneeScolaire"));
        c.setTypeDiplome(TypeDiplome.valueOf(rs.getString("typeDiplome")));
        c.setNiveauAnnee(NiveauAnnee.valueOf(rs.getString("niveauAnnee")));
        c.setSpecialiteId(rs.getLong("specialiteId"));
        return c;
    }

}
