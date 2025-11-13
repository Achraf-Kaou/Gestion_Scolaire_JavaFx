SELECT edt.*
FROM emploiDuTemps edt
         JOIN seance s ON edt.id = s.emploiDuTempsId
WHERE s.id = ? AND edt.deletedAt IS NULL;