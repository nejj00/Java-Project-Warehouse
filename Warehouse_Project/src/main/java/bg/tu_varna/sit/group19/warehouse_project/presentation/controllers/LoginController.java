package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.presentation.models.LoginModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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

    public LoginController() {
        this.model = new LoginModel();
    }

    @FXML
    private void initialize(){
        ButtonLogin.setOnMouseClicked(this::test);
    }


    @FXML
    public void test(MouseEvent mouseEvent) {
        LabelTryAgain.setText("test");
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        LabelTryAgain.setText(model.getWrongMessage());
    }
}
