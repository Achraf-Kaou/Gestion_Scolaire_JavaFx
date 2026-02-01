package com.school_management.controller.etudiant;

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
import java.util.*;

public class ClassViewController {

    @FXML
    private Label classeLabel;

    @FXML
    private Label specialiteLabel;

    @FXML
    private Label effectifLabel;

    @FXML
    private TableView<Map<String, Object>> teachersTable;

    @FXML
    private TableColumn<Map<String, Object>, String> teacherNomColumn;

    @FXML
    private TableColumn<Map<String, Object>, String> teacherPrenomColumn;

    @FXML
    private TableColumn<Map<String, Object>, String> matiereColumn;

    @FXML
    private TableColumn<Map<String, Object>, String> emailColumn;

    @FXML
    private TableColumn<Map<String, Object>, String> telephoneColumn;

    @FXML
    private TableView<Etudiant> classmatesTable;

    @FXML
    private TableColumn<Etudiant, String> numeroColumn;

    @FXML
    private TableColumn<Etudiant, String> nomColumn;

    @FXML
    private TableColumn<Etudiant, String> prenomColumn;

    @FXML
    private TableColumn<Etudiant, String> studentEmailColumn;

    @FXML
    private TableColumn<Etudiant, String> studentTelephoneColumn;

    private ClasseServiceImpl classeService = new ClasseServiceImpl();
    private EtudiantServiceImpl etudiantService = new EtudiantServiceImpl();
    private SeanceServiceImpl seanceService = new SeanceServiceImpl();

    @FXML
    public void initialize() {
        // Teachers table
        teacherNomColumn.setCellValueFactory(data -> 
            new SimpleStringProperty((String) data.getValue().get("nom")));
        teacherPrenomColumn.setCellValueFactory(data -> 
            new SimpleStringProperty((String) data.getValue().get("prenom")));
        matiereColumn.setCellValueFactory(data -> 
            new SimpleStringProperty((String) data.getValue().get("matiere")));
        emailColumn.setCellValueFactory(data -> 
            new SimpleStringProperty((String) data.getValue().get("email")));
        telephoneColumn.setCellValueFactory(data -> 
            new SimpleStringProperty((String) data.getValue().get("telephone")));

        // Classmates table
        numeroColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumeroEtudiant()));
        nomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        prenomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
        studentEmailColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        studentTelephoneColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelephone()));
    }

    public void setEtudiant(Etudiant etudiant) {
        if (etudiant != null && etudiant.getClasseId() != null) {
            loadClassInfo(etudiant);
        }
    }

    private void loadClassInfo(Etudiant etudiant) {
        try {
            // Load class information
            Classe classe = classeService.lireParId(etudiant.getClasseId());
            if (classe != null) {
                classeLabel.setText(classe.getNom());
                specialiteLabel.setText(classe.getSpecialite() != null ? classe.getSpecialite().getNom() : "N/A");
                effectifLabel.setText(String.valueOf(classe.getEffectif()));
                
                // Load teachers for this class
                loadTeachers(etudiant.getClasseId());
                
                // Load classmates
                loadClassmates(etudiant);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showError("Erreur", "Impossible de charger les informations de la classe: " + e.getMessage());
        }
    }

    private void loadTeachers(Long classeId) {
        try {
            List<Seance> seances = seanceService.lireParClasse(classeId);
            
            // Create a map to store unique teachers with their subjects
            Map<Long, Map<String, Object>> teachersMap = new HashMap<>();
            
            for (Seance seance : seances) {
                if (seance.getEnseignant() != null && seance.getMatiere() != null) {
                    Enseignant enseignant = seance.getEnseignant();
                    Long enseignantId = enseignant.getId();
                    
                    if (!teachersMap.containsKey(enseignantId)) {
                        Map<String, Object> teacherInfo = new HashMap<>();
                        teacherInfo.put("nom", enseignant.getNom());
                        teacherInfo.put("prenom", enseignant.getPrenom());
                        teacherInfo.put("email", enseignant.getEmail());
                        teacherInfo.put("telephone", enseignant.getTelephone());
                        
                        // Collect all subjects for this teacher
                        Set<String> matieres = new HashSet<>();
                        matieres.add(seance.getMatiere().getNom());
                        teacherInfo.put("matieres", matieres);
                        
                        teachersMap.put(enseignantId, teacherInfo);
                    } else {
                        // Add this subject to the existing teacher
                        @SuppressWarnings("unchecked")
                        Set<String> matieres = (Set<String>) teachersMap.get(enseignantId).get("matieres");
                        matieres.add(seance.getMatiere().getNom());
                    }
                }
            }
            
            // Convert to list for table display
            List<Map<String, Object>> teachersList = new ArrayList<>();
            for (Map<String, Object> teacherInfo : teachersMap.values()) {
                @SuppressWarnings("unchecked")
                Set<String> matieres = (Set<String>) teacherInfo.get("matieres");
                teacherInfo.put("matiere", String.join(", ", matieres));
                teachersList.add(teacherInfo);
            }
            
            ObservableList<Map<String, Object>> teachersObservableList = FXCollections.observableArrayList(teachersList);
            teachersTable.setItems(teachersObservableList);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void loadClassmates(Etudiant currentEtudiant) {
        try {
            List<Etudiant> allStudents = etudiantService.lireParClasse(currentEtudiant.getClasseId());
            
            // Remove current student from the list
            allStudents.removeIf(e -> e.getId().equals(currentEtudiant.getId()));
            
            ObservableList<Etudiant> classmatesList = FXCollections.observableArrayList(allStudents);
            classmatesTable.setItems(classmatesList);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.show();
    }
}
