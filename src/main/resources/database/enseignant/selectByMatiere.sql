SELECT e.*
FROM enseignant e
         JOIN enseignantMatiere em ON e.id = em.enseignantId
WHERE em.matiereId = ? AND e.deletedAt IS NULL;