INSERT INTO matiere (code, nom, nbHeuresCours, nbHeuresTd, nbHeuresTp, nbCredits)
VALUES (?, ?, ?, ?, ?, ?)
ON DUPLICATE KEY UPDATE 
    nom = VALUES(nom),
    nbHeuresCours = VALUES(nbHeuresCours),
    nbHeuresTd = VALUES(nbHeuresTd),
    nbHeuresTp = VALUES(nbHeuresTp),
    nbCredits = VALUES(nbCredits),
    deletedAt = NULL,
    updatedAt = CURRENT_TIMESTAMP;