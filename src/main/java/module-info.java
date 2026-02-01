module com.school_management.school_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires mysql.connector.j;
    requires jdk.jdi;

    opens com.school_management to javafx.fxml;
    opens com.school_management.controller to javafx.fxml;

    opens database;
    opens database.etudiant;
    opens database.enseignant;
    opens database.specialite;
    opens database.matiere;

    exports com.school_management;
    exports com.school_management.controller;
    exports com.school_management.controller.admin;
    opens com.school_management.controller.admin to javafx.fxml;

}