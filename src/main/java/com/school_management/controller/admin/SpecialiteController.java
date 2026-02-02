package com.school_management.controller.admin;

import com.school_management.model.Matiere;
import com.school_management.model.Specialite;
import com.school_management.service.MatiereService;
import com.school_management.service.SpecialiteService;
import com.school_management.service.MatiereServiceImpl;
import com.school_management.service.SpecialiteServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Contrôleur pour la gestion des spécialités et l'association avec les matières.
 * 
 * COMMENT ASSIGNER DES MATIÈRES À UNE SPÉCIALITÉ:
 * 1. Sélectionnez une ou plusieurs matières dans le tableau 'matieresTable'
 *    (Utilisez Ctrl+Clic pour sélectionner plusieurs matières)
 * 2. Remplissez les champs Code et Nom de la spécialité
 * 3. Cliquez sur 'Ajouter' pour créer une nouvelle spécialité avec les matières sélectionnées
 * 4. Pour modifier: sélectionnez une spécialité, modifiez les matières, puis cliquez sur 'Modifier'
 */
public class SpecialiteController {

    @FXML private TextField txtCode;
    @FXML private TextField txtNom;
    @FXML private Button btnAjouter;
    @FXML private Button btnModifier;
    @FXML private Button btnSupprimer;
    @FXML private TableView<Specialite> table;
    @FXML private TableColumn<Specialite, Long> colId;
    @FXML private TableColumn<Specialite, String> colCode;
    @FXML private TableColumn<Specialite, String> colNom;
    @FXML private TableColumn<Specialite, String> colMatieres;
    @FXML private TableView<Matiere> matieresTable;
    @FXML private TableColumn<Matiere, Long> colMatId;
    @FXML private TableColumn<Matiere, String> colMatCode;
    @FXML private TableColumn<Matiere, String> colMatNom;

    private ObservableList<Matiere> allMatieres = FXCollections.observableArrayList();
    private final SpecialiteService service = new SpecialiteServiceImpl();
    private final MatiereService matiereService = new MatiereServiceImpl();
    private final ObservableList<Specialite> specialites = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        try {
            allMatieres.setAll(matiereService.listeMatieres());
            specialites.setAll(service.listeSpecialites());
        } catch (Exception e) {
            alert("Erreur de chargement", e.getMessage());
        }

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getId()).asObject());
        colCode.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCode()));
        colNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));
        colMatieres.setCellValueFactory(data -> {
            Set<Matiere> matieres = data.getValue().getMatieres();
            if (matieres == null || matieres.isEmpty()) {
                return new javafx.beans.property.SimpleStringProperty("Aucune matière");
            }
            String matieresStr = matieres.stream()
                .map(m -> m.getCode() + " (" + m.getNom() + ")")
                .sorted()
                .reduce((a, b) -> a + ", " + b)
                .orElse("Aucune matière");
            return new javafx.beans.property.SimpleStringProperty(matieresStr);
        });
        colMatieres.setStyle("-fx-wrap-text: true;");
        table.setItems(specialites);

        colMatId.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getId()).asObject());
        colMatCode.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCode()));
        colMatNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));
        matieresTable.setItems(allMatieres);
        matieresTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Synchronise la sélection des matières liées selon la spécialité choisie
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtCode.setText(newSel.getCode());
                txtNom.setText(newSel.getNom());
                matieresTable.getSelectionModel().clearSelection();

                if (newSel.getMatieres() != null) {
                    for (Matiere m : allMatieres) {
                        if (newSel.getMatieres().stream().anyMatch(ma -> ma.getId().equals(m.getId()))) {
                            matieresTable.getSelectionModel().select(m);
                        }
                    }
                }
            } else {
                clearFields();
            }
        });

        btnAjouter.setOnAction(e -> ajouterSpecialite());
        btnModifier.setOnAction(e -> modifierSpecialite());
        btnSupprimer.setOnAction(e -> supprimerSpecialite());
    }

    private void refreshTable() {
        try {
            specialites.setAll(service.listeSpecialites());
        } catch (Exception e) {
            alert("Erreur", "Impossible de charger les spécialités :\n" + e.getMessage());
        }
    }

    private void ajouterSpecialite() {
        String code = txtCode.getText();
        String nom = txtNom.getText();

        if (code.isBlank() || nom.isBlank()) {
            alert("Validation", "Veuillez remplir le code et le nom.");
            return;
        }
        try {
            Specialite s = new Specialite();
            s.setCode(code);
            s.setNom(nom);
            Set<Matiere> selectedMatieres = new HashSet<>(matieresTable.getSelectionModel().getSelectedItems());
            s.setMatieres(selectedMatieres);

            service.ajouterAvecMatieres(s);
            refreshTable();
            clearFields();
        } catch (Exception e) {
            alert("Erreur", "Impossible d'ajouter :\n" + e.getMessage());
        }
    }

    private void modifierSpecialite() {
        Specialite selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Modification", "Veuillez sélectionner une spécialité à modifier.");
            return;
        }
        try {
            selected.setCode(txtCode.getText());
            selected.setNom(txtNom.getText());
            Set<Matiere> selectedMatieres = new HashSet<>(matieresTable.getSelectionModel().getSelectedItems());
            selected.setMatieres(selectedMatieres);

            service.modifierAvecMatieres(selected);
            refreshTable();
            clearFields();
        } catch (Exception e) {
            alert("Erreur", "Impossible de modifier :\n" + e.getMessage());
        }
    }

    private void supprimerSpecialite() {
        Specialite selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Suppression", "Veuillez sélectionner une spécialité à supprimer.");
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
        matieresTable.getSelectionModel().clearSelection();
    }

    private void alert(String titre, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestion des spécialités");
        alert.setHeaderText(titre);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
