package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.EnumHolder;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AdminWindowController implements EventHandler<MouseEvent> {

    @FXML
    private Label userType;
    @FXML
    private Label userFullName;
    @FXML
    private AnchorPane mainAnchorPane;

    private Admin admin;

    private EnumHolder enumHolder = EnumHolder.getInstance();

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName.setText(userFullName);
    }

    @FXML
    public void initialize() {
        //userFullName.setText("Random Text " + admin.getFirstName());
    }

    URL userListPath = getClass().getResource(Constants.View.ADMIN_OWNERS_LIST_VIEW);

    public void showOwnersClicked(MouseEvent mouseEvent) throws IOException {
        enumHolder.setAccountType(Enums.AccountType.Owner);
        AnchorPane pane = FXMLLoader.load(userListPath);
        mainAnchorPane.getChildren().setAll(pane);
    }

    public void showAgentsClicked(MouseEvent mouseEvent) throws IOException {
        enumHolder.setAccountType(Enums.AccountType.Agent);
        AnchorPane pane = FXMLLoader.load(userListPath);
        mainAnchorPane.getChildren().setAll(pane);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
