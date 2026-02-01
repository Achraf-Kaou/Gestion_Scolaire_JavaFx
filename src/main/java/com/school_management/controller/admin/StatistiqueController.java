package com.school_management.controller.admin;

import com.school_management.model.*;
import com.school_management.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatistiqueController {

    @FXML private Label lblNbEtudiants;
    @FXML private Label lblNbEnseignants;
    @FXML private Label lblNbClasses;
    @FXML private Label lblNbMatieres;
    @FXML private Label lblNbSeances;
    @FXML private PieChart pieChartEtudiants;
    @FXML private BarChart<String, Number> barChartMatieres;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;

    private final EtudiantService etudiantService = new EtudiantServiceImpl();
    private final EnseignantService enseignantService = new EnseignantServiceImpl();
    private final ClasseService classeService = new ClasseServiceImpl();
    private final MatiereService matiereService = new MatiereServiceImpl();
    private final SeanceService seanceService = new SeanceServiceImpl();

    @FXML
    public void initialize() {
        loadStatistics();
        loadCharts();
    }

    private void loadStatistics() {
        try {
            int nbEtudiants = etudiantService.lireTous().size();
            lblNbEtudiants.setText(String.valueOf(nbEtudiants));
        } catch (Exception e) {
            lblNbEtudiants.setText("Erreur");
        }

        try {
            int nbEnseignants = enseignantService.lireTous().size();
            lblNbEnseignants.setText(String.valueOf(nbEnseignants));
        } catch (Exception e) {
            lblNbEnseignants.setText("Erreur");
        }

        try {
            int nbClasses = classeService.lireTous().size();
            lblNbClasses.setText(String.valueOf(nbClasses));
        } catch (Exception e) {
            lblNbClasses.setText("Erreur");
        }

        try {
            int nbMatieres = matiereService.listeMatieres().size();
            lblNbMatieres.setText(String.valueOf(nbMatieres));
        } catch (Exception e) {
            lblNbMatieres.setText("Erreur");
        }

        try {
            int nbSeances = seanceService.lireTous().size();
            lblNbSeances.setText(String.valueOf(nbSeances));
        } catch (Exception e) {
            lblNbSeances.setText("Erreur");
        }
    }

    private void loadCharts() {
        loadPieChart();
        loadBarChart();
    }

    private void loadPieChart() {
        try {
            List<Classe> classes = classeService.lireTous();
            List<Etudiant> etudiants = etudiantService.lireTous();
            
            Map<String, Integer> etudiantsParClasse = new HashMap<>();
            
            for (Classe classe : classes) {
                long count = etudiants.stream()
                    .filter(e -> e.getClasseId() != null && e.getClasseId().equals(classe.getId()))
                    .count();
                if (count > 0) {
                    etudiantsParClasse.put(classe.getCode(), (int) count);
                }
            }
            
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (Map.Entry<String, Integer> entry : etudiantsParClasse.entrySet()) {
                pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }
            
            if (!pieChartData.isEmpty()) {
                pieChartEtudiants.setData(pieChartData);
            } else {
                pieChartData.add(new PieChart.Data("Aucune donnée", 1));
                pieChartEtudiants.setData(pieChartData);
            }
        } catch (Exception e) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            pieChartData.add(new PieChart.Data("Erreur de chargement", 1));
            pieChartEtudiants.setData(pieChartData);
        }
    }

    private void loadBarChart() {
        try {
            List<Matiere> matieres = matiereService.listeMatieres();
            List<Seance> seances = seanceService.lireTous();
            
            Map<String, Integer> seancesParMatiere = new HashMap<>();
            
            for (Matiere matiere : matieres) {
                long count = seances.stream()
                    .filter(s -> s.getMatiereId() != null && s.getMatiereId().equals(matiere.getId()))
                    .count();
                seancesParMatiere.put(matiere.getCode(), (int) count);
            }
            
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Nombre de séances");
            
            for (Map.Entry<String, Integer> entry : seancesParMatiere.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
            
            barChartMatieres.getData().clear();
            if (!series.getData().isEmpty()) {
                barChartMatieres.getData().add(series);
            }
        } catch (Exception e) {
            // Chart stays empty on error
        }
    }
}
