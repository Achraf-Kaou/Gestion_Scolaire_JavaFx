SELECT *
FROM etudiant e
    JOIN classe c ON e.idClasse = c.id
    JOIN specialite s On c.idSpecialite = ?
WHERE c.TypeDiplome = ?;
