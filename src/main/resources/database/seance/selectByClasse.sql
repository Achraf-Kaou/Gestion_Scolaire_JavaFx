SELECT s.*
FROM seance s
         JOIN emploiDuTemps edt ON s.emploiDuTempsId = edt.id
WHERE edt.classeId = ? AND s.deletedAt IS NULL;