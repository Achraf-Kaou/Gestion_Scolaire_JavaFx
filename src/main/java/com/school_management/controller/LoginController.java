package com.school_management.controller;

import com.school_management.controller.enseignant.DashboardEnseignantController;
import com.school_management.controller.etudiant.DashboardEtudiantController;
import com.school_management.exception.EmailNotFoundException;
import com.school_management.exception.IncorrectPasswordException;
import com.school_management.model.Enseignant;
import com.school_management.model.Etudiant;
import com.school_management.service.EnseignantServiceImpl;
import com.school_management.service.EtudiantServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField etudiantEmailField;

    @FXML
    private PasswordField etudiantPasswordField;

    @FXML
    private TextField enseignantEmailField;

    @FXML
    private PasswordField enseignantPasswordField;

    @FXML
    private Button btnEtudiantLogin;

    @FXML
    private Button btnEnseignantLogin;

    @FXML
    private TabPane tabPane;


    private final EtudiantServiceImpl etudiantService = new EtudiantServiceImpl();
    private final EnseignantServiceImpl enseignantService = new EnseignantServiceImpl();
    @FXML
    public void initialize() {

        btnEtudiantLogin.setOnAction(event -> {
            String email = etudiantEmailField.getText();
            String password = etudiantPasswordField.getText();

            try {
                Etudiant etu = etudiantService.seConnecter(email, password);
                openEtudiantDashboard(etu);
            } catch (EmailNotFoundException e) {
                showError("Email incorrect", e.getMessage());
            } catch (IncorrectPasswordException e) {
                showError("Mot de passe incorrect", e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                showError("Erreur système", "Une erreur est survenue.");
            }
        });

        // Action login enseignant
        btnEnseignantLogin.setOnAction(event -> {
            String email = enseignantEmailField.getText();
            String password = enseignantPasswordField.getText();

            try {
                if (email.equals("admin") &&  password.equals("admin")) {
                    this.openView("Admin/DashboardAdmin.fxml");
                    return;
                }
                Enseignant ens = enseignantService.seConnecter(email, password);
                openEnseignantDashboard(ens);
            } catch (EmailNotFoundException e) {
                showError("Email incorrect", e.getMessage());
            } catch (IncorrectPasswordException e) {
                showError("Mot de passe incorrect", e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                showError("Erreur système", "Une erreur est survenue.");
            }
        });
    }

    // Alert erreur
    private void showError(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(msg);
        alert.show();
    }

    // Alert succès
    private void showInfo(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(title);
        alert.setContentText(msg);
        alert.show();
    }

    private void openView(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/school_management/" + fxml));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) tabPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openEnseignantDashboard(Enseignant enseignant) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/school_management/Enseignant/DashboardEnseignant.fxml"));
            Scene scene = new Scene(loader.load());
            
            DashboardEnseignantController controller = loader.getController();
            controller.setEnseignant(enseignant);
            
            Stage stage = (Stage) tabPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Erreur", "Impossible d'ouvrir le dashboard enseignant");
        }
    }

    private void openEtudiantDashboard(Etudiant etudiant) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/school_management/Etudiant/DashboardEtudiant.fxml"));
            Scene scene = new Scene(loader.load());
            
            DashboardEtudiantController controller = loader.getController();
            controller.setEtudiant(etudiant);
            
            Stage stage = (Stage) tabPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Erreur", "Impossible d'ouvrir le dashboard étudiant");
        }
    }

}
