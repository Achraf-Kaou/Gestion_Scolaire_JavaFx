UPDATE etudiant SET
                    nom = ?,
                    prenom = ?,
                    adresse = ?,
                    telephone = ?,
                    email = ?,
                    password = ?,
                    birthDate = ?,
                    photo = ?,
                    numeroEtudiant = ?,
                    dateInscription = ?,
                    classeId = ?,
                    updatedAt = NOW()
WHERE id = ?;
