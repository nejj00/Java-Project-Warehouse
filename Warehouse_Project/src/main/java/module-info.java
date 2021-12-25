module bg.tu_varna.sit.group19.warehouse_project {
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

    exports bg.tu_varna.sit.group19.warehouse_project.application;
    opens bg.tu_varna.sit.group19.warehouse_project.application to javafx.fxml;

    exports bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;
    opens bg.tu_varna.sit.group19.warehouse_project.presentation.controllers to javafx.fxml;

    exports bg.tu_varna.sit.group19.warehouse_project.data.access;
    opens bg.tu_varna.sit.group19.warehouse_project.data.access to org.hibernate.orm.core;

    exports bg.tu_varna.sit.group19.warehouse_project.data.entities;
    opens bg.tu_varna.sit.group19.warehouse_project.data.entities to org.hibernate.orm.core;

    exports bg.tu_varna.sit.group19.warehouse_project.presentation.models;

}