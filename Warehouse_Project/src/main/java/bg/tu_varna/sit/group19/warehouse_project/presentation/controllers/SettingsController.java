package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.UserHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.OwnerAccountService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.OwnerService;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AccountChecker;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AlertMessages;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.BaseWindowModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.SettingsModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SettingsController {
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField newPasswordRepeatField;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label newPasswordLabel;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;
    @FXML
    private AnchorPane mainPane;

    public void initialize(){
        confirmButton.setOnMouseClicked(this::ConfirmClicked);
        cancelButton.setOnMouseClicked(this::CancelClicked);
        InitControls();
    }

    private final UserHolder userHolder = UserHolder.getInstance();
    private void InitControls() {
        if(userHolder.getAccountType() == Enums.AccountType.Owner)
        {
            Owner owner = userHolder.getOwner();
            firstNameTextField.setText(owner.getFirstName());
            lastNameTextField.setText(owner.getLastName());
            usernameTextField.setText(owner.getOwnerAccount().getUsername());
        }
    }

    private final BaseWindowModel baseWindowModel = new BaseWindowModel();
    private final SettingsModel settingsModel = new SettingsModel();
    private final AccountChecker accountChecker = new AccountChecker();
    private void ConfirmClicked(MouseEvent mouseEvent) {
        String defaultUsername = "";
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String newPassword = "", newPasswordRepeat = "";
        boolean hasNewPassword = false;
        Object user = null;

        switch (userHolder.getAccountType()) {
            case Owner -> {
                user = userHolder.getOwner();
                defaultUsername = userHolder.getOwner().getOwnerAccount().getUsername();
            }
            case Agent -> defaultUsername = userHolder.getAgent().getAgentAccount().getUsername();
        }

        if(firstNameTextField.getText().trim().isEmpty() || lastNameTextField.getText().trim().isEmpty() ||
            usernameTextField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty())
        {
            AlertMessages.alertWarningMessage(baseWindowModel.getAlertTitle(), baseWindowModel.getAlertEmptyFields());
            return;
        }

        if(accountChecker.accountExists(username) && !username.equals(defaultUsername)) {
            AlertMessages.alertWarningMessage(settingsModel.getAlertTitle(), settingsModel.getAlertUsername());
            return;
        }

        if(!accountChecker.checkPasswordSettings(user, username, password, userHolder.getAccountType())){
            passwordLabel.setText(settingsModel.getLabelPassword());
            return;
        }

        if(!newPasswordField.getText().trim().isEmpty()) {
            hasNewPassword = true;
            newPassword = newPasswordField.getText();
            newPasswordRepeat = newPasswordRepeatField.getText();
            if(!newPasswordField.getText().trim().equals(newPasswordRepeatField.getText().trim())) {
                newPasswordLabel.setText(settingsModel.getLabelNewPassword());
                return;
            }
        }

        switch (userHolder.getAccountType())
        {
            case Agent -> updateAgentProfile();
            case Owner -> updateOwnerProfile(firstName, lastName, username, newPassword, hasNewPassword);
        }

        AlertMessages.alertInformationMessage(settingsModel.getAlertTitle(), settingsModel.getAlertHeader(), settingsModel.getAlertProfileUpdated());

        mainPane.getChildren().clear();
    }

    private final OwnerService ownerService = OwnerService.getInstance();
    private final OwnerAccountService ownerAccountService = OwnerAccountService.getInstance();
    private void updateOwnerProfile(String firstName, String lastName, String username, String newPassword, boolean hasNewPassword) {
        Owner owner = userHolder.getOwner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.getOwnerAccount().setUsername(username);
        if(hasNewPassword)
            owner.getOwnerAccount().setPassword(newPassword);

        ownerService.updateOwner(owner);
        ownerAccountService.updateOwnerAccount(owner.getOwnerAccount());
    }

    private void updateAgentProfile() {

    }

    private void CancelClicked(MouseEvent mouseEvent) {
        mainPane.getChildren().clear();
    }
}
