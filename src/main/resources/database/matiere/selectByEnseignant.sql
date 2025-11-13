SELECT m.*
FROM matiere m
         JOIN enseignantMatiere em ON m.id = em.matiereId
WHERE em.enseignantId = ? AND m.deletedAt IS NULL;