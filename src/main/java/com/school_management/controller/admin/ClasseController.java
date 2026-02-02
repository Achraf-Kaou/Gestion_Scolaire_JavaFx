package com.school_management.controller.admin;

import com.school_management.model.Classe;
import com.school_management.model.Specialite;
import com.school_management.model.enums.NiveauAnnee;
import com.school_management.model.enums.TypeDiplome;
import com.school_management.service.ClasseService;
import com.school_management.service.ClasseServiceImpl;
import com.school_management.service.SpecialiteService;
import com.school_management.service.SpecialiteServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class ClasseController {

    @FXML private TextField txtCode;
    @FXML private TextField txtNom;
    @FXML private TextField txtCapaciteMax;
    @FXML private TextField txtAnneeScolaire;
    @FXML private TextField txtSearch;
    @FXML private ComboBox<TypeDiplome> cbTypeDiplome;
    @FXML private ComboBox<NiveauAnnee> cbNiveauAnnee;
    @FXML private ComboBox<Specialite> cbSpecialite;
    @FXML private Button btnAjouter;
    @FXML private Button btnModifier;
    @FXML private Button btnSupprimer;
    @FXML private TableView<Classe> table;
    @FXML private TableColumn<Classe, Long> colId;
    @FXML private TableColumn<Classe, String> colCode;
    @FXML private TableColumn<Classe, String> colNom;
    @FXML private TableColumn<Classe, Integer> colCapaciteMax;
    @FXML private TableColumn<Classe, String> colAnneeScolaire;
    @FXML private TableColumn<Classe, String> colTypeDiplome;
    @FXML private TableColumn<Classe, String> colNiveauAnnee;
    @FXML private TableColumn<Classe, String> colSpecialite;

    private final ClasseService classeService = new ClasseServiceImpl();
    private final SpecialiteService specialiteService = new SpecialiteServiceImpl();
    private final ObservableList<Classe> classes = FXCollections.observableArrayList();
    private final ObservableList<Specialite> specialites = FXCollections.observableArrayList();
    private List<Classe> allClasses = new java.util.ArrayList<>();  // Cache for search filtering

    @FXML
    public void initialize() {
        try {
            specialites.setAll(specialiteService.listeSpecialites());
        } catch (Exception e) {
            alert("Erreur de chargement", "Impossible de charger les spécialités : " + e.getMessage());
        }

        // Setup ComboBoxes
        cbTypeDiplome.setItems(FXCollections.observableArrayList(TypeDiplome.values()));
        cbNiveauAnnee.setItems(FXCollections.observableArrayList(NiveauAnnee.values()));
        cbSpecialite.setItems(specialites);
        
        cbSpecialite.setCellFactory(param -> new ListCell<Specialite>() {
            @Override
            protected void updateItem(Specialite item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCode() + " - " + item.getNom());
            }
        });
        cbSpecialite.setButtonCell(new ListCell<Specialite>() {
            @Override
            protected void updateItem(Specialite item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCode() + " - " + item.getNom());
            }
        });

        // Setup table columns
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getId()).asObject());
        colCode.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCode()));
        colNom.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));
        colCapaciteMax.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getCapaciteMax()).asObject());
        colAnneeScolaire.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAnneeScolaire()));
        colTypeDiplome.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
            data.getValue().getTypeDiplome() != null ? data.getValue().getTypeDiplome().toString() : ""
        ));
        colNiveauAnnee.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
            data.getValue().getNiveauAnnee() != null ? data.getValue().getNiveauAnnee().toString() : ""
        ));
        colSpecialite.setCellValueFactory(data -> {
            Specialite specialite = data.getValue().getSpecialite();
            return new javafx.beans.property.SimpleStringProperty(
                specialite != null ? specialite.getCode() + " - " + specialite.getNom() : ""
            );
        });

        table.setItems(classes);
        refreshTable();

        btnAjouter.setOnAction(e -> ajouterClasse());
        btnModifier.setOnAction(e -> modifierClasse());
        btnSupprimer.setOnAction(e -> supprimerClasse());

        // Add search functionality
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> filterTable(newValue));

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtCode.setText(newSel.getCode());
                txtNom.setText(newSel.getNom());
                txtCapaciteMax.setText(String.valueOf(newSel.getCapaciteMax()));
                txtAnneeScolaire.setText(newSel.getAnneeScolaire());
                cbTypeDiplome.setValue(newSel.getTypeDiplome());
                cbNiveauAnnee.setValue(newSel.getNiveauAnnee());
                
                // Set the selected specialite
                if (newSel.getSpecialite() != null) {
                    for (Specialite specialite : specialites) {
                        if (specialite.getId().equals(newSel.getSpecialite().getId())) {
                            cbSpecialite.setValue(specialite);
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
            List<Classe> classeList = classeService.lireTous();
            // Load the specialite for each classe
            for (Classe classe : classeList) {
                if (classe.getSpecialiteId() != null) {
                    Specialite specialite = specialiteService.listeSpecialitesId(classe.getSpecialiteId());
                    classe.setSpecialite(specialite);
                }
            }
            allClasses = classeList;  // Cache the full list
            classes.setAll(classeList);
            // Apply current search filter if any
            if (txtSearch != null && !txtSearch.getText().isEmpty()) {
                filterTable(txtSearch.getText());
            }
        } catch (Exception e) {
            alert("Erreur", "Impossible de charger les classes :\n" + e.getMessage());
        }
    }

    private void filterTable(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            classes.setAll(allClasses);
        } else {
            String search = searchText.toLowerCase().trim();
            List<Classe> filtered = allClasses.stream()
                .filter(c -> 
                    (c.getCode() != null && c.getCode().toLowerCase().contains(search)) ||
                    (c.getNom() != null && c.getNom().toLowerCase().contains(search))
                )
                .toList();
            classes.setAll(filtered);
        }
    }

    private void ajouterClasse() {
        if (!validateFields()) {
            return;
        }

        try {
            Specialite selectedSpecialite = cbSpecialite.getValue();
            if (selectedSpecialite == null) {
                alert("Erreur", "Veuillez sélectionner une spécialité.");
                return;
            }

            Classe classe = new Classe();
            classe.setCode(txtCode.getText());
            classe.setNom(txtNom.getText());
            classe.setCapaciteMax(Integer.parseInt(txtCapaciteMax.getText()));
            classe.setAnneeScolaire(txtAnneeScolaire.getText());
            classe.setTypeDiplome(cbTypeDiplome.getValue());
            classe.setNiveauAnnee(cbNiveauAnnee.getValue());
            classe.setSpecialiteId(selectedSpecialite.getId());

            classeService.ajouter(classe);
            refreshTable();
            clearFields();
            alert("Succès", "Classe ajoutée avec succès.");
        } catch (NumberFormatException e) {
            alert("Erreur", "La capacité maximale doit être un nombre valide.");
        } catch (Exception e) {
            alert("Erreur", "Impossible d'ajouter la classe :\n" + e.getMessage());
        }
    }

    private void modifierClasse() {
        Classe selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Modification", "Veuillez sélectionner une classe à modifier.");
            return;
        }

        if (!validateFields()) {
            return;
        }

        try {
            Specialite selectedSpecialite = cbSpecialite.getValue();
            if (selectedSpecialite == null) {
                alert("Erreur", "Veuillez sélectionner une spécialité.");
                return;
            }

            selected.setCode(txtCode.getText());
            selected.setNom(txtNom.getText());
            selected.setCapaciteMax(Integer.parseInt(txtCapaciteMax.getText()));
            selected.setAnneeScolaire(txtAnneeScolaire.getText());
            selected.setTypeDiplome(cbTypeDiplome.getValue());
            selected.setNiveauAnnee(cbNiveauAnnee.getValue());
            selected.setSpecialiteId(selectedSpecialite.getId());

            classeService.modifier(selected);
            refreshTable();
            clearFields();
            alert("Succès", "Classe modifiée avec succès.");
        } catch (NumberFormatException e) {
            alert("Erreur", "La capacité maximale doit être un nombre valide.");
        } catch (Exception e) {
            alert("Erreur", "Impossible de modifier la classe :\n" + e.getMessage());
        }
    }

    private void supprimerClasse() {
        Classe selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Suppression", "Veuillez sélectionner une classe à supprimer.");
            return;
        }

        try {
            classeService.supprimer(selected.getId());
            refreshTable();
            clearFields();
            alert("Succès", "Classe supprimée avec succès.");
        } catch (Exception e) {
            alert("Erreur", "Impossible de supprimer la classe :\n" + e.getMessage());
        }
    }

    private boolean validateFields() {
        if (txtCode.getText().isBlank() || txtNom.getText().isBlank() || 
            txtCapaciteMax.getText().isBlank() || txtAnneeScolaire.getText().isBlank() ||
            cbTypeDiplome.getValue() == null || cbNiveauAnnee.getValue() == null ||
            cbSpecialite.getValue() == null) {
            alert("Validation", "Veuillez remplir tous les champs obligatoires.");
            return false;
        }
        return true;
    }

    private void clearFields() {
        txtCode.clear();
        txtNom.clear();
        txtCapaciteMax.clear();
        txtAnneeScolaire.clear();
        cbTypeDiplome.setValue(null);
        cbNiveauAnnee.setValue(null);
        cbSpecialite.setValue(null);
    }

    private void alert(String titre, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestion des classes");
        alert.setHeaderText(titre);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
