package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseHolder;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.ScenePaneSwitcher;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class OwnerWindowController implements EventHandler<MouseEvent> {

    @FXML
    private Label userFullName;
    @FXML
    public Button SettingsButton;
    @FXML
    public Button AddRoomButton;
    @FXML
    public Button ShowContractsButton;
    @FXML
    public Button ShowAvailableWarehousesButton;
    @FXML
    public Button ShowAllWarehousesButton;
    @FXML
    public Button logOutButton;
    @FXML
    public AnchorPane mainAnchorPane;

    private static Owner owner;

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    public void setUserFullName(String userFullName) {
        this.userFullName.setText(userFullName);
    }

    public static int OwnerWindowButtonClicked;

    @FXML
    public void initialize() {
        SettingsButton.setOnMouseClicked(this::Settings);
        AddRoomButton.setOnMouseClicked(this::AddRoom);
        ShowContractsButton.setOnMouseClicked(this::ShowContracts);
        ShowAvailableWarehousesButton.setOnMouseClicked(this::ShowAvailableWarehouses);
        ShowAllWarehousesButton.setOnMouseClicked(this::ShowAllWarehouses);
        logOutButton.setOnMouseClicked(this::LogOutClicked);

        OwnerWindowButtonClicked=0;
    }

    private final URL ListPath = getClass().getResource(Constants.View.WAREHOUSES_LIST_VIEW);
    private final WarehouseHolder warehouseHolder = WarehouseHolder.getInstance();
    @FXML
    public void ShowAllWarehouses(MouseEvent mouseEvent){
        OwnerWindowButtonClicked=Constants.Owner.ShowAllWarehousesClicked;

        AnchorPane pane;
        try {
            pane = FXMLLoader.load(ListPath);
            warehouseHolder.setOwner(owner);
            mainAnchorPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Settings(MouseEvent mouseEvent) {

    }

    @FXML
    public void AddRoom(MouseEvent mouseEvent) {

    }

    @FXML
    public void ShowContracts(MouseEvent mouseEvent) {
        OwnerWindowButtonClicked=Constants.Owner.ShowContractsClicked;

        AnchorPane pane;
        try {
            pane = FXMLLoader.load(ListPath);
            mainAnchorPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ShowAvailableWarehouses(MouseEvent mouseEvent) {
        OwnerWindowButtonClicked=Constants.Owner.ShowAvailableWarehousesClicked;

        AnchorPane pane;
        try {
            pane = FXMLLoader.load(ListPath);
            mainAnchorPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LogOutClicked(MouseEvent mouseEvent) {
        ScenePaneSwitcher method = new ScenePaneSwitcher();
        URL loginPath = getClass().getResource(Constants.View.LOGIN_VIEW);
        Stage thisStage = method.getStage(mouseEvent);
        method.ChangeScene(thisStage, loginPath);
    }

    public static Owner getOwner() {
        return owner;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
