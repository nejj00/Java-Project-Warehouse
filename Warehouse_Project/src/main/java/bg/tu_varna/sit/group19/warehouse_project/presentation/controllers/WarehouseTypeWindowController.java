package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseTypeHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseTypeService;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AlertMessages;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseType;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.BaseWindowModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WarehouseTypeWindowController {
    @FXML
    private TextField typeTextField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    private final WarehouseTypeHolder warehouseTypeHolder = WarehouseTypeHolder.getInstance();

    @FXML
    public void initialize(){
        confirmButton.setOnMouseClicked(this::ConfirmClicked);
        cancelButton.setOnMouseClicked(this::CancelClicked);

        if(warehouseTypeHolder.getTypeOpenMode() == Enums.OpenMode.UpdateMode)
            typeTextField.setText(warehouseTypeHolder.getWarehouseType().getType());
    }

    private final BaseWindowModel baseWindowModel = new BaseWindowModel();
    private void ConfirmClicked(MouseEvent mouseEvent) {
        if(typeTextField.getText().trim().isEmpty()) {
            AlertMessages.alertWarningMessage(baseWindowModel.getAlertTitle(), baseWindowModel.getAlertEmptyFields());
            return;
        }

        String type = typeTextField.getText();

        if(warehouseTypeHolder.getTypeOpenMode() == Enums.OpenMode.InsertMode)
            insertTypeDb(type);
        if(warehouseTypeHolder.getTypeOpenMode() == Enums.OpenMode.UpdateMode)
            updateTypeDb(type);

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    private final WarehouseTypeService warehouseTypeService = WarehouseTypeService.getInstance();
    private void insertTypeDb(String type) {
        WarehouseType warehouseType = new WarehouseType();
        warehouseType.setType(type);
        warehouseTypeService.insertWarehouseType(warehouseType);
        warehouseTypeHolder.setWarehouseType(warehouseType);
    }

    private void updateTypeDb(String type) {
        WarehouseType warehouseType = new WarehouseType();
        warehouseType.setId(warehouseTypeHolder.getWarehouseType().getId());
        warehouseType.setType(type);

        warehouseTypeService.updateWarehouseType(warehouseType);
        warehouseTypeHolder.setWarehouseType(warehouseType);
    }

    private void CancelClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

}
