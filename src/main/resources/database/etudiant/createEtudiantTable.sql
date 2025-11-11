DROP TABLE IF EXISTS etudiant;

CREATE TABLE etudiant (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    adresse VARCHAR(255),
    telephone VARCHAR(20),
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    birthDate DATE,
    photo BLOB,
    numeroEtudiant VARCHAR(50) UNIQUE NOT NULL,
    dateInscription Date NOT NULL,
    classeId BIGINT NOT NULL,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME NULL,
    FOREIGN KEY (classeId) REFERENCES classe(id)
    );