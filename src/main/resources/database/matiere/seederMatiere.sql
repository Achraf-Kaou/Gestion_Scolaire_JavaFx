-- Exemples de matières associées
INSERT INTO matiere (code, nom, nbHeuresCours, nbHeuresTd, nbHeuresTp, nbCredits) VALUES
    ('ALG101', 'Algorithmique', 30, 20, 10, 5),
    ('BDD201', 'Bases de données', 25, 15, 20, 5),
    ('WEB301', 'Développement Web', 20, 10, 30, 6);

-- Association spécialité-matière
INSERT INTO specialiteMatiere (specialite_id, matiere_id) VALUES
    (1, 1), -- Informatique contient Algorithmique
    (1, 2), -- Informatique contient Bases de données
    (1, 3), -- Informatique contient Développement Web
    (4, 2), -- Génie Logiciel contient Bases de données
    (4, 3); -- Génie Logiciel contient Développement Web