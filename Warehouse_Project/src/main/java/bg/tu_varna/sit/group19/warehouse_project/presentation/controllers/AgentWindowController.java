package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
    @FXML
    private Button ShowAvailableWarehousesButton;
    @FXML
    private Button GiveWarehouseOnRentButton;
    @FXML
    private Button ShowOwnersWarehousesButton;
    @FXML
    private Button RentHistoryButton;
    @FXML
    private Button SettingsButton;


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
        ShowAvailableWarehousesButton.setOnMouseClicked(this::ShowAvailableWarehouses);
        GiveWarehouseOnRentButton.setOnMouseClicked(this::GiveWarehouseOnRent);
        ShowOwnersWarehousesButton.setOnMouseClicked((this::ShowOwnersWarehouses));
        RentHistoryButton.setOnMouseClicked(this::RentHistory);
        SettingsButton.setOnMouseClicked(this::Settings);
    }

    @FXML
    public void ShowAvailableWarehouses(MouseEvent mouseEvent){

    }

    @FXML
    public void GiveWarehouseOnRent(MouseEvent mouseEvent){

    }

    @FXML
    public void ShowOwnersWarehouses(MouseEvent mouseEvent){

    }

    @FXML
    public void RentHistory(MouseEvent mouseEvent){

    }

    @FXML
    public void Settings(MouseEvent mouseEvent){

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
