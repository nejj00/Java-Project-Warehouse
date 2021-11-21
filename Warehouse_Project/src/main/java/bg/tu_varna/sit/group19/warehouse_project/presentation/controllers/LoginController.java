package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.services.LoginServices;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.LoginModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginController implements EventHandler<MouseEvent> {
    @FXML
    private Button ButtonRegister;
    @FXML
    private Button ButtonLogin;
    @FXML
    private Button ButtonCancel;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private Label LabelTryAgain;

    private final LoginModel model;
    private final LoginServices services;
    public static int AccountType;
    public static Admin admin;
    public static Agent agent;
    public static Owner owner;
    URL registerPath = getClass().getResource(Constants.View.REGISTER_VIEW);

    public LoginController() {
        this.model = new LoginModel();
        this.services = new LoginServices();
    }

    @FXML
    private void initialize(){
        ButtonLogin.setOnMouseClicked(this::LoginClicked);
        ButtonRegister.setOnMouseClicked(this::RegisterClicked);
        ButtonCancel.setOnMouseClicked(this::CancelClicked);
    }

    @FXML
    public void LoginClicked(MouseEvent mouseEvent) {
        //check if account exists
        //if not exists - wrong message
        //else
        //save account type and account info
        //closes login stage

        AccountType = 1;
        Node node = (Node) mouseEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        thisStage.hide();
    }

    @FXML
    public void RegisterClicked(MouseEvent mouseEvent) {
        AccountType = 0;
        Node node = (Node) mouseEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();

        Parent root = null;
        try {
            root = FXMLLoader.load(registerPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene registerScene = new Scene(root);
        thisStage.setScene(registerScene);
    }

    @FXML
    public void CancelClicked(MouseEvent mouseEvent) {
        System.exit(0);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
