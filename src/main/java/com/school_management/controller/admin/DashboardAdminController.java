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
        btnStatistiques.setOnAction(e -> showStatistiques());
    }

    private void handleLogout() {
        System.out.println("Déconnexion");
        // Implémente la logique de déconnexion (retour à la page login, nettoyage session, etc)
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

    private void showStatistiques() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Label("Interface statistiques (à implémenter)"));
    }

    private void showGestionSpecialites() {
        loadViewInContent("/com/school_management/Admin/SpecialiteView.fxml");
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
