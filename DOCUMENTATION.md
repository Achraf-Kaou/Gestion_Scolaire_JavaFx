# Documentation Technique

## Architecture

Le projet suit une architecture en couches :

- `model` : entités métier
- `dao` : accès aux données SQL
- `service` : logique métier
- `controller` : contrôleurs JavaFX
- `resources` : vues FXML, styles CSS, scripts SQL

## Fonctionnalités principales

- Authentification par email/mot de passe
- Gestion CRUD des étudiants
- Gestion CRUD des enseignants
- Gestion CRUD des classes
- Gestion CRUD des matières
- Gestion CRUD des spécialités
- Gestion des emplois du temps
- Gestion des séances
- Tableau de bord et statistiques

## Structure du projet

- Code Java : `/tmp/workspace/Achraf-Kaou/Gestion_Scolaire_JavaFx/src/main/java/com/school_management`
- Vues JavaFX : `/tmp/workspace/Achraf-Kaou/Gestion_Scolaire_JavaFx/src/main/resources/com/school_management`
- Scripts SQL : `/tmp/workspace/Achraf-Kaou/Gestion_Scolaire_JavaFx/src/main/resources/database`

## Build et tests

Commandes depuis la racine du projet :

```bash
bash mvnw test
bash mvnw javafx:run
```
