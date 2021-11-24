package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.services.LoginServices;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AccountChecker;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Methods;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.LoginModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    private Label LoginLabel;

    private final LoginModel model;
    private final LoginServices services;
    private final Methods method;
    public static int AccountType;
    public static Admin admin;
    public static Agent agent;
    public static Owner owner;
    URL registerPath = getClass().getResource(Constants.View.REGISTER_VIEW);

    AccountChecker accountChecker = new AccountChecker();

    public LoginController() {
        this.method = new Methods();
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
        String username = this.Username.getText();
        String password = this.Password.getText();

        if(!accountChecker.accountExists(username))
        {
            LoginLabel.setText(model.getWrongMessage());
            return;
        }

        AccountType = 1;
        Stage thisStage = method.getStage(mouseEvent);

        thisStage.hide();
    }

    @FXML
    public void RegisterClicked(MouseEvent mouseEvent) {
        AccountType = 0;
        Stage thisStage = method.getStage(mouseEvent);

        method.ChangeScene(thisStage,registerPath);
    }

    @FXML
    public void CancelClicked(MouseEvent mouseEvent) {
        System.exit(0);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
