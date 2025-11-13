SELECT c.*
FROM classe c
         JOIN emploiDuTemps edt ON c.id = edt.classeId
WHERE edt.id = ? AND c.deletedAt IS NULL;