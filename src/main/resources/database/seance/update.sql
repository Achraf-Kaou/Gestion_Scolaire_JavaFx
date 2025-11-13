UPDATE seance SET
                  jour = ?,
                  heureDebut = ?,
                  heureFin = ?,
                  salle = ?,
                  matiereId = ?,
                  enseignantId = ?,
                  emploiDuTempsId = ?,
                  updatedAt = NOW()
WHERE id = ?;