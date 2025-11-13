SELECT m.*
FROM matiere m
         JOIN seance s ON m.id = s.matiereId
WHERE s.id = ? AND m.deletedAt IS NULL;