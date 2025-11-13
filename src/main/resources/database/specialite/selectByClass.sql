SELECT s.*
FROM specialite s
         JOIN classe c ON s.id = c.specialiteId
WHERE c.id = ? AND s.deletedAt IS NULL;