package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.services.LoginServices;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.LoginModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.RegisterModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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

    URL loginPath = getClass().getResource(Constants.View.LOGIN_VIEW);
    private RegisterModel model;

    public RegisterController() {
        this.model=new RegisterModel();
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
        else if(username.isEmpty()||firstName.isEmpty()||lastName.isEmpty()||password.isEmpty()){
            registerLabel.setText(model.getEmptyFieldMessage());
        }
        else if(!agentRadioButton.isSelected()&&!ownerRadioButton.isSelected()){
            registerLabel.setText(model.getSelectAccountTypeMessage());
        }
        else{
            //call register func from register service

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration message!");
            alert.setHeaderText("Congratulations");
            alert.setContentText("You registered successfully.");
            alert.showAndWait();

            Node node = (Node) mouseEvent.getSource();//tezi nqkolko reda se povtarqt mnogocratno, ako iskash gi iskarai vuv funciq
            Stage thisStage = (Stage) node.getScene().getWindow();

            Parent root = null;
            try {
                root = FXMLLoader.load(loginPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert root != null;
            Scene loginScene = new Scene(root);
            thisStage.setScene(loginScene);
        }
    }

    @FXML
    public void CancelClicked(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();

        Parent root = null;
        try {
            root = FXMLLoader.load(loginPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene loginScene = new Scene(root);
        thisStage.setScene(loginScene);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
