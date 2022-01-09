package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.AgentHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.UserHolder;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.ScenePaneSwitcher;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Renter;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.OwnerRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.RenterRepository;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
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
    private final AgentHolder agentHolder = AgentHolder.getInstance();

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
    public void setUserFullName(String userFullName) {
        this.userFullName.setText(userFullName);
    }

    private final URL ListPath = getClass().getResource(Constants.View.AGENT_LIST_VIEW);
    public static int AgentWindowButtonClicked;
    public static Owner owner;
    public static Renter renter;

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
        AgentWindowButtonClicked = Constants.AgentConstants.ShowAvailableWarehousesClicked;

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
        AgentWindowButtonClicked = Constants.AgentConstants.ShowOwnersWarehousesClicked;

        TextInputDialog inputdialog = new TextInputDialog("Enter owner name and family");
        inputdialog.setContentText("Here: ");
        inputdialog.setHeaderText("Input");
        inputdialog.showAndWait();
        String name = inputdialog.getEditor().getText();
        Owner owner = OwnerRepository.getInstance().getByName(name);

        if(owner==null) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Owner with that name does not exist.");
            a.showAndWait();
            return;
        }

        this.owner = owner;

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
        AgentWindowButtonClicked = Constants.AgentConstants.ShowRenterHistoryClicked;

        TextInputDialog inputdialog = new TextInputDialog("Enter renter name and family");
        inputdialog.setContentText("Here: ");
        inputdialog.setHeaderText("Input");
        inputdialog.showAndWait();
        String name = inputdialog.getEditor().getText();
        Renter renter = RenterRepository.getInstance().getByName(name);

        if(renter==null) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Owner with that name does not exist.");
            a.showAndWait();
            return;
        }

        this.renter = renter;

        AnchorPane pane;
        try {
            pane = FXMLLoader.load(ListPath);

            mainAnchorPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final UserHolder userHolder = UserHolder.getInstance();

    @FXML
    public void Settings(MouseEvent mouseEvent){
        URL pathSettings = getClass().getResource(Constants.View.SETTINGS_VIEW);
        userHolder.setAccountType(Enums.AccountType.Agent);
        userHolder.setAgent(agent);

        AnchorPane pane = ScenePaneSwitcher.getPaneToSwitchTo(pathSettings);
        mainAnchorPane.getChildren().setAll(pane);
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
