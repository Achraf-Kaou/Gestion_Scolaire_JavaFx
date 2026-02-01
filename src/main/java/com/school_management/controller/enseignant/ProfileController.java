package com.school_management.controller.enseignant;

import com.school_management.model.Enseignant;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfileController {

    @FXML
    private Label numeroLabel;

    @FXML
    private Label nomLabel;

    @FXML
    private Label prenomLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label telephoneLabel;

    @FXML
    private Label adresseLabel;

    @FXML
    private Label specialiteLabel;

    @FXML
    private Label gradeLabel;

    @FXML
    private Label dateRecrutementLabel;

    public void setEnseignant(Enseignant enseignant) {
        if (enseignant != null) {
            numeroLabel.setText(enseignant.getNumeroEnseignant() != null ? enseignant.getNumeroEnseignant() : "N/A");
            nomLabel.setText(enseignant.getNom() != null ? enseignant.getNom() : "N/A");
            prenomLabel.setText(enseignant.getPrenom() != null ? enseignant.getPrenom() : "N/A");
            emailLabel.setText(enseignant.getEmail() != null ? enseignant.getEmail() : "N/A");
            telephoneLabel.setText(enseignant.getTelephone() != null ? enseignant.getTelephone() : "N/A");
            adresseLabel.setText(enseignant.getAdresse() != null ? enseignant.getAdresse() : "N/A");
            specialiteLabel.setText(enseignant.getSpecialite() != null ? enseignant.getSpecialite() : "N/A");
            gradeLabel.setText(enseignant.getGrade() != null ? enseignant.getGrade() : "N/A");
            dateRecrutementLabel.setText(enseignant.getDateRecrutement() != null ? enseignant.getDateRecrutement().toString() : "N/A");
        }
    }
}
