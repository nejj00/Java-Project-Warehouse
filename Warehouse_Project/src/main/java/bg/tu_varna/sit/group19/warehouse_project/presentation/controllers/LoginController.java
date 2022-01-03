package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.AgentHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.AdminAccountService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.AgentAccountService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.OwnerAccountService;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AccountChecker;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.ScenePaneSwitcher;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.LoginModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private Label LoginLabel;

    private final LoginModel model;
    private final ScenePaneSwitcher method;
    public static Enums accountType = new Enums();
    public static Admin admin;
    public static Agent agent;
    public static Owner owner;

    URL registerPath = getClass().getResource(Constants.View.REGISTER_VIEW);
    URL adminWindowPath = getClass().getResource(Constants.View.ADMIN_VIEW);

    AccountChecker accountChecker = new AccountChecker();

    public LoginController() {
        this.method = new ScenePaneSwitcher();
        this.model = new LoginModel();
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

        if(!accountChecker.checkPassword(username, password, accountType))
        {
            LoginLabel.setText(model.getWrongMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Account DOES NOT exists or password is INCORRECT");
            alert.showAndWait();
            return;
        }

        Stage thisStage = method.getStage(mouseEvent);
        thisStage.hide();

        switch (accountType.getAccountType()) {
            case Admin -> openAdminWindow(username);
            case Agent -> openAgentWindow(username);
            case Owner -> openOwnerWindow(username);
        }
        /*
        Stage thisStage = method.getStage(mouseEvent);
        //method.ChangeScene(thisStage, userWindowPath);
        thisStage.hide();
        if(accountType.getAccountType() == AccountTypeEnum.AccountType.Admin)
            openAdminWindow(username);
         */
    }

    private void openAdminWindow(String username) {
        Stage myStage = new Stage();

        AdminAccountService adminAccountService = AdminAccountService.getInstance();
        Admin admin = adminAccountService.getAdminByUsername(username);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.View.ADMIN_VIEW));

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AdminWindowController controller = fxmlLoader.<AdminWindowController>getController();
        controller.setUserFullName(admin.getFirstName() + " " + admin.getLastName());
        controller.setAdmin(admin);
        assert root != null;
        Scene scene = new Scene(root);

        myStage.setScene(scene);

        myStage.show();
    }

    private void openAgentWindow(String username) {
        Stage myStage = new Stage();

        AgentAccountService agentAccountService = AgentAccountService.getInstance();
        AgentHolder agentHolder = AgentHolder.getInstance();
        Agent agent = agentAccountService.getAgentByUsername(username);
        agentHolder.setAgent(agent);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.View.AGENT_VIEW));

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AgentWindowController controller = fxmlLoader.<AgentWindowController>getController();
        controller.setUserFullName(agent.getFirstName() + " " + agent.getLastName());
        controller.setAgent(agent);
        assert root != null;
        Scene scene = new Scene(root);

        myStage.setScene(scene);

        myStage.show();
    }

    private void openOwnerWindow(String username) {
        Stage myStage = new Stage();

        OwnerAccountService ownerAccountService = OwnerAccountService.getInstance();
        Owner owner = ownerAccountService.getOwnerByUsername(username);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.View.OWNER_VIEW));

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OwnerWindowController controller = fxmlLoader.<OwnerWindowController>getController(); //!!!!!!!!! exception
        controller.setUserFullName(owner.getFirstName() + " " + owner.getLastName());
        controller.setOwner(owner);
        assert root != null;
        Scene scene = new Scene(root);

        myStage.setScene(scene);

        myStage.show();
    }

    @FXML
    public void RegisterClicked(MouseEvent mouseEvent) {
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
