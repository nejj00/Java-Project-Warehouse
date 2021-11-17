module bg.tuvarna.sit.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires log4j;
    requires org.hibernate.orm.core;
    requires java.persistence;

    requires java.naming;
    requires java.sql;

    opens bg.tuvarna.sit.example.data.enities to org.hibernate.orm.core;
    exports bg.tuvarna.sit.example.data.enities;

    opens bg.tuvarna.sit.example.data.access to org.hibernate.orm.core;
    exports bg.tuvarna.sit.example.data.access;

    exports bg.tuvarna.sit.example.application;
    opens bg.tuvarna.sit.example.application to javafx.fxml;

    exports bg.tuvarna.sit.example.presentation.controlers;
    opens bg.tuvarna.sit.example.presentation.controlers to javafx.fxml;
}