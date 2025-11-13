UPDATE classe SET
    code = ?,
    nom = ?,
    capaciteMax = ?,
    anneeScolaire = ?,
    typeDiplome = ?,
    niveauAnnee = ?,
    specialiteId = ?,
    updatedAt = NOW()
WHERE id = ?;