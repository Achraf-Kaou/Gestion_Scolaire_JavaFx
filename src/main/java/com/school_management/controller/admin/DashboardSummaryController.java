package com.school_management.controller.admin;

import com.school_management.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardSummaryController {

    @FXML private Label lblNbEtudiants;
    @FXML private Label lblNbEnseignants;
    @FXML private Label lblNbClasses;
    @FXML private Label lblNbMatieres;
    @FXML private Label lblNbSpecialites;
    @FXML private Label lblNbSeances;
    @FXML private Label lblNbEmploisDuTemps;

    private final EtudiantService etudiantService = new EtudiantServiceImpl();
    private final EnseignantService enseignantService = new EnseignantServiceImpl();
    private final ClasseService classeService = new ClasseServiceImpl();
    private final MatiereService matiereService = new MatiereServiceImpl();
    private final SpecialiteService specialiteService = new SpecialiteServiceImpl();
    private final SeanceService seanceService = new SeanceServiceImpl();
    private final EmploiDuTempsService emploiDuTempsService = new EmploiDuTempsServiceImpl();

    @FXML
    public void initialize() {
        loadStatistics();
    }

    private void loadStatistics() {
        try {
            int nbEtudiants = etudiantService.lireTous().size();
            lblNbEtudiants.setText(String.valueOf(nbEtudiants));
        } catch (Exception e) {
            lblNbEtudiants.setText("Erreur");
        }

        try {
            int nbEnseignants = enseignantService.lireTous().size();
            lblNbEnseignants.setText(String.valueOf(nbEnseignants));
        } catch (Exception e) {
            lblNbEnseignants.setText("Erreur");
        }

        try {
            int nbClasses = classeService.lireTous().size();
            lblNbClasses.setText(String.valueOf(nbClasses));
        } catch (Exception e) {
            lblNbClasses.setText("Erreur");
        }

        try {
            int nbMatieres = matiereService.listeMatieres().size();
            lblNbMatieres.setText(String.valueOf(nbMatieres));
        } catch (Exception e) {
            lblNbMatieres.setText("Erreur");
        }

        try {
            int nbSpecialites = specialiteService.listeSpecialites().size();
            lblNbSpecialites.setText(String.valueOf(nbSpecialites));
        } catch (Exception e) {
            lblNbSpecialites.setText("Erreur");
        }

        try {
            int nbSeances = seanceService.lireTous().size();
            lblNbSeances.setText(String.valueOf(nbSeances));
        } catch (Exception e) {
            lblNbSeances.setText("Erreur");
        }

        try {
            int nbEmploisDuTemps = emploiDuTempsService.lireTous().size();
            lblNbEmploisDuTemps.setText(String.valueOf(nbEmploisDuTemps));
        } catch (Exception e) {
            lblNbEmploisDuTemps.setText("Erreur");
        }
    }
}
