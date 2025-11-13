-- TABLE MATIERE
CREATE TABLE matiere (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(20) UNIQUE NOT NULL,
    nom VARCHAR(100) NOT NULL,
    nbHeuresCours INT DEFAULT 0,
    nbHeuresTd INT DEFAULT 0,
    nbHeuresTp INT DEFAULT 0,
    nbCredits INT DEFAULT 0,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME NULL
);

-- Association many-to-many SPECIALITE<->MATIERE
CREATE TABLE specialiteMatiere (
    specialite_id BIGINT NOT NULL,
    matiere_id BIGINT NOT NULL,
    PRIMARY KEY (specialite_id, matiere_id),
    FOREIGN KEY (specialite_id) REFERENCES specialite(id),
    FOREIGN KEY (matiere_id) REFERENCES matiere(id)
);