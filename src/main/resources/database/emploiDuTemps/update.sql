UPDATE emploiDuTemps SET
     classeId = ?,
     anneeScolaire = ?,
     semestre = ?,
     dateDebut = ?,
     dateFin = ?,
     updatedAt = NOW()
WHERE id = ?;