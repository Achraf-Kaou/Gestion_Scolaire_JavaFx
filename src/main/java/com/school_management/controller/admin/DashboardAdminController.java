package com.school_management.controller.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardAdminController {

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnGestionSpecialites;

    @FXML
    private Button btnGestionMatieres;

    @FXML
    private Button btnGestionEtudiants;

    @FXML
    private Button btnGestionEnseignants;

    @FXML
    private Button btnGestionClasses;

    @FXML
    private Button btnGestionEmploiDuTemps;

    @FXML
    private Button btnGestionSeances;

    @FXML
    private Button btnStatistiques;

    @FXML
    private VBox contentArea;

    @FXML
    public void initialize() {
        btnLogout.setOnAction(e -> handleLogout());
        btnGestionSpecialites.setOnAction(e -> showGestionSpecialites());
        btnGestionMatieres.setOnAction(e -> showGestionMatieres());
        btnGestionEtudiants.setOnAction(e -> showGestionEtudiants());
        btnGestionEnseignants.setOnAction(e -> showGestionEnseignants());
        btnGestionClasses.setOnAction(e -> showGestionClasses());
        btnGestionEmploiDuTemps.setOnAction(e -> showGestionEmploiDuTemps());
        btnGestionSeances.setOnAction(e -> showGestionSeances());
        btnStatistiques.setOnAction(e -> showStatistiques());
        
        // Show dashboard summary by default
        showDashboardSummary();
    }

    private void handleLogout() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/com/school_management/LoginView.fxml"));
            javafx.scene.Scene scene = new javafx.scene.Scene(loader.load());
            javafx.stage.Stage stage = (javafx.stage.Stage) btnLogout.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void showGestionMatieres(){
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Admin/MatiereView.fxml");

    }

    private void showGestionEtudiants() {
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Admin/EtudiantView.fxml");
    }

    private void showGestionEnseignants() {
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Admin/EnseignantView.fxml");
    }

    private void showGestionClasses() {
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Admin/ClasseView.fxml");
    }

    private void showGestionEmploiDuTemps() {
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Admin/EmploiDuTempsView.fxml");
    }

    private void showGestionSeances() {
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Admin/SeanceView.fxml");
    }

    private void showStatistiques() {
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Admin/StatistiqueView.fxml");
    }

    private void showGestionSpecialites() {
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Admin/SpecialiteView.fxml");
    }

    private void showDashboardSummary() {
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Admin/DashboardSummaryView.fxml");
    }

    private void loadViewInContent(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
