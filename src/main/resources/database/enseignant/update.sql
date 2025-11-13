UPDATE enseignant SET
                      nom = ?,
                      prenom = ?,
                      adresse = ?,
                      telephone = ?,
                      email = ?,
                      password = ?,
                      birthDate = ?,
                      photo = ?,
                      numeroEnseignant = ?,
                      specialite = ?,
                      grade = ?,
                      dateRecrutement = ?,
                      updatedAt = NOW()
WHERE id = ?;