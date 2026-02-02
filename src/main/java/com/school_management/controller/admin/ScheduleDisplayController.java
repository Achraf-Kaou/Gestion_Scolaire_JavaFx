package com.school_management.controller.admin;

import com.school_management.model.*;
import com.school_management.service.*;
import com.school_management.utils.ScheduleUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ScheduleDisplayController {

    @FXML private ComboBox<EmploiDuTemps> cbEmploiDuTemps;
    @FXML private GridPane scheduleGrid;
    @FXML private Label lblTitle;

    private final EmploiDuTempsService emploiDuTempsService = new EmploiDuTempsServiceImpl();
    private final ClasseService classeService = new ClasseServiceImpl();
    private final SeanceService seanceService = new SeanceServiceImpl();
    private final MatiereService matiereService = new MatiereServiceImpl();
    private final EnseignantService enseignantService = new EnseignantServiceImpl();
    
    private final ObservableList<EmploiDuTemps> emploisDuTemps = FXCollections.observableArrayList();
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private final String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
    private final String[] heures = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};

    @FXML
    public void initialize() {
        try {
            List<EmploiDuTemps> edtList = emploiDuTempsService.lireTous();
            for (EmploiDuTemps edt : edtList) {
                if (edt.getClasseId() != null) {
                    edt.setClasse(classeService.lireParId(edt.getClasseId()));
                }
            }
            emploisDuTemps.setAll(edtList);
        } catch (Exception e) {
            alert("Erreur de chargement", "Impossible de charger les emplois du temps : " + e.getMessage());
        }

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

        cbEmploiDuTemps.setOnAction(e -> displaySchedule());
        
        initializeGrid();
    }

    private void initializeGrid() {
        scheduleGrid.getChildren().clear();
        scheduleGrid.getColumnConstraints().clear();
        scheduleGrid.getRowConstraints().clear();

        // Add column constraints - now hours are columns
        ColumnConstraints dayColumn = new ColumnConstraints();
        dayColumn.setMinWidth(100);
        dayColumn.setPrefWidth(100);
        scheduleGrid.getColumnConstraints().add(dayColumn);
        
        for (int i = 0; i < heures.length; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setMinWidth(120);
            cc.setPrefWidth(120);
            cc.setHgrow(Priority.ALWAYS);
            scheduleGrid.getColumnConstraints().add(cc);
        }

        // Add row constraints - now days are rows
        for (int i = 0; i <= jours.length; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setMinHeight(60);
            rc.setPrefHeight(60);
            rc.setVgrow(Priority.ALWAYS);
            scheduleGrid.getRowConstraints().add(rc);
        }

        // Add header corner cell
        Label cornerHeader = new Label("Jour");
        cornerHeader.setStyle("-fx-font-weight: bold; -fx-background-color: #34495e; -fx-text-fill: white; -fx-alignment: center;");
        cornerHeader.setMaxWidth(Double.MAX_VALUE);
        cornerHeader.setMaxHeight(Double.MAX_VALUE);
        cornerHeader.setAlignment(Pos.CENTER);
        GridPane.setFillWidth(cornerHeader, true);
        GridPane.setFillHeight(cornerHeader, true);
        scheduleGrid.add(cornerHeader, 0, 0);

        // Add header row with hours (horizontal)
        for (int i = 0; i < heures.length; i++) {
            Label hourLabel = new Label(heures[i]);
            hourLabel.setStyle("-fx-font-weight: bold; -fx-background-color: #3498db; -fx-text-fill: white; -fx-alignment: center;");
            hourLabel.setMaxWidth(Double.MAX_VALUE);
            hourLabel.setMaxHeight(Double.MAX_VALUE);
            hourLabel.setAlignment(Pos.CENTER);
            GridPane.setFillWidth(hourLabel, true);
            GridPane.setFillHeight(hourLabel, true);
            scheduleGrid.add(hourLabel, i + 1, 0);
        }

        // Add day rows (vertical)
        for (int i = 0; i < jours.length; i++) {
            Label dayLabel = new Label(jours[i]);
            dayLabel.setStyle("-fx-background-color: #ecf0f1; -fx-alignment: center; -fx-font-weight: bold;");
            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setMaxHeight(Double.MAX_VALUE);
            dayLabel.setAlignment(Pos.CENTER);
            GridPane.setFillWidth(dayLabel, true);
            GridPane.setFillHeight(dayLabel, true);
            scheduleGrid.add(dayLabel, 0, i + 1);

            // Add empty cells for each hour
            for (int j = 0; j < heures.length; j++) {
                Label emptyCell = new Label("");
                emptyCell.setStyle("-fx-background-color: white; -fx-border-color: #bdc3c7; -fx-border-width: 1;");
                emptyCell.setMaxWidth(Double.MAX_VALUE);
                emptyCell.setMaxHeight(Double.MAX_VALUE);
                GridPane.setFillWidth(emptyCell, true);
                GridPane.setFillHeight(emptyCell, true);
                scheduleGrid.add(emptyCell, j + 1, i + 1);
            }
        }
    }

    private void displaySchedule() {
        EmploiDuTemps selected = cbEmploiDuTemps.getValue();
        if (selected == null) {
            return;
        }

        initializeGrid();

        if (selected.getClasse() != null) {
            lblTitle.setText("Emploi du Temps - " + selected.getClasse().getCode() + " - " + 
                           selected.getAnneeScolaire() + " (" + selected.getSemestre() + ")");
        }

        try {
            List<Seance> seances = seanceService.lireParEmploiDuTemps(selected.getId());
            
            // Load related objects
            for (Seance seance : seances) {
                if (seance.getMatiereId() != null) {
                    seance.setMatiere(matiereService.listeMatieresId(seance.getMatiereId()));
                }
                if (seance.getEnseignantId() != null) {
                    seance.setEnseignant(enseignantService.lireParId(seance.getEnseignantId()));
                }
            }

            // Place seances in the grid - now column is hour, row is day
            for (Seance seance : seances) {
                int dayIndex = getDayIndex(seance.getJour());
                int timeIndex = getTimeIndex(seance.getHeureDebut());
                
                if (dayIndex >= 0 && timeIndex >= 0) {
                    VBox seanceBox = createSeanceBox(seance);
                    // Swap: column = timeIndex + 1, row = dayIndex + 1
                    scheduleGrid.add(seanceBox, timeIndex + 1, dayIndex + 1);
                    
                    // Calculate and set column span based on duration
                    int columnSpan = ScheduleUtils.calculateColumnSpan(seance);
                    GridPane.setColumnSpan(seanceBox, columnSpan);
                    
                    // Adjust height based on duration
                    double blockHeight = ScheduleUtils.calculateBlockHeight(seance);
                    seanceBox.setPrefHeight(blockHeight);
                }
            }
        } catch (Exception e) {
            alert("Erreur", "Impossible de charger les séances :\n" + e.getMessage());
        }
    }

    private VBox createSeanceBox(Seance seance) {
        VBox box = new VBox(5);
        box.setStyle("-fx-background-color: #2ecc71; -fx-background-radius: 5; -fx-padding: 8; -fx-border-color: #27ae60; -fx-border-width: 2; -fx-border-radius: 5;");
        box.setMaxWidth(Double.MAX_VALUE);
        box.setMaxHeight(Double.MAX_VALUE);
        GridPane.setFillWidth(box, true);
        GridPane.setFillHeight(box, true);

        Label matiereLabel = new Label(seance.getMatiere() != null ? seance.getMatiere().getCode() : "");
        matiereLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-font-size: 11px;");
        matiereLabel.setWrapText(true);

        Label timeLabel = new Label(
            (seance.getHeureDebut() != null ? seance.getHeureDebut().format(timeFormatter) : "") + " - " +
            (seance.getHeureFin() != null ? seance.getHeureFin().format(timeFormatter) : "")
        );
        timeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 10px;");

        Label enseignantLabel = new Label(
            seance.getEnseignant() != null && seance.getEnseignant().getNom() != null && 
            seance.getEnseignant().getPrenom() != null && !seance.getEnseignant().getPrenom().isEmpty() ? 
            seance.getEnseignant().getNom() + " " + seance.getEnseignant().getPrenom().charAt(0) + "." : ""
        );
        enseignantLabel.setStyle("-fx-text-fill: white; -fx-font-size: 10px;");
        enseignantLabel.setWrapText(true);

        Label salleLabel = new Label(seance.getSalle() != null ? "Salle: " + seance.getSalle() : "");
        salleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 9px;");

        box.getChildren().addAll(matiereLabel, timeLabel, enseignantLabel, salleLabel);
        return box;
    }

    private int getDayIndex(String jour) {
        for (int i = 0; i < jours.length; i++) {
            if (jours[i].equalsIgnoreCase(jour)) {
                return i;
            }
        }
        return -1;
    }

    private int getTimeIndex(LocalTime heureDebut) {
        if (heureDebut == null) {
            return -1;
        }
        
        String heureStr = heureDebut.format(timeFormatter);
        for (int i = 0; i < heures.length; i++) {
            if (heures[i].equals(heureStr)) {
                return i;
            }
        }
        
        // If exact match not found, find the closest time slot
        LocalTime targetTime = heureDebut;
        for (int i = 0; i < heures.length - 1; i++) {
            LocalTime slotTime = LocalTime.parse(heures[i], timeFormatter);
            LocalTime nextSlotTime = LocalTime.parse(heures[i + 1], timeFormatter);
            
            if (!targetTime.isBefore(slotTime) && targetTime.isBefore(nextSlotTime)) {
                return i;
            }
        }
        
        return -1;
    }

    private void alert(String titre, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Affichage de l'emploi du temps");
        alert.setHeaderText(titre);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
