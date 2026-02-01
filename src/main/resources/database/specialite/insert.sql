INSERT INTO specialite (code, nom)
VALUES (?, ?)
ON DUPLICATE KEY UPDATE 
    nom = VALUES(nom),
    deletedAt = NULL,
    updatedAt = CURRENT_TIMESTAMP;