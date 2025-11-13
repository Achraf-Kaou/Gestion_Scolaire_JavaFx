SELECT m.*
FROM matiere m
         JOIN specialiteMatiere sm ON m.id = sm.matiere_id
WHERE sm.specialite_id = ? AND m.deletedAt IS NULL;