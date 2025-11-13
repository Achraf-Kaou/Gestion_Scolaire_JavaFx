DROP TABLE IF EXISTS enseignant;

CREATE TABLE enseignant (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    adresse VARCHAR(255),
    telephone VARCHAR(20),
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    birthDate DATE,
    photo BLOB,
    numeroEnseignant VARCHAR(50) UNIQUE NOT NULL,
    specialite VARCHAR(100),
    grade VARCHAR(50),
    dateRecrutement DATE,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME NULL
);