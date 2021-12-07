package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class OwnerWindowController implements EventHandler<MouseEvent> {

    @FXML
    private Label userFullName;
    @FXML
    public Button SettingsButton;
    @FXML
    public Button CreateWarehouseButton;
    @FXML
    public Button EditWarehouseButton;
    @FXML
    public Button AddRoomButton;
    @FXML
    public Button ShowContractsButton;
    @FXML
    public Button ShowAvailableWarehousesButton;
    @FXML
    public Button ShowAllWarehousesButton;


    private Owner owner;

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName.setText(userFullName);
    }

    @FXML
    public void initialize() {
        SettingsButton.setOnMouseClicked(this::Settings);
        CreateWarehouseButton.setOnMouseClicked(this::CreateWarehouse);
        EditWarehouseButton.setOnMouseClicked(this::EditWarehouse);
        AddRoomButton.setOnMouseClicked(this::AddRoom);
        ShowContractsButton.setOnMouseClicked(this::ShowContracts);
        ShowAvailableWarehousesButton.setOnMouseClicked(this::ShowAvailableWarehouses);
        ShowAllWarehousesButton.setOnMouseClicked(this::ShowAllWarehouses);
    }

    @FXML
    public void Settings(MouseEvent mouseEvent) {

    }

    @FXML
    public void CreateWarehouse(MouseEvent mouseEvent) {

    }

    @FXML
    public void EditWarehouse(MouseEvent mouseEvent) {

    }

    @FXML
    public void AddRoom(MouseEvent mouseEvent) {

    }

    @FXML
    public void ShowContracts(MouseEvent mouseEvent) {

    }

    @FXML
    public void ShowAvailableWarehouses(MouseEvent mouseEvent) {

    }

    @FXML
    public void ShowAllWarehouses(MouseEvent mouseEvent) {

    }


    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
