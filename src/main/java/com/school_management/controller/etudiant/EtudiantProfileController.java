package com.school_management.controller.etudiant;

import com.school_management.model.Etudiant;
import com.school_management.service.EtudiantService;
import com.school_management.service.EtudiantServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EtudiantProfileController {

    @FXML
    private Label numeroLabel;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField adresseField;

    @FXML
    private Label classeLabel;

    @FXML
    private Label birthDateLabel;

    @FXML
    private Label dateInscriptionLabel;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    private Etudiant etudiant;
    private final EtudiantService etudiantService = new EtudiantServiceImpl();

    @FXML
    public void initialize() {
        // Initially, fields are not editable
        setFieldsEditable(false);

        btnEdit.setOnAction(e -> enableEditing());
        btnSave.setOnAction(e -> saveProfile());
        btnCancel.setOnAction(e -> cancelEditing());
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
        if (etudiant != null) {
            numeroLabel.setText(etudiant.getNumeroEtudiant() != null ? etudiant.getNumeroEtudiant() : "N/A");
            nomField.setText(etudiant.getNom() != null ? etudiant.getNom() : "");
            prenomField.setText(etudiant.getPrenom() != null ? etudiant.getPrenom() : "");
            emailField.setText(etudiant.getEmail() != null ? etudiant.getEmail() : "");
            telephoneField.setText(etudiant.getTelephone() != null ? etudiant.getTelephone() : "");
            adresseField.setText(etudiant.getAdresse() != null ? etudiant.getAdresse() : "");
            
            if (etudiant.getClasse() != null) {
                classeLabel.setText(etudiant.getClasse().getCode() + " - " + etudiant.getClasse().getNom());
            } else {
                classeLabel.setText("N/A");
            }
            
            birthDateLabel.setText(etudiant.getBirthDate() != null ? etudiant.getBirthDate().toString() : "N/A");
            dateInscriptionLabel.setText(etudiant.getDateInscription() != null ? etudiant.getDateInscription().toString() : "N/A");
        }
    }

    private void enableEditing() {
        setFieldsEditable(true);
        btnEdit.setVisible(false);
        btnSave.setVisible(true);
        btnCancel.setVisible(true);
    }

    private void cancelEditing() {
        setFieldsEditable(false);
        setEtudiant(etudiant); // Reload original data
        btnEdit.setVisible(true);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);
    }

    private void saveProfile() {
        if (etudiant == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Aucun étudiant sélectionné.");
            return;
        }

        // Validate fields
        if (nomField.getText().trim().isEmpty() || prenomField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom, prénom et email sont obligatoires.");
            return;
        }

        try {
            // Update etudiant object
            etudiant.setNom(nomField.getText().trim());
            etudiant.setPrenom(prenomField.getText().trim());
            etudiant.setEmail(emailField.getText().trim());
            etudiant.setTelephone(telephoneField.getText().trim());
            etudiant.setAdresse(adresseField.getText().trim());

            // Save to database
            etudiantService.modifier(etudiant);

            showAlert(Alert.AlertType.INFORMATION, "Succès", "Profil mis à jour avec succès.");
            setFieldsEditable(false);
            btnEdit.setVisible(true);
            btnSave.setVisible(false);
            btnCancel.setVisible(false);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de mettre à jour le profil : " + e.getMessage());
        }
    }

    private void setFieldsEditable(boolean editable) {
        nomField.setEditable(editable);
        prenomField.setEditable(editable);
        emailField.setEditable(editable);
        telephoneField.setEditable(editable);
        adresseField.setEditable(editable);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Profil Étudiant");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
