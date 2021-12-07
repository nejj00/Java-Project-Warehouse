package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
