SELECT * FROM emploiDuTemps
WHERE dateDebut >= ? AND dateFin <= ?
  AND deletedAt IS NULL;