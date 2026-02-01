package com.school_management.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface SpecialiteMatiereDAO {
    void associer(Long matiereId, Long specialiteId) throws SQLException, IOException;
    void supprimerAssociationsMatiere(Long matiereId) throws SQLException, IOException;
    void supprimerAssociationsSpecialite(Long specialiteId) throws SQLException, IOException;
    List<Long> getSpecialiteIdsParMatiere(Long matiereId) throws SQLException, IOException;
    List<Long> getMatiereIdsParSpecialite(Long specialiteId) throws SQLException, IOException;
}
