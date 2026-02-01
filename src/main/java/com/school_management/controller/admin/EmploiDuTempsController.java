package com.school_management.controller.admin;

import com.school_management.model.Classe;
import com.school_management.model.EmploiDuTemps;
import com.school_management.model.enums.Semestre;
import com.school_management.service.ClasseService;
import com.school_management.service.ClasseServiceImpl;
import com.school_management.service.EmploiDuTempsService;
import com.school_management.service.EmploiDuTempsServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.List;

public class EmploiDuTempsController {

    @FXML private ComboBox<Classe> cbClasse;
    @FXML private TextField txtAnneeScolaire;
    @FXML private ComboBox<Semestre> cbSemestre;
    @FXML private DatePicker dpDateDebut;
    @FXML private DatePicker dpDateFin;
    @FXML private Button btnAjouter;
    @FXML private Button btnModifier;
    @FXML private Button btnSupprimer;
    @FXML private TableView<EmploiDuTemps> table;
    @FXML private TableColumn<EmploiDuTemps, Long> colId;
    @FXML private TableColumn<EmploiDuTemps, String> colClasse;
    @FXML private TableColumn<EmploiDuTemps, String> colAnneeScolaire;
    @FXML private TableColumn<EmploiDuTemps, String> colSemestre;
    @FXML private TableColumn<EmploiDuTemps, LocalDate> colDateDebut;
    @FXML private TableColumn<EmploiDuTemps, LocalDate> colDateFin;

    private final EmploiDuTempsService emploiDuTempsService = new EmploiDuTempsServiceImpl();
    private final ClasseService classeService = new ClasseServiceImpl();
    private final ObservableList<EmploiDuTemps> emploisDuTemps = FXCollections.observableArrayList();
    private final ObservableList<Classe> classes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        try {
            classes.setAll(classeService.lireTous());
        } catch (Exception e) {
            alert("Erreur de chargement", "Impossible de charger les classes : " + e.getMessage());
        }

        // Setup ComboBoxes
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

        cbSemestre.setItems(FXCollections.observableArrayList(Semestre.values()));

        // Setup table columns
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getId()).asObject());
        colClasse.setCellValueFactory(data -> {
            Classe classe = data.getValue().getClasse();
            return new javafx.beans.property.SimpleStringProperty(
                classe != null ? classe.getCode() + " - " + classe.getNom() : ""
            );
        });
        colAnneeScolaire.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAnneeScolaire()));
        colSemestre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
            data.getValue().getSemestre() != null ? data.getValue().getSemestre().toString() : ""
        ));
        colDateDebut.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getDateDebut()));
        colDateFin.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getDateFin()));

        table.setItems(emploisDuTemps);
        refreshTable();

        btnAjouter.setOnAction(e -> ajouterEmploiDuTemps());
        btnModifier.setOnAction(e -> modifierEmploiDuTemps());
        btnSupprimer.setOnAction(e -> supprimerEmploiDuTemps());

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtAnneeScolaire.setText(newSel.getAnneeScolaire());
                cbSemestre.setValue(newSel.getSemestre());
                dpDateDebut.setValue(newSel.getDateDebut());
                dpDateFin.setValue(newSel.getDateFin());
                
                // Set the selected classe
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
            List<EmploiDuTemps> edtList = emploiDuTempsService.lireTous();
            // Load the classe for each emploi du temps
            for (EmploiDuTemps edt : edtList) {
                if (edt.getClasseId() != null) {
                    Classe classe = classeService.lireParId(edt.getClasseId());
                    edt.setClasse(classe);
                }
            }
            emploisDuTemps.setAll(edtList);
        } catch (Exception e) {
            alert("Erreur", "Impossible de charger les emplois du temps :\n" + e.getMessage());
        }
    }

    private void ajouterEmploiDuTemps() {
        if (!validateFields()) {
            return;
        }

        try {
            Classe selectedClasse = cbClasse.getValue();
            if (selectedClasse == null) {
                alert("Erreur", "Veuillez sélectionner une classe.");
                return;
            }

            EmploiDuTemps edt = new EmploiDuTemps();
            edt.setClasseId(selectedClasse.getId());
            edt.setAnneeScolaire(txtAnneeScolaire.getText());
            edt.setSemestre(cbSemestre.getValue());
            edt.setDateDebut(dpDateDebut.getValue());
            edt.setDateFin(dpDateFin.getValue());

            emploiDuTempsService.ajouter(edt);
            refreshTable();
            clearFields();
            alert("Succès", "Emploi du temps ajouté avec succès.");
        } catch (Exception e) {
            alert("Erreur", "Impossible d'ajouter l'emploi du temps :\n" + e.getMessage());
        }
    }

    private void modifierEmploiDuTemps() {
        EmploiDuTemps selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Modification", "Veuillez sélectionner un emploi du temps à modifier.");
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

            selected.setClasseId(selectedClasse.getId());
            selected.setAnneeScolaire(txtAnneeScolaire.getText());
            selected.setSemestre(cbSemestre.getValue());
            selected.setDateDebut(dpDateDebut.getValue());
            selected.setDateFin(dpDateFin.getValue());

            emploiDuTempsService.modifier(selected);
            refreshTable();
            clearFields();
            alert("Succès", "Emploi du temps modifié avec succès.");
        } catch (Exception e) {
            alert("Erreur", "Impossible de modifier l'emploi du temps :\n" + e.getMessage());
        }
    }

    private void supprimerEmploiDuTemps() {
        EmploiDuTemps selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Suppression", "Veuillez sélectionner un emploi du temps à supprimer.");
            return;
        }

        try {
            emploiDuTempsService.supprimer(selected.getId());
            refreshTable();
            clearFields();
            alert("Succès", "Emploi du temps supprimé avec succès.");
        } catch (Exception e) {
            alert("Erreur", "Impossible de supprimer l'emploi du temps :\n" + e.getMessage());
        }
    }

    private boolean validateFields() {
        if (cbClasse.getValue() == null || txtAnneeScolaire.getText().isBlank() || 
            cbSemestre.getValue() == null) {
            alert("Validation", "Veuillez remplir tous les champs obligatoires.");
            return false;
        }
        return true;
    }

    private void clearFields() {
        cbClasse.setValue(null);
        txtAnneeScolaire.clear();
        cbSemestre.setValue(null);
        dpDateDebut.setValue(null);
        dpDateFin.setValue(null);
    }

    private void alert(String titre, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestion des emplois du temps");
        alert.setHeaderText(titre);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
