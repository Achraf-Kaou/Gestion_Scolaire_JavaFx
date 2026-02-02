package com.school_management.controller.admin;

import com.school_management.model.Enseignant;
import com.school_management.service.EnseignantService;
import com.school_management.service.EnseignantServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class EnseignantController {

    @FXML private TextField txtNom;
    @FXML private TextField txtPrenom;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelephone;
    @FXML private TextField txtAdresse;
    @FXML private TextField txtNumeroEnseignant;
    @FXML private TextField txtSpecialite;
    @FXML private TextField txtGrade;
    @FXML private TextField txtSearch;
    @FXML private PasswordField txtPassword;
    @FXML private DatePicker dpBirthDate;
    @FXML private DatePicker dpDateRecrutement;
    @FXML private Button btnAjouter;
    @FXML private Button btnModifier;
    @FXML private Button btnSupprimer;
    @FXML private TableView<Enseignant> table;
    @FXML private TableColumn<Enseignant, Long> colId;
    @FXML private TableColumn<Enseignant, String> colNumero;
    @FXML private TableColumn<Enseignant, String> colNom;
    @FXML private TableColumn<Enseignant, String> colPrenom;
    @FXML private TableColumn<Enseignant, String> colEmail;
    @FXML private TableColumn<Enseignant, String> colTelephone;
    @FXML private TableColumn<Enseignant, String> colSpecialite;
    @FXML private TableColumn<Enseignant, String> colGrade;

    private final EnseignantService enseignantService = new EnseignantServiceImpl();
    private final ObservableList<Enseignant> enseignants = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getId()).asObject());
        colNumero.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNumeroEnseignant()));
        colNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));
        colPrenom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPrenom()));
        colEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        colTelephone.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelephone()));
        colSpecialite.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSpecialite()));
        colGrade.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGrade()));

        table.setItems(enseignants);
        refreshTable();

        btnAjouter.setOnAction(e -> ajouterEnseignant());
        btnModifier.setOnAction(e -> modifierEnseignant());
        btnSupprimer.setOnAction(e -> supprimerEnseignant());

        // Add search functionality
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> filterTable(newValue));

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtNom.setText(newSel.getNom());
                txtPrenom.setText(newSel.getPrenom());
                txtEmail.setText(newSel.getEmail());
                txtTelephone.setText(newSel.getTelephone());
                txtAdresse.setText(newSel.getAdresse());
                txtNumeroEnseignant.setText(newSel.getNumeroEnseignant());
                txtSpecialite.setText(newSel.getSpecialite());
                txtGrade.setText(newSel.getGrade());
                txtPassword.setText(newSel.getPassword());
                dpBirthDate.setValue(newSel.getBirthDate());
                dpDateRecrutement.setValue(newSel.getDateRecrutement());
            } else {
                clearFields();
            }
        });
    }

    private void refreshTable() {
        try {
            enseignants.setAll(enseignantService.lireTous());
            // Apply current search filter if any
            if (txtSearch != null && !txtSearch.getText().isEmpty()) {
                filterTable(txtSearch.getText());
            }
        } catch (Exception e) {
            alert("Erreur", "Impossible de charger les enseignants :\n" + e.getMessage());
        }
    }

    private void filterTable(String searchText) {
        try {
            var enseignantList = enseignantService.lireTous();
            
            if (searchText == null || searchText.trim().isEmpty()) {
                enseignants.setAll(enseignantList);
            } else {
                String search = searchText.toLowerCase().trim();
                var filtered = enseignantList.stream()
                    .filter(e -> 
                        (e.getNom() != null && e.getNom().toLowerCase().contains(search)) ||
                        (e.getPrenom() != null && e.getPrenom().toLowerCase().contains(search)) ||
                        (e.getEmail() != null && e.getEmail().toLowerCase().contains(search)) ||
                        (e.getNumeroEnseignant() != null && e.getNumeroEnseignant().toLowerCase().contains(search))
                    )
                    .toList();
                enseignants.setAll(filtered);
            }
        } catch (Exception e) {
            alert("Erreur", "Impossible de filtrer les enseignants :\n" + e.getMessage());
        }
    }

    private void ajouterEnseignant() {
        if (!validateFields()) {
            return;
        }

        try {
            Enseignant enseignant = new Enseignant();
            enseignant.setNom(txtNom.getText());
            enseignant.setPrenom(txtPrenom.getText());
            enseignant.setEmail(txtEmail.getText());
            enseignant.setTelephone(txtTelephone.getText());
            enseignant.setAdresse(txtAdresse.getText());
            enseignant.setNumeroEnseignant(txtNumeroEnseignant.getText());
            enseignant.setSpecialite(txtSpecialite.getText());
            enseignant.setGrade(txtGrade.getText());
            enseignant.setPassword(txtPassword.getText());
            enseignant.setBirthDate(dpBirthDate.getValue());
            enseignant.setDateRecrutement(dpDateRecrutement.getValue());

            enseignantService.ajouter(enseignant);
            refreshTable();
            clearFields();
            alert("Succès", "Enseignant ajouté avec succès.");
        } catch (Exception e) {
            alert("Erreur", "Impossible d'ajouter l'enseignant :\n" + e.getMessage());
        }
    }

    private void modifierEnseignant() {
        Enseignant selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Modification", "Veuillez sélectionner un enseignant à modifier.");
            return;
        }

        if (!validateFields()) {
            return;
        }

        try {
            selected.setNom(txtNom.getText());
            selected.setPrenom(txtPrenom.getText());
            selected.setEmail(txtEmail.getText());
            selected.setTelephone(txtTelephone.getText());
            selected.setAdresse(txtAdresse.getText());
            selected.setNumeroEnseignant(txtNumeroEnseignant.getText());
            selected.setSpecialite(txtSpecialite.getText());
            selected.setGrade(txtGrade.getText());
            selected.setPassword(txtPassword.getText());
            selected.setBirthDate(dpBirthDate.getValue());
            selected.setDateRecrutement(dpDateRecrutement.getValue());

            enseignantService.modifier(selected);
            refreshTable();
            clearFields();
            alert("Succès", "Enseignant modifié avec succès.");
        } catch (Exception e) {
            alert("Erreur", "Impossible de modifier l'enseignant :\n" + e.getMessage());
        }
    }

    private void supprimerEnseignant() {
        Enseignant selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Suppression", "Veuillez sélectionner un enseignant à supprimer.");
            return;
        }

        try {
            enseignantService.supprimer(selected.getId());
            refreshTable();
            clearFields();
            alert("Succès", "Enseignant supprimé avec succès.");
        } catch (Exception e) {
            alert("Erreur", "Impossible de supprimer l'enseignant :\n" + e.getMessage());
        }
    }

    private boolean validateFields() {
        if (txtNom.getText().isBlank() || txtPrenom.getText().isBlank() || 
            txtEmail.getText().isBlank() || txtNumeroEnseignant.getText().isBlank() ||
            txtPassword.getText().isBlank()) {
            alert("Validation", "Veuillez remplir tous les champs obligatoires.");
            return false;
        }
        return true;
    }

    private void clearFields() {
        txtNom.clear();
        txtPrenom.clear();
        txtEmail.clear();
        txtTelephone.clear();
        txtAdresse.clear();
        txtNumeroEnseignant.clear();
        txtSpecialite.clear();
        txtGrade.clear();
        txtPassword.clear();
        dpBirthDate.setValue(null);
        dpDateRecrutement.setValue(null);
    }

    private void alert(String titre, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestion des enseignants");
        alert.setHeaderText(titre);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
