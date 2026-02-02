package com.school_management.controller.etudiant;

import com.school_management.model.Etudiant;
import com.school_management.model.Seance;
import com.school_management.service.SeanceServiceImpl;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EtudiantScheduleController {

    @FXML
    private GridPane scheduleGrid;

    @FXML
    private Label lblClassInfo;

    private SeanceServiceImpl seanceService = new SeanceServiceImpl();
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private final String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
    private final String[] heures = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};

    @FXML
    public void initialize() {
        initializeGrid();
    }

    public void setEtudiant(Etudiant etudiant) {
        if (etudiant != null && etudiant.getId() != null) {
            if (etudiant.getClasse() != null) {
                lblClassInfo.setText("Classe: " + etudiant.getClasse().getCode() + " - " + etudiant.getClasse().getNom());
            }
            loadSchedule(etudiant.getId());
        }
    }

    private void initializeGrid() {
        scheduleGrid.getChildren().clear();
        scheduleGrid.getColumnConstraints().clear();
        scheduleGrid.getRowConstraints().clear();

        // Add column constraints - hours are columns
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

        // Add row constraints - days are rows
        for (int i = 0; i <= jours.length; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setMinHeight(60);
            rc.setPrefHeight(60);
            rc.setVgrow(Priority.ALWAYS);
            scheduleGrid.getRowConstraints().add(rc);
        }

        // Add header corner cell
        Label cornerHeader = new Label("Jour");
        cornerHeader.setStyle("-fx-font-weight: bold; -fx-background-color: #16A085; -fx-text-fill: white; -fx-alignment: center;");
        cornerHeader.setMaxWidth(Double.MAX_VALUE);
        cornerHeader.setMaxHeight(Double.MAX_VALUE);
        cornerHeader.setAlignment(Pos.CENTER);
        GridPane.setFillWidth(cornerHeader, true);
        GridPane.setFillHeight(cornerHeader, true);
        scheduleGrid.add(cornerHeader, 0, 0);

        // Add header row with hours (horizontal)
        for (int i = 0; i < heures.length; i++) {
            Label hourLabel = new Label(heures[i]);
            hourLabel.setStyle("-fx-font-weight: bold; -fx-background-color: #1ABC9C; -fx-text-fill: white; -fx-alignment: center;");
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
            dayLabel.setStyle("-fx-background-color: #D5F4E6; -fx-alignment: center; -fx-font-weight: bold;");
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

    private void loadSchedule(Long etudiantId) {
        try {
            List<Seance> seances = seanceService.lireParEtudiant(etudiantId);
            
            initializeGrid();
            
            // Place seances in the grid
            for (Seance seance : seances) {
                int dayIndex = getDayIndex(seance.getJour());
                int timeIndex = getTimeIndex(seance.getHeureDebut());
                
                if (dayIndex >= 0 && timeIndex >= 0) {
                    VBox seanceBox = createSeanceBox(seance);
                    scheduleGrid.add(seanceBox, timeIndex + 1, dayIndex + 1);
                    
                    // Calculate column span based on duration
                    int columnSpan = calculateColumnSpan(seance);
                    if (columnSpan > 1) {
                        GridPane.setColumnSpan(seanceBox, columnSpan);
                    }
                    
                    // Adjust height based on duration
                    double durationInHours = getDurationInHours(seance);
                    if (durationInHours > 0) {
                        seanceBox.setPrefHeight(60 * durationInHours);
                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showError("Erreur", "Impossible de charger l'emploi du temps: " + e.getMessage());
        }
    }

    private VBox createSeanceBox(Seance seance) {
        VBox box = new VBox(5);
        box.setStyle("-fx-background-color: #16A085; -fx-background-radius: 5; -fx-padding: 8; -fx-border-color: #138D75; -fx-border-width: 2; -fx-border-radius: 5;");
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

    private double getDurationInHours(Seance seance) {
        if (seance.getHeureDebut() == null || seance.getHeureFin() == null) {
            return 1.0;
        }
        
        long minutes = java.time.Duration.between(seance.getHeureDebut(), seance.getHeureFin()).toMinutes();
        return minutes / 60.0;
    }

    private int calculateColumnSpan(Seance seance) {
        double durationInHours = getDurationInHours(seance);
        // Round up to the nearest hour
        // 1 hour = 1 column, 1.5 hours = 2 columns, 2 hours = 2 columns
        return (int) Math.ceil(durationInHours);
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.show();
    }
}
