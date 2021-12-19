package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class ContractController {
    @FXML
    private TextField Price;
    @FXML
    private DatePicker FromDate;
    @FXML
    private DatePicker ToDate;
    @FXML
    private Button CreateButton;
    @FXML
    private Button CancelButton;
    @FXML
    private TextField RenterName;
    @FXML
    private TextField RenterFamily;
    @FXML
    private TextField RenterPhoneNumber;
    @FXML
    private Label AgentName;
    @FXML
    private Label OwnerName;
    @FXML
    private ComboBox WarehouseCombobox;
    @FXML
    private ComboBox RoomCombobox;

    @FXML
    private void initialize(){
        CreateButton.setOnMouseClicked(this::CreateClicked);
        CancelButton.setOnMouseClicked(this::CancelClicked);
    }

    @FXML
    private void CreateClicked(MouseEvent mouseEvent) {

    }

    @FXML
    private void CancelClicked(MouseEvent mouseEvent) {

    }
}
