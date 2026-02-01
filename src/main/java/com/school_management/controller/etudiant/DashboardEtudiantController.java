package com.school_management.controller.etudiant;

import com.school_management.model.Etudiant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardEtudiantController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnSchedule;

    @FXML
    private Button btnClass;

    @FXML
    private VBox contentArea;

    private Etudiant etudiant;

    @FXML
    public void initialize() {
        btnLogout.setOnAction(e -> handleLogout());
        btnSchedule.setOnAction(e -> showSchedule());
        btnClass.setOnAction(e -> showClass());
        
        // Show schedule by default
        showSchedule();
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
        if (etudiant != null) {
            welcomeLabel.setText("Bienvenue " + etudiant.getPrenom() + " " + etudiant.getNom());
        }
    }

    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/school_management/LoginView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSchedule() {
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Etudiant/ScheduleView.fxml");
    }

    private void showClass() {
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Etudiant/ClassView.fxml");
    }

    private void loadViewInContent(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            
            // Pass etudiant to the loaded controller
            Object controller = loader.getController();
            if (controller instanceof EtudiantScheduleController) {
                ((EtudiantScheduleController) controller).setEtudiant(etudiant);
            } else if (controller instanceof ClassViewController) {
                ((ClassViewController) controller).setEtudiant(etudiant);
            }
            
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
