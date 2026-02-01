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
    @FXML private TextField txtNbHeuresCours;
    @FXML private TextField txtNbHeuresTd;
    @FXML private TextField txtNbHeuresTp;
    @FXML private TextField txtNbCredits;
    @FXML private Button btnAjouter;
    @FXML private Button btnModifier;
    @FXML private Button btnSupprimer;
    @FXML private TableView<Matiere> table;
    @FXML private TableColumn<Matiere, Long> colId;
    @FXML private TableColumn<Matiere, String> colCode;
    @FXML private TableColumn<Matiere, String> colNom;
    @FXML private TableColumn<Matiere, Integer> colNbHeuresCours;
    @FXML private TableColumn<Matiere, Integer> colNbHeuresTd;
    @FXML private TableColumn<Matiere, Integer> colNbHeuresTp;
    @FXML private TableColumn<Matiere, Integer> colNbCredits;

    private final MatiereService service = new MatiereServiceImpl();
    private final ObservableList<Matiere> matieres = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getId()).asObject());
        colCode.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCode()));
        colNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));
        colNbHeuresCours.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getNbHeuresCours()).asObject());
        colNbHeuresTd.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getNbHeuresTd()).asObject());
        colNbHeuresTp.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getNbHeuresTp()).asObject());
        colNbCredits.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getNbCredits()).asObject());

        table.setItems(matieres);
        refreshTable();

        btnAjouter.setOnAction(e -> ajouterMatiere());
        btnModifier.setOnAction(e -> modifierMatiere());
        btnSupprimer.setOnAction(e -> supprimerMatiere());

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtCode.setText(newSel.getCode());
                txtNom.setText(newSel.getNom());
                txtNbHeuresCours.setText(String.valueOf(newSel.getNbHeuresCours()));
                txtNbHeuresTd.setText(String.valueOf(newSel.getNbHeuresTd()));
                txtNbHeuresTp.setText(String.valueOf(newSel.getNbHeuresTp()));
                txtNbCredits.setText(String.valueOf(newSel.getNbCredits()));
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

        if (code.isBlank() || nom.isBlank()) {
            alert("Validation", "Veuillez remplir au moins le code et le nom.");
            return;
        }

        try {
            Matiere m = new Matiere();
            m.setCode(code);
            m.setNom(nom);
            m.setNbHeuresCours(parseIntOrZero(txtNbHeuresCours.getText()));
            m.setNbHeuresTd(parseIntOrZero(txtNbHeuresTd.getText()));
            m.setNbHeuresTp(parseIntOrZero(txtNbHeuresTp.getText()));
            m.setNbCredits(parseIntOrZero(txtNbCredits.getText()));
            service.ajouter(m);
            refreshTable();
            clearFields();
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
            selected.setCode(txtCode.getText());
            selected.setNom(txtNom.getText());
            selected.setNbHeuresCours(parseIntOrZero(txtNbHeuresCours.getText()));
            selected.setNbHeuresTd(parseIntOrZero(txtNbHeuresTd.getText()));
            selected.setNbHeuresTp(parseIntOrZero(txtNbHeuresTp.getText()));
            selected.setNbCredits(parseIntOrZero(txtNbCredits.getText()));
            service.modifier(selected);
            refreshTable();
            clearFields();
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
        txtNbHeuresCours.clear();
        txtNbHeuresTd.clear();
        txtNbHeuresTp.clear();
        txtNbCredits.clear();
    }

    private int parseIntOrZero(String text) {
        try {
            return text.isBlank() ? 0 : Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void alert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestion des matières");
        alert.setHeaderText(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
