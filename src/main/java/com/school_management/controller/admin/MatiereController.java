package com.school_management.controller.admin;

import com.school_management.model.Matiere;
import com.school_management.service.MatiereService;
import com.school_management.service.MatiereServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MatiereController {

    @FXML private TextField txtCode;
    @FXML private TextField txtNom;
    @FXML private TextField txtSpecialiteId;
    @FXML private Button btnAjouter;
    @FXML private Button btnModifier;
    @FXML private Button btnSupprimer;
    @FXML private TableView<Matiere> table;
    @FXML private TableColumn<Matiere, Long> colId;
    @FXML private TableColumn<Matiere, String> colCode;
    @FXML private TableColumn<Matiere, String> colNom;
    @FXML private TableColumn<Matiere, Long> colSpecialiteId;

    private final MatiereService service = new MatiereServiceImpl();
    private final ObservableList<Matiere> matieres = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getId()).asObject());
        colCode.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCode()));
        colNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));
//        colSpecialiteId.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getSpecialiteId()).asObject());

        table.setItems(matieres);
        refreshTable();

        btnAjouter.setOnAction(e -> ajouterMatiere());
        btnModifier.setOnAction(e -> modifierMatiere());
        btnSupprimer.setOnAction(e -> supprimerMatiere());

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtCode.setText(newSel.getCode());
                txtNom.setText(newSel.getNom());
//                txtSpecialiteId.setText(String.valueOf(newSel.getSpecialiteId()));
            }
        });
    }

    private void refreshTable() {
        try {
            matieres.setAll(service.listeMatieres());
        } catch (Exception e) {
            alert("Erreur", "Impossible de charger les matières :\n" + e.getMessage());
        }
    }

    private void ajouterMatiere() {
        String code = txtCode.getText();
        String nom = txtNom.getText();
        String specialiteIdStr = txtSpecialiteId.getText();

        if (code.isBlank() || nom.isBlank() || specialiteIdStr.isBlank()) {
            alert("Validation", "Veuillez remplir tous les champs.");
            return;
        }

        try {
            long specialiteId = Long.parseLong(specialiteIdStr);
            Matiere m = new Matiere();
            m.setCode(code);
            m.setNom(nom);
//            m.setSpecialiteId(specialiteId);
            service.ajouter(m);
            refreshTable();
            clearFields();
        } catch (NumberFormatException nfe) {
            alert("Validation", "L'ID spécialité doit être un nombre.");
        } catch (Exception e) {
            alert("Erreur", "Impossible d'ajouter :\n" + e.getMessage());
        }
    }

    private void modifierMatiere() {
        Matiere selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Modification", "Veuillez sélectionner une matière à modifier.");
            return;
        }

        try {
            long specialiteId = Long.parseLong(txtSpecialiteId.getText());
            selected.setCode(txtCode.getText());
            selected.setNom(txtNom.getText());
//            selected.setSpecialiteId(specialiteId);
            service.modifier(selected);
            refreshTable();
            clearFields();
        } catch (NumberFormatException nfe) {
            alert("Validation", "L'ID spécialité doit être un nombre.");
        } catch (Exception e) {
            alert("Erreur", "Impossible de modifier :\n" + e.getMessage());
        }
    }

    private void supprimerMatiere() {
        Matiere selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Suppression", "Veuillez sélectionner une matière à supprimer.");
            return;
        }

        try {
            service.supprimer(selected.getId());
            refreshTable();
            clearFields();
        } catch (Exception e) {
            alert("Erreur", "Impossible de supprimer :\n" + e.getMessage());
        }
    }

    private void clearFields() {
        txtCode.clear();
        txtNom.clear();
        txtSpecialiteId.clear();
    }

    private void alert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestion des matières");
        alert.setHeaderText(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
