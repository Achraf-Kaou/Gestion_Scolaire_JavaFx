package com.school_management.controller.enseignant;

import com.school_management.model.Classe;
import com.school_management.model.Enseignant;
import com.school_management.model.Seance;
import com.school_management.service.ClasseServiceImpl;
import com.school_management.service.SeanceServiceImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ScheduleController {

    @FXML
    private TableView<Seance> scheduleTable;

    @FXML
    private TableColumn<Seance, String> jourColumn;

    @FXML
    private TableColumn<Seance, String> heureDebutColumn;

    @FXML
    private TableColumn<Seance, String> heureFinColumn;

    @FXML
    private TableColumn<Seance, String> matiereColumn;

    @FXML
    private TableColumn<Seance, String> classeColumn;

    @FXML
    private TableColumn<Seance, String> salleColumn;

    @FXML
    private TableColumn<Seance, Number> nbEtudiantsColumn;

    private SeanceServiceImpl seanceService = new SeanceServiceImpl();
    private ClasseServiceImpl classeService = new ClasseServiceImpl();

    @FXML
    public void initialize() {
        jourColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getJour()));
        heureDebutColumn.setCellValueFactory(data -> 
            new SimpleStringProperty(data.getValue().getHeureDebut() != null ? data.getValue().getHeureDebut().toString() : ""));
        heureFinColumn.setCellValueFactory(data -> 
            new SimpleStringProperty(data.getValue().getHeureFin() != null ? data.getValue().getHeureFin().toString() : ""));
        matiereColumn.setCellValueFactory(data -> 
            new SimpleStringProperty(data.getValue().getMatiere() != null ? data.getValue().getMatiere().getNom() : "N/A"));
        classeColumn.setCellValueFactory(data -> {
            if (data.getValue().getEmploiDuTemps() != null && data.getValue().getEmploiDuTemps().getClasse() != null) {
                return new SimpleStringProperty(data.getValue().getEmploiDuTemps().getClasse().getNom());
            }
            return new SimpleStringProperty("N/A");
        });
        salleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSalle()));
        nbEtudiantsColumn.setCellValueFactory(data -> {
            if (data.getValue().getEmploiDuTemps() != null && data.getValue().getEmploiDuTemps().getClasse() != null) {
                return new SimpleIntegerProperty(data.getValue().getEmploiDuTemps().getClasse().getEffectif());
            }
            return new SimpleIntegerProperty(0);
        });
    }

    public void setEnseignant(Enseignant enseignant) {
        if (enseignant != null && enseignant.getId() != null) {
            loadSchedule(enseignant.getId());
        }
    }

    private void loadSchedule(Long enseignantId) {
        try {
            List<Seance> seances = seanceService.lireParEnseignant(enseignantId);
            
            // Pre-load all classes to avoid N+1 queries
            for (Seance seance : seances) {
                if (seance.getEmploiDuTemps() != null && seance.getEmploiDuTemps().getClasseId() != null) {
                    Classe classe = classeService.lireParId(seance.getEmploiDuTemps().getClasseId());
                    seance.getEmploiDuTemps().setClasse(classe);
                }
            }
            
            ObservableList<Seance> seanceList = FXCollections.observableArrayList(seances);
            scheduleTable.setItems(seanceList);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showError("Erreur", "Impossible de charger l'emploi du temps: " + e.getMessage());
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.show();
    }
}
