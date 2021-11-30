package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class OwnerWindowController implements EventHandler<MouseEvent> {

    @FXML
    private Label userType;
    @FXML
    private Label userFullName;
    @FXML
    private AnchorPane mainAnchorPane;


    private Owner owner;

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName.setText(userFullName);
    }

    @FXML
    public void initialize() {
        //userFullName.setText("Random Text " + admin.getFirstName());
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
