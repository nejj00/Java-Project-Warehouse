package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AgentWindowController implements EventHandler<MouseEvent> {

    @FXML
    private Label userType;
    @FXML
    private Label userFullName;
    @FXML
    private AnchorPane mainAnchorPane;


    private Agent agent;

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName.setText(userFullName);
    }

    @FXML
    public void initialize() {
        //userFullName.setText("Random Text " + admin.getFirstName());
    }

    URL ownerListPath = getClass().getResource(Constants.View.ADMIN_OWNERS_LIST_VIEW);
/*
    public void showOwnersClicked(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(Constants.View.ADMIN_OWNERS_LIST_VIEW));
        mainAnchorPane.getChildren().setAll(pane);
    }*/

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
