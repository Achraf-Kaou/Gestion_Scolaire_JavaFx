package com.school_management.controller.enseignant;

import com.school_management.model.Classe;
import com.school_management.model.Enseignant;
import com.school_management.model.Etudiant;
import com.school_management.model.Seance;
import com.school_management.service.ClasseServiceImpl;
import com.school_management.service.EtudiantServiceImpl;
import com.school_management.service.SeanceServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentsController {

    @FXML
    private TableView<Etudiant> studentsTable;

    @FXML
    private TableColumn<Etudiant, String> numeroColumn;

    @FXML
    private TableColumn<Etudiant, String> nomColumn;

    @FXML
    private TableColumn<Etudiant, String> prenomColumn;

    @FXML
    private TableColumn<Etudiant, String> classeColumn;

    @FXML
    private TableColumn<Etudiant, String> emailColumn;

    @FXML
    private TableColumn<Etudiant, String> telephoneColumn;

    private SeanceServiceImpl seanceService = new SeanceServiceImpl();
    private EtudiantServiceImpl etudiantService = new EtudiantServiceImpl();
    private ClasseServiceImpl classeService = new ClasseServiceImpl();

    @FXML
    public void initialize() {
        numeroColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumeroEtudiant()));
        nomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        prenomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
        classeColumn.setCellValueFactory(data -> {
            if (data.getValue().getClasse() != null) {
                return new SimpleStringProperty(data.getValue().getClasse().getNom());
            }
            return new SimpleStringProperty("N/A");
        });
        emailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        telephoneColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelephone()));
    }

    public void setEnseignant(Enseignant enseignant) {
        if (enseignant != null && enseignant.getId() != null) {
            loadStudents(enseignant.getId());
        }
    }

    private void loadStudents(Long enseignantId) {
        try {
            // Get all seances for this teacher
            List<Seance> seances = seanceService.lireParEnseignant(enseignantId);
            
            // Get unique classes from seances
            Set<Long> classeIds = new HashSet<>();
            for (Seance seance : seances) {
                if (seance.getEmploiDuTemps() != null && seance.getEmploiDuTemps().getClasseId() != null) {
                    classeIds.add(seance.getEmploiDuTemps().getClasseId());
                }
            }
            
            // Get all students from these classes
            Set<Etudiant> allStudents = new HashSet<>();
            for (Long classeId : classeIds) {
                List<Etudiant> etudiants = etudiantService.lireParClasse(classeId);
                // Pre-load classe for each student
                Classe classe = classeService.lireParId(classeId);
                for (Etudiant etudiant : etudiants) {
                    etudiant.setClasse(classe);
                }
                allStudents.addAll(etudiants);
            }
            
            ObservableList<Etudiant> studentList = FXCollections.observableArrayList(allStudents);
            studentsTable.setItems(studentList);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showError("Erreur", "Impossible de charger la liste des étudiants: " + e.getMessage());
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.show();
    }
}
