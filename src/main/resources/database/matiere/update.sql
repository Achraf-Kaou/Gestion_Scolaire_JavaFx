UPDATE matiere SET
    code = ?,
    nom = ?,
    nbHeuresCours = ?,
    nbHeuresTd = ?,
    nbHeuresTp = ?,
    nbCredits = ?,
    updatedAt = NOW()
WHERE id = ?;