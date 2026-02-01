package com.school_management.controller.admin;

import com.school_management.model.*;
import com.school_management.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SeanceController {

    @FXML private ComboBox<String> cbJour;
    @FXML private TextField txtHeureDebut;
    @FXML private TextField txtHeureFin;
    @FXML private TextField txtSalle;
    @FXML private ComboBox<Matiere> cbMatiere;
    @FXML private ComboBox<Enseignant> cbEnseignant;
    @FXML private ComboBox<EmploiDuTemps> cbEmploiDuTemps;
    @FXML private Button btnAjouter;
    @FXML private Button btnModifier;
    @FXML private Button btnSupprimer;
    @FXML private TableView<Seance> table;
    @FXML private TableColumn<Seance, Long> colId;
    @FXML private TableColumn<Seance, String> colJour;
    @FXML private TableColumn<Seance, String> colHeureDebut;
    @FXML private TableColumn<Seance, String> colHeureFin;
    @FXML private TableColumn<Seance, String> colSalle;
    @FXML private TableColumn<Seance, String> colMatiere;
    @FXML private TableColumn<Seance, String> colEnseignant;
    @FXML private TableColumn<Seance, String> colEmploiDuTemps;

    private final SeanceService seanceService = new SeanceServiceImpl();
    private final MatiereService matiereService = new MatiereServiceImpl();
    private final EnseignantService enseignantService = new EnseignantServiceImpl();
    private final EmploiDuTempsService emploiDuTempsService = new EmploiDuTempsServiceImpl();
    private final ClasseService classeService = new ClasseServiceImpl();
    
    private final ObservableList<Seance> seances = FXCollections.observableArrayList();
    private final ObservableList<Matiere> matieres = FXCollections.observableArrayList();
    private final ObservableList<Enseignant> enseignants = FXCollections.observableArrayList();
    private final ObservableList<EmploiDuTemps> emploisDuTemps = FXCollections.observableArrayList();

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @FXML
    public void initialize() {
        try {
            matieres.setAll(matiereService.listeMatieres());
            enseignants.setAll(enseignantService.listeEnseignants());
            List<EmploiDuTemps> edtList = emploiDuTempsService.lireTous();
            for (EmploiDuTemps edt : edtList) {
                if (edt.getClasseId() != null) {
                    edt.setClasse(classeService.lireParId(edt.getClasseId()));
                }
            }
            emploisDuTemps.setAll(edtList);
        } catch (Exception e) {
            alert("Erreur de chargement", "Impossible de charger les données : " + e.getMessage());
        }

        // Setup combo boxes
        ObservableList<String> jours = FXCollections.observableArrayList(
            "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"
        );
        cbJour.setItems(jours);

        cbMatiere.setItems(matieres);
        cbMatiere.setCellFactory(param -> new ListCell<Matiere>() {
            @Override
            protected void updateItem(Matiere item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCode() + " - " + item.getNom());
            }
        });
        cbMatiere.setButtonCell(new ListCell<Matiere>() {
            @Override
            protected void updateItem(Matiere item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getCode() + " - " + item.getNom());
            }
        });

        cbEnseignant.setItems(enseignants);
        cbEnseignant.setCellFactory(param -> new ListCell<Enseignant>() {
            @Override
            protected void updateItem(Enseignant item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNom() + " " + item.getPrenom());
            }
        });
        cbEnseignant.setButtonCell(new ListCell<Enseignant>() {
            @Override
            protected void updateItem(Enseignant item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNom() + " " + item.getPrenom());
            }
        });

        cbEmploiDuTemps.setItems(emploisDuTemps);
        cbEmploiDuTemps.setCellFactory(param -> new ListCell<EmploiDuTemps>() {
            @Override
            protected void updateItem(EmploiDuTemps item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getClasse() == null) {
                    setText(null);
                } else {
                    setText(item.getClasse().getCode() + " - " + item.getAnneeScolaire() + " (" + item.getSemestre() + ")");
                }
            }
        });
        cbEmploiDuTemps.setButtonCell(new ListCell<EmploiDuTemps>() {
            @Override
            protected void updateItem(EmploiDuTemps item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getClasse() == null) {
                    setText(null);
                } else {
                    setText(item.getClasse().getCode() + " - " + item.getAnneeScolaire() + " (" + item.getSemestre() + ")");
                }
            }
        });

        // Setup table columns
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getId()).asObject());
        colJour.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getJour()));
        colHeureDebut.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
            data.getValue().getHeureDebut() != null ? data.getValue().getHeureDebut().format(timeFormatter) : ""
        ));
        colHeureFin.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
            data.getValue().getHeureFin() != null ? data.getValue().getHeureFin().format(timeFormatter) : ""
        ));
        colSalle.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSalle()));
        colMatiere.setCellValueFactory(data -> {
            Matiere m = data.getValue().getMatiere();
            return new javafx.beans.property.SimpleStringProperty(m != null ? m.getCode() + " - " + m.getNom() : "");
        });
        colEnseignant.setCellValueFactory(data -> {
            Enseignant e = data.getValue().getEnseignant();
            return new javafx.beans.property.SimpleStringProperty(e != null ? e.getNom() + " " + e.getPrenom() : "");
        });
        colEmploiDuTemps.setCellValueFactory(data -> {
            EmploiDuTemps edt = data.getValue().getEmploiDuTemps();
            if (edt != null && edt.getClasse() != null) {
                return new javafx.beans.property.SimpleStringProperty(edt.getClasse().getCode());
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });

        table.setItems(seances);
        refreshTable();

        btnAjouter.setOnAction(e -> ajouterSeance());
        btnModifier.setOnAction(e -> modifierSeance());
        btnSupprimer.setOnAction(e -> supprimerSeance());

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                cbJour.setValue(newSel.getJour());
                txtHeureDebut.setText(newSel.getHeureDebut() != null ? newSel.getHeureDebut().format(timeFormatter) : "");
                txtHeureFin.setText(newSel.getHeureFin() != null ? newSel.getHeureFin().format(timeFormatter) : "");
                txtSalle.setText(newSel.getSalle());
                
                // Set selected matiere
                if (newSel.getMatiere() != null) {
                    for (Matiere m : matieres) {
                        if (m.getId().equals(newSel.getMatiere().getId())) {
                            cbMatiere.setValue(m);
                            break;
                        }
                    }
                }
                
                // Set selected enseignant
                if (newSel.getEnseignant() != null) {
                    for (Enseignant e : enseignants) {
                        if (e.getId().equals(newSel.getEnseignant().getId())) {
                            cbEnseignant.setValue(e);
                            break;
                        }
                    }
                }
                
                // Set selected emploi du temps
                if (newSel.getEmploiDuTemps() != null) {
                    for (EmploiDuTemps edt : emploisDuTemps) {
                        if (edt.getId().equals(newSel.getEmploiDuTemps().getId())) {
                            cbEmploiDuTemps.setValue(edt);
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
            List<Seance> seanceList = seanceService.lireTous();
            // Load related objects for each seance
            for (Seance s : seanceList) {
                if (s.getMatiereId() != null) {
                    s.setMatiere(matiereService.lireParId(s.getMatiereId()));
                }
                if (s.getEnseignantId() != null) {
                    s.setEnseignant(enseignantService.lireParId(s.getEnseignantId()));
                }
                if (s.getEmploiDuTempsId() != null) {
                    EmploiDuTemps edt = emploiDuTempsService.lireParId(s.getEmploiDuTempsId());
                    if (edt != null && edt.getClasseId() != null) {
                        edt.setClasse(classeService.lireParId(edt.getClasseId()));
                    }
                    s.setEmploiDuTemps(edt);
                }
            }
            seances.setAll(seanceList);
        } catch (Exception e) {
            alert("Erreur", "Impossible de charger les séances :\n" + e.getMessage());
        }
    }

    private void ajouterSeance() {
        if (!validateFields()) {
            return;
        }

        try {
            Seance seance = new Seance();
            seance.setJour(cbJour.getValue());
            seance.setHeureDebut(LocalTime.parse(txtHeureDebut.getText(), timeFormatter));
            seance.setHeureFin(LocalTime.parse(txtHeureFin.getText(), timeFormatter));
            seance.setSalle(txtSalle.getText());
            seance.setMatiereId(cbMatiere.getValue().getId());
            seance.setEnseignantId(cbEnseignant.getValue().getId());
            seance.setEmploiDuTempsId(cbEmploiDuTemps.getValue().getId());

            seanceService.ajouter(seance);
            refreshTable();
            clearFields();
            alert("Succès", "Séance ajoutée avec succès.");
        } catch (Exception e) {
            alert("Erreur", "Impossible d'ajouter la séance :\n" + e.getMessage());
        }
    }

    private void modifierSeance() {
        Seance selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Modification", "Veuillez sélectionner une séance à modifier.");
            return;
        }

        if (!validateFields()) {
            return;
        }

        try {
            selected.setJour(cbJour.getValue());
            selected.setHeureDebut(LocalTime.parse(txtHeureDebut.getText(), timeFormatter));
            selected.setHeureFin(LocalTime.parse(txtHeureFin.getText(), timeFormatter));
            selected.setSalle(txtSalle.getText());
            selected.setMatiereId(cbMatiere.getValue().getId());
            selected.setEnseignantId(cbEnseignant.getValue().getId());
            selected.setEmploiDuTempsId(cbEmploiDuTemps.getValue().getId());

            seanceService.modifier(selected);
            refreshTable();
            clearFields();
            alert("Succès", "Séance modifiée avec succès.");
        } catch (Exception e) {
            alert("Erreur", "Impossible de modifier la séance :\n" + e.getMessage());
        }
    }

    private void supprimerSeance() {
        Seance selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            alert("Suppression", "Veuillez sélectionner une séance à supprimer.");
            return;
        }

        try {
            seanceService.supprimer(selected.getId());
            refreshTable();
            clearFields();
            alert("Succès", "Séance supprimée avec succès.");
        } catch (Exception e) {
            alert("Erreur", "Impossible de supprimer la séance :\n" + e.getMessage());
        }
    }

    private boolean validateFields() {
        if (cbJour.getValue() == null || txtHeureDebut.getText().isBlank() || 
            txtHeureFin.getText().isBlank() || cbMatiere.getValue() == null ||
            cbEnseignant.getValue() == null || cbEmploiDuTemps.getValue() == null) {
            alert("Validation", "Veuillez remplir tous les champs obligatoires.");
            return false;
        }
        
        try {
            LocalTime.parse(txtHeureDebut.getText(), timeFormatter);
            LocalTime.parse(txtHeureFin.getText(), timeFormatter);
        } catch (Exception e) {
            alert("Validation", "Format d'heure invalide. Utilisez le format HH:mm (ex: 08:00)");
            return false;
        }
        
        return true;
    }

    private void clearFields() {
        cbJour.setValue(null);
        txtHeureDebut.clear();
        txtHeureFin.clear();
        txtSalle.clear();
        cbMatiere.setValue(null);
        cbEnseignant.setValue(null);
        cbEmploiDuTemps.setValue(null);
    }

    private void alert(String titre, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestion des séances");
        alert.setHeaderText(titre);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
