# Gestion Scolaire JavaFX

Application JavaFX de gestion scolaire (étudiants, enseignants, classes, matières, emplois du temps et séances).

## Prérequis

- Java 17
- Maven (ou le wrapper Maven fourni)
- MySQL 8+

## Configuration de la base de données

La connexion est définie dans `/tmp/workspace/Achraf-Kaou/Gestion_Scolaire_JavaFx/src/main/java/com/school_management/config/DatabaseConfig.java` :

- URL : `jdbc:mysql://localhost:3306/gestion_scolaire`
- Utilisateur : `root`
- Mot de passe : `root`

Créez la base puis exécutez le script SQL :

- `/tmp/workspace/Achraf-Kaou/Gestion_Scolaire_JavaFx/src/main/resources/database/createTables.sql`

## Exécution

Depuis `/tmp/workspace/Achraf-Kaou/Gestion_Scolaire_JavaFx` :

```bash
bash mvnw test
bash mvnw javafx:run
```

## Documentation

- Documentation technique : `/tmp/workspace/Achraf-Kaou/Gestion_Scolaire_JavaFx/DOCUMENTATION.md`
- Guide utilisateur : `/tmp/workspace/Achraf-Kaou/Gestion_Scolaire_JavaFx/GUIDE_UTILISATION.md`
