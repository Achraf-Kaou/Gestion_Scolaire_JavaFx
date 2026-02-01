# Guide d'Utilisation - Système de Gestion Scolaire

## Fonctionnalités Implémentées

### 1. Gestion des Séances

La gestion des séances permet de créer, modifier et supprimer des séances de cours pour les emplois du temps.

**Comment accéder:**
- Cliquez sur le bouton "Gestion Séances" dans le menu latéral du tableau de bord

**Fonctionnalités:**
- Ajouter une nouvelle séance avec:
  - Jour de la semaine
  - Heure de début et de fin (format HH:mm, ex: 08:00)
  - Salle
  - Matière
  - Enseignant
  - Emploi du temps associé
- Modifier une séance existante
- Supprimer une séance
- Visualiser toutes les séances dans un tableau

### 2. Affichage de l'Emploi du Temps

L'affichage de l'emploi du temps présente une vue visuelle sous forme de grille calendrier.

**Comment accéder:**
- Depuis "Gestion Emploi du Temps", cliquez sur le bouton "Voir l'emploi du temps"
- Une nouvelle fenêtre s'ouvre avec la vue calendrier

**Fonctionnalités:**
- Sélectionner un emploi du temps par classe
- Visualiser les séances organisées par jour et heure
- Voir les détails de chaque séance (matière, enseignant, salle, horaires)
- Format de grille facile à lire avec code couleur

### 3. Vue Statistiques

La vue statistiques affiche des données analytiques sur l'ensemble du système.

**Comment accéder:**
- Cliquez sur "Statistiques" dans le menu latéral

**Informations affichées:**
- Nombre total d'étudiants, enseignants, classes, matières, et séances
- Graphique circulaire: Distribution des étudiants par classe
- Graphique à barres: Nombre de séances par matière
- Toutes les statistiques sont calculées en temps réel

### 4. Tableau de Bord Amélioré

Le tableau de bord affiche maintenant une vue d'ensemble du système.

**Fonctionnalités:**
- Cartes colorées avec statistiques clés:
  - Étudiants (bleu)
  - Enseignants (vert)
  - Classes (rouge)
  - Matières (orange)
  - Spécialités (violet)
  - Séances (turquoise)
  - Emplois du temps (gris foncé)
- Vue par défaut lors de l'ouverture du tableau de bord

### 5. Assigner une Matière à une Spécialité

L'association entre matières et spécialités est déjà implémentée dans la gestion des spécialités.

**Comment assigner des matières à une spécialité:**

1. **Accéder à la gestion des spécialités:**
   - Cliquez sur "Gestion spécialités" dans le menu latéral

2. **Pour créer une nouvelle spécialité avec des matières:**
   - Dans le tableau "Matières associées" (en haut), sélectionnez une ou plusieurs matières
     - **Astuce:** Utilisez Ctrl+Clic pour sélectionner plusieurs matières
   - Remplissez les champs "Code" et "Nom" de la spécialité
   - Cliquez sur "Ajouter"
   - La spécialité sera créée avec les matières sélectionnées

3. **Pour modifier les matières d'une spécialité existante:**
   - Sélectionnez la spécialité dans le tableau du bas
   - Les matières déjà associées seront automatiquement sélectionnées dans le tableau du haut
   - Modifiez la sélection des matières (ajoutez ou retirez des matières)
   - Cliquez sur "Modifier"
   - Les associations seront mises à jour

4. **Pour retirer toutes les matières d'une spécialité:**
   - Sélectionnez la spécialité
   - Désélectionnez toutes les matières dans le tableau du haut
   - Cliquez sur "Modifier"

**Note importante:** Les matières doivent d'abord être créées dans "Gestion matieres" avant de pouvoir être associées à une spécialité.

## Architecture du Système

Le système suit une architecture en couches:
- **Model:** Classes de domaine (Seance, EmploiDuTemps, Matiere, Specialite, etc.)
- **DAO:** Couche d'accès aux données (interaction avec MySQL)
- **Service:** Logique métier
- **Controller:** Contrôleurs JavaFX pour les vues
- **FXML:** Vues de l'interface utilisateur

## Technologies Utilisées

- JavaFX 21 pour l'interface graphique
- MySQL pour la base de données
- Maven pour la gestion des dépendances
- Java 17

## Prochaines Étapes Possibles

- Export PDF des emplois du temps
- Notifications pour les conflits d'horaires
- Gestion des absences
- Notes et évaluations
- Interface pour les étudiants et enseignants
