SELECT c.*
FROM classe c
         JOIN etudiant e ON c.id = e.classeId
WHERE e.id = ? AND c.deletedAt IS NULL;