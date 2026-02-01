package com.school_management.controller.admin;

import com.school_management.model.Classe;
import com.school_management.model.Etudiant;
import com.school_management.service.ClasseService;
import com.school_management.service.ClasseServiceImpl;
import com.school_management.service.EtudiantService;
import com.school_management.service.EtudiantServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;

public class EtudiantController {

    @FXML private TextField txtNom;
    @FXML private TextField txtPrenom;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelephone;
    @FXML private TextField txtAdresse;
    @FXML private TextField txtNumeroEtudiant;
    @FXML private PasswordField txtPassword;
    @FXML private DatePicker dpBirthDate;
    @FXML private DatePicker dpDateInscription;
    @FXML private ComboBox<Classe> cbClasse;
    @FXML private Button btnAjouter;
    @FXML private Button btnModifier;
    @FXML private Button btnSupprimer;
    @FXML private TableView<Etudiant> table;
    @FXML private TableColumn<Etudiant, Long> colId;
    @FXML private TableColumn<Etudiant, String> colNumero;
    @FXML private TableColumn<Etudiant, String> colNom;
    @FXML private TableColumn<Etudiant, String> colPrenom;
    @FXML private TableColumn<Etudiant, String> colEmail;
    @FXML private TableColumn<Etudiant, String> colTelephone;
    @FXML private TableColumn<Etudiant, String> colClasse;

    private final EtudiantService etudiantService = new EtudiantServiceImpl();
    private final ClasseService classeService = new ClasseServiceImpl();
    private final ObservableList<Etudiant> etudiants = FXCollections.observableArrayList();
    private final ObservableList<Classe> classes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        try {
            classes.setAll(classeService.lireTous());
        } catch (Exception e) {
            alert("Erreur de chargement", "Impossible de charger les classes : " + e.getMessage());
        }

        cbClasse.setItems(classes);
        cbClasse.setCellFactory(param -> new ListCell<Classe>() {
            @Override
            protected void updateItem(Classe item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCode() + " - " + item.getNom());
            }
        });
        cbClasse.setButtonCell(new ListCell<Classe>() {
            @Override
            protected void updateItem(Classe item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCode() + " - " + item.getNom());
            }
        });

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getId()).asObject());
        colNumero.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNumeroEtudiant()));
        colNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));
        colPrenom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPrenom()));
        colEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        colTelephone.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelephone()));
        colClasse.setCellValueFactory(data -> {
            Classe classe = data.getValue().getClasse();
            return new javafx.beans.property.SimpleStringProperty(
                classe != null ? classe.getCode() + " - " + classe.getNom() : ""
            );
        });

        table.setItems(etudiants);
        refreshTable();

        btnAjouter.setOnAction(e -> ajouterEtudiant());
        btnModifier.setOnAction(e -> modifierEtudiant());
        btnSupprimer.setOnAction(e -> supprimerEtudiant());

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtNom.setText(newSel.getNom());
                txtPrenom.setText(newSel.getPrenom());
                txtEmail.setText(newSel.getEmail());
                txtTelephone.setText(newSel.getTelephone());
                txtAdresse.setText(newSel.getAdresse());
                txtNumeroEtudiant.setText(newSel.getNumeroEtudiant());
                txtPassword.setText(newSel.getPassword());
                dpBirthDate.setValue(newSel.getBirthDate());
                dpDateInscription.setValue(newSel.getDateInscription());
                
                // Set the selected class
                if (newSel.getClasse() != null) {
                    for (Classe classe : classes) {
                        if (classe.getId().equals(newSel.getClasse().getId())) {
                            cbClasse.setValue(classe);
                            break;
                        }
                    }
                }
            } else {
                clearFields();
            }
        });
    }

    private void refreshTable() {
        try {
            List<Etudiant> etudiantList = etudiantService.lireTous();
            // Load the classe for each etudiant
            for (Etudiant etudiant : etudiantList) {
                if (etudiant.getClasseId() != null) {
                    Classe classe = classeService.lireParId(etudiant.getClasseId());
                    etudiant.setClasse(classe);
                }
            }
            etudiants.setAll(etudiantList);
        } catch (Exception e) {
            alert("Erreur", "Impossible de charger les étudiants :\n" + e.getMessage());
        }
    }

    private void ajouterEtudiant() {
        if (!validateFields()) {
            return;
        }

        try {
            Classe selectedClasse = cbClasse.getValue();
            if (selectedClasse == null) {
                alert("Erreur", "Veuillez sélectionner une classe.");
                return;
            }

            Etudiant etudiant = new Etudiant();
            etudiant.setNom(txtNom.getText());
            etudiant.setPrenom(txtPrenom.getText());
            etudiant.setEmail(txtEmail.getText());
            etudiant.setTelephone(txtTelephone.getText());
            etudiant.setAdresse(txtAdresse.getText());
            etudiant.setNumeroEtudiant(txtNumeroEtudiant.getText());
            etudiant.setPassword(txtPassword.getText());
            etudiant.setBirthDate(dpBirthDate.getValue());
            etudiant.setDateInscription(dpDateInscription.getValue());
            etudiant.setClasseId(selectedClasse.getId());

            etudiantService.ajouter(etudiant);
            refreshTable();
            clearFields();
            alert("Succès", "Étudiant ajouté avec succès.");
        } catch (Exception e) {
            alert("Erreur", "Impossible d'ajouter l'étudiant :\n" + e.getMessage());
        }
    }

    private void modifierEtudiant() {
        Etudiant selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Modification", "Veuillez sélectionner un étudiant à modifier.");
            return;
        }

        if (!validateFields()) {
            return;
        }

        try {
            Classe selectedClasse = cbClasse.getValue();
            if (selectedClasse == null) {
                alert("Erreur", "Veuillez sélectionner une classe.");
                return;
            }

            selected.setNom(txtNom.getText());
            selected.setPrenom(txtPrenom.getText());
            selected.setEmail(txtEmail.getText());
            selected.setTelephone(txtTelephone.getText());
            selected.setAdresse(txtAdresse.getText());
            selected.setNumeroEtudiant(txtNumeroEtudiant.getText());
            selected.setPassword(txtPassword.getText());
            selected.setBirthDate(dpBirthDate.getValue());
            selected.setDateInscription(dpDateInscription.getValue());
            selected.setClasseId(selectedClasse.getId());

            etudiantService.modifier(selected);
            refreshTable();
            clearFields();
            alert("Succès", "Étudiant modifié avec succès.");
        } catch (Exception e) {
            alert("Erreur", "Impossible de modifier l'étudiant :\n" + e.getMessage());
        }
    }

    private void supprimerEtudiant() {
        Etudiant selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Suppression", "Veuillez sélectionner un étudiant à supprimer.");
            return;
        }

        try {
            etudiantService.supprimer(selected.getId());
            refreshTable();
            clearFields();
            alert("Succès", "Étudiant supprimé avec succès.");
        } catch (Exception e) {
            alert("Erreur", "Impossible de supprimer l'étudiant :\n" + e.getMessage());
        }
    }

    private boolean validateFields() {
        if (txtNom.getText().isBlank() || txtPrenom.getText().isBlank() || 
            txtEmail.getText().isBlank() || txtNumeroEtudiant.getText().isBlank() ||
            txtPassword.getText().isBlank() || dpDateInscription.getValue() == null ||
            cbClasse.getValue() == null) {
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
        txtNumeroEtudiant.clear();
        txtPassword.clear();
        dpBirthDate.setValue(null);
        dpDateInscription.setValue(null);
        cbClasse.setValue(null);
    }

    private void alert(String titre, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestion des étudiants");
        alert.setHeaderText(titre);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
