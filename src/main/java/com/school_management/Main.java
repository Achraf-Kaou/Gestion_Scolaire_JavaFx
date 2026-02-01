package com.school_management;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

import com.school_management.config.DatabaseConfig;

public class Main extends Application {

    private static Connection connection;

    @Override
    public void start(Stage stage) throws IOException {
        // Établir la connexion au démarrage
        connection = DatabaseConfig.getConnection();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Gestion Scolaire");
        stage.setScene(scene);

        // Fermer la connexion quand la fenêtre se ferme
        stage.setOnCloseRequest(event -> closeApplication());

        stage.show();
    }

    private void closeApplication() {
        System.out.println("Fermeture de l'application...");
        if (connection != null) {
            DatabaseConfig.closeConnection(connection);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
