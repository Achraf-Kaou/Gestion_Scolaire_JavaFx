SELECT s.*
FROM specialite s
         JOIN specialiteMatiere sm ON s.id = sm.specialite_id
WHERE sm.matiere_id = ? AND s.deletedAt IS NULL;