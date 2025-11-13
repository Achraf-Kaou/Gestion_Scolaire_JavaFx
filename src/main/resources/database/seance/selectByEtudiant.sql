SELECT s.*
FROM seance s
         JOIN emploiDuTemps edt ON s.emploiDuTempsId = edt.id
         JOIN etudiant e ON edt.classeId = e.classeId
WHERE e.id = ? AND s.deletedAt IS NULL;