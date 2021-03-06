package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.EnumHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.AgentAccountService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.AgentService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.OwnerAccountService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.OwnerService;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AccountChecker;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.ScenePaneSwitcher;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.AgentAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.RegisterModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class RegisterController implements EventHandler<MouseEvent> {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Button registerButton;
    @FXML
    private Button cancelButton;
    @FXML
    private RadioButton ownerRadioButton;
    @FXML
    private RadioButton agentRadioButton;
    @FXML
    private Label registerLabel;
    @FXML
    private AnchorPane imagePane;
    @FXML
    private BorderPane mainPane;
    @FXML
    private AnchorPane registerPane;
    @FXML
    private EnumHolder enumHolder = EnumHolder.getInstance();

    Enums.OpenMode openMode = Enums.OpenMode.RegularMode;

    long userID;

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setOpenMode(Enums.OpenMode openMode) {
        this.openMode = openMode;
    }

    public RadioButton getOwnerRadioButton() {
        return ownerRadioButton;
    }

    public RadioButton getAgentRadioButton() {
        return agentRadioButton;
    }

    public BorderPane getMainPane() {
        return mainPane;
    }

    public AnchorPane getImagePane() {
        return imagePane;
    }

    public AnchorPane getRegisterPane() {
        return registerPane;
    }

    public TextField getFirstName() {
        return firstName;
    }

    public TextField getLastName() {
        return lastName;
    }

    public TextField getUsername() {
        return username;
    }

    URL loginPath = getClass().getResource(Constants.View.LOGIN_VIEW);
    private final RegisterModel model;
    private final ScenePaneSwitcher scenePaneSwitcher;

    OwnerService ownerService = OwnerService.getInstance();
    OwnerAccountService ownerAccountService = OwnerAccountService.getInstance();
    AgentService agentService = AgentService.getInstance();
    AgentAccountService agentAccountService = AgentAccountService.getInstance();

    AccountChecker accountChecker = new AccountChecker();

    public RegisterController() {
        this.model=new RegisterModel();
        this.scenePaneSwitcher =new ScenePaneSwitcher();
    }

    @FXML
    private void initialize(){
        registerButton.setOnMouseClicked(this::RegisterClicked);
        cancelButton.setOnMouseClicked(this::CancelClicked);

    }

    @FXML
    public void RegisterClicked(MouseEvent mouseEvent) {
        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String username = this.username.getText();
        String password = this.password.getText();
        String confPassword = this.confirmPassword.getText();

        if(!password.equals(confPassword)){
            registerLabel.setText(model.getWrongPasswordMessage());
            this.password.clear();
            this.confirmPassword.clear();
        }
        else if(username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()){
            registerLabel.setText(model.getEmptyFieldMessage());
        }
        else if(!agentRadioButton.isSelected() && !ownerRadioButton.isSelected()){
            registerLabel.setText(model.getSelectAccountTypeMessage());
        }
        else{
            //call register func from register service
            if(accountChecker.accountExists(username) && openMode == Enums.OpenMode.RegularMode)
            {
                registerLabel.setText(model.getUsernameExists());
                return;
            }

            if(ownerRadioButton.isSelected())
            {
                if(openMode == Enums.OpenMode.UpdateMode && enumHolder.getAccountType() == Enums.AccountType.Owner)
                    updateOwnerToDB(userID, firstName, lastName, username, password);
                else
                    addOwnerToDB(firstName, lastName, username, password);
            }

            if(agentRadioButton.isSelected())
            {
                if(openMode == Enums.OpenMode.UpdateMode && enumHolder.getAccountType() == Enums.AccountType.Agent)
                    updateAgentToDB(userID, firstName, lastName, username, password);
                else
                    addAgentToDB(firstName, lastName, username, password);
            }

            successfulRegistrationAlert();

            Stage thisStage = scenePaneSwitcher.getStage(mouseEvent);

            if(openMode == Enums.OpenMode.InsertMode || openMode == Enums.OpenMode.UpdateMode) {
                AnchorPane anchorPane = getListViewPane();
                registerPane.getChildren().setAll(anchorPane);
            }
            else
                scenePaneSwitcher.ChangeScene(thisStage, loginPath);
        }
    }

    private void updateOwnerToDB(long ID, String firstName, String lastName, String username, String password) {
        Owner owner = ownerService.getOwnerById(ID);
        owner.setFirstName(firstName);
        owner.setLastName(lastName);

        ownerService.updateOwner(owner);

        OwnerAccount ownerAccount = ownerAccountService.getOwnerAccountById(owner.getOwnerAccount().getId());
        ownerAccount.setUsername(username);
        ownerAccount.setPassword(password);
        ownerAccount.setOwner(owner);

        ownerAccountService.updateOwnerAccount(ownerAccount);
    }

    private void updateAgentToDB(long ID, String firstName, String lastName, String username, String password) {
        Agent agent = agentService.getAgentById(ID);
        agent.setFirstName(firstName);
        agent.setLastName(lastName);

        agentService.updateAgent(agent);

        AgentAccount agentAccount = agentAccountService.getAgentAccountById(agent.getAgentAccount().getId());
        agentAccount.setUsername(username);
        agentAccount.setPassword(password);
        agentAccount.setAgent(agent);

        agentAccountService.updateAgentAccount(agentAccount);
    }

    private void successfulRegistrationAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration message!");
        alert.setHeaderText("Congratulations");
        alert.setContentText("You registered successfully.");
        alert.showAndWait();
    }

    private void addAgentToDB(String firstName, String lastName, String username, String password) {
        Agent agent = new Agent();
        agent.setFirstName(firstName);
        agent.setLastName(lastName);
        agent.setRating(0);

        agentService.insertAgent(agent);

        AgentAccount agentAccount = new AgentAccount();
        agentAccount.setUsername(username);
        agentAccount.setPassword(password);
        agentAccount.setAgent(agent);

        agentAccountService.insertAgentAccount(agentAccount);
    }

    private void addOwnerToDB(String firstName, String lastName, String username, String password) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);

        ownerService.insertOwner(owner);

        OwnerAccount ownerAccount = new OwnerAccount();
        ownerAccount.setUsername(username);
        ownerAccount.setPassword(password);
        ownerAccount.setOwner(owner);

        ownerAccountService.insertOwnerAccount(ownerAccount);
    }

    @FXML
    public void CancelClicked(MouseEvent mouseEvent){
        Stage thisStage = scenePaneSwitcher.getStage(mouseEvent);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.View.ADMIN_OWNERS_LIST_VIEW));
        AnchorPane pane = null;

        if(openMode == Enums.OpenMode.InsertMode || openMode == Enums.OpenMode.UpdateMode) {
            AnchorPane anchorPane = getListViewPane();
            registerPane.getChildren().setAll(anchorPane);
        }
        else
            scenePaneSwitcher.ChangeScene(thisStage, loginPath);
    }

    private AnchorPane getListViewPane() {
        AnchorPane anchorPane = null;
        try{
            anchorPane = FXMLLoader.load(getClass().getResource(Constants.View.ADMIN_OWNERS_LIST_VIEW));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return anchorPane;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
