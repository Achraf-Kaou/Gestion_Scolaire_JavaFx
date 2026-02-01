package com.school_management.controller.enseignant;

import com.school_management.model.Enseignant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardEnseignantController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnSchedule;

    @FXML
    private Button btnStudents;

    @FXML
    private VBox contentArea;

    private Enseignant enseignant;

    @FXML
    public void initialize() {
        btnLogout.setOnAction(e -> handleLogout());
        btnProfile.setOnAction(e -> showProfile());
        btnSchedule.setOnAction(e -> showSchedule());
        btnStudents.setOnAction(e -> showStudents());
        
        // Show profile by default
        showProfile();
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
        if (enseignant != null) {
            welcomeLabel.setText("Bienvenue " + enseignant.getPrenom() + " " + enseignant.getNom());
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

    private void showProfile() {
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Enseignant/ProfileView.fxml");
    }

    private void showSchedule() {
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Enseignant/ScheduleView.fxml");
    }

    private void showStudents() {
        contentArea.getChildren().clear();
        loadViewInContent("/com/school_management/Enseignant/StudentsView.fxml");
    }

    private void loadViewInContent(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            
            // Pass enseignant to the loaded controller
            Object controller = loader.getController();
            if (controller instanceof ProfileController) {
                ((ProfileController) controller).setEnseignant(enseignant);
            } else if (controller instanceof ScheduleController) {
                ((ScheduleController) controller).setEnseignant(enseignant);
            } else if (controller instanceof StudentsController) {
                ((StudentsController) controller).setEnseignant(enseignant);
            }
            
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
