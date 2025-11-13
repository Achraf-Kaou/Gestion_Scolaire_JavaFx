SELECT c.*
FROM classe c
         JOIN emploiDuTemps edt ON c.id = edt.classeId
         JOIN seance s ON edt.id = s.emploiDuTempsId
WHERE s.id = ? AND c.deletedAt IS NULL;