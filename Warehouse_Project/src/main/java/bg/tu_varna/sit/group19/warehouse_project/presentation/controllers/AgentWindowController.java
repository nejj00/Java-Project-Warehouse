package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.ScenePaneSwitcher;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
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
    @FXML
    private Button LogOutButton;

    private Agent agent;
    private final URL ContractPath = getClass().getResource(Constants.View.CONTRACT_VIEW);

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
    public void setUserFullName(String userFullName) {
        this.userFullName.setText(userFullName);
    }

    private final URL ListPath = getClass().getResource(Constants.View.AGENT_LIST_VIEW);
    public static int AgentWindowButtonClicked;

    @FXML
    public void initialize() {
        ShowAvailableWarehousesButton.setOnMouseClicked(this::ShowAvailableWarehouses);
        GiveWarehouseOnRentButton.setOnMouseClicked(this::GiveWarehouseOnRent);
        ShowOwnersWarehousesButton.setOnMouseClicked((this::ShowOwnersWarehouses));
        RentHistoryButton.setOnMouseClicked(this::RentHistory);
        SettingsButton.setOnMouseClicked(this::Settings);
        LogOutButton.setOnMouseClicked(this::LogOut);

        AgentWindowButtonClicked=0;
    }

    @FXML
    public void ShowAvailableWarehouses(MouseEvent mouseEvent){
        AgentWindowButtonClicked = Constants.Agent.ShowAvailableWarehousesClicked;

        AnchorPane pane;
        try {
            pane = FXMLLoader.load(ListPath);

            mainAnchorPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void GiveWarehouseOnRent(MouseEvent mouseEvent){
        mainAnchorPane.getChildren().setAll(ScenePaneSwitcher.getPaneToSwitchTo(ContractPath));
    }

    @FXML
    public void ShowOwnersWarehouses(MouseEvent mouseEvent){
        AgentWindowButtonClicked = Constants.Agent.ShowOwnersWarehousesClicked;

        AnchorPane pane;
        try {
            pane = FXMLLoader.load(ListPath);

            mainAnchorPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void LogOut(MouseEvent mouseEvent) {
        ScenePaneSwitcher method = new ScenePaneSwitcher();
        URL loginPath = getClass().getResource(Constants.View.LOGIN_VIEW);
        Stage thisStage = method.getStage(mouseEvent);
        method.ChangeScene(thisStage, loginPath);
    }
}
