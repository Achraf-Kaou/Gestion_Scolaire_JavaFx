SELECT *
FROM etudiant e
         JOIN classe c ON e.idClasse = c.id
WHERE c.niveauAnne = ?;
