SELECT e.*
FROM enseignant e
         JOIN seance s ON e.id = s.enseignantId
WHERE s.id = ? AND e.deletedAt IS NULL;