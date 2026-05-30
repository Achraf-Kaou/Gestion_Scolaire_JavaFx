# Gestion Scolaire JavaFX

Application JavaFX de gestion scolaire (étudiants, enseignants, classes, matières, emplois du temps et séances).

## Prérequis

- Java 17
- Maven (ou le wrapper Maven fourni)
- MySQL 8+

## Configuration de la base de données

La connexion est définie dans `src/main/java/com/school_management/config/DatabaseConfig.java` :

- URL : `jdbc:mysql://localhost:3306/gestion_scolaire`
- Utilisateur : `root`
- Mot de passe : `root`

> ⚠️ Ces identifiants sont des valeurs de développement local.  
> Créez de préférence un utilisateur MySQL dédié (ex: `school_app`) avec des droits limités.
> Pour un déploiement réel, externalisez ces paramètres (variables d'environnement ou fichier de configuration sécurisé).

Créez la base puis exécutez le script SQL :

- `src/main/resources/database/createTables.sql`

## Exécution

Depuis la racine du projet :

```bash
# Exécuter la suite de tests
./mvnw test

# Lancer l'application JavaFX
./mvnw javafx:run
```

## Documentation

- Documentation technique : `DOCUMENTATION.md`
