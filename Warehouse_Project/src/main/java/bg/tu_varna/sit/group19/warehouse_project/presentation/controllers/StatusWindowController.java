package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.StatusHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseStatusService;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AlertMessages;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseRoom;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.BaseWindowModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StatusWindowController {
    @FXML
    private TextField statusTextField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    private final StatusHolder statusHolder = StatusHolder.getInstance();

    @FXML
    public void initialize(){
        confirmButton.setOnMouseClicked(this::ConfirmClicked);
        cancelButton.setOnMouseClicked(this::CancelClicked);

        if(statusHolder.getWarehouseStatusOpenMode() == Enums.OpenMode.UpdateMode)
            statusTextField.setText(statusHolder.getWarehouseStatus().getStatus());
    }

    private final BaseWindowModel baseWindowModel = new BaseWindowModel();
    private void ConfirmClicked(MouseEvent mouseEvent) {
        if(statusTextField.getText().trim().isEmpty())
        {
            AlertMessages.alertWarningMessage(baseWindowModel.getAlertTitle(), baseWindowModel.getAlertEmptyFields());
            return;
        }

        String status = statusTextField.getText();

        if(statusHolder.getWarehouseStatusOpenMode() == Enums.OpenMode.InsertMode)
            insertStatusDb(status);
        if(statusHolder.getWarehouseStatusOpenMode() == Enums.OpenMode.UpdateMode)
            updateStatusDb(status);

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    private final WarehouseStatusService warehouseStatusService = WarehouseStatusService.getInstance();
    private void insertStatusDb(String status) {
        WarehouseStatus warehouseStatus = new WarehouseStatus();
        warehouseStatus.setStatus(status);
        warehouseStatusService.insertWarehouseStatus(warehouseStatus);
        statusHolder.setWarehouseStatus(warehouseStatus);
    }

    private void updateStatusDb(String status) {
        WarehouseStatus warehouseStatus = new WarehouseStatus();
        warehouseStatus.setId(statusHolder.getWarehouseStatus().getId());
        warehouseStatus.setStatus(status);

        warehouseStatusService.updateWarehouseStatus(warehouseStatus);
        statusHolder.setWarehouseStatus(warehouseStatus);
    }

    private void CancelClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
