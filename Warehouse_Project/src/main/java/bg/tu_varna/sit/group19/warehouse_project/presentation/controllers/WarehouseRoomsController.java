package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseRoomHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseWithRoomsHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.ClimateService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseRoomService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseWithRoomsService;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AlertMessages;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseRoom;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.RoomWindowModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Arrays;

public class WarehouseRoomsController {
    @FXML
    private TextField sizeTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private ComboBox<String> climateComboBox;
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;


    @FXML
    private void initialize(){
        createButton.setOnMouseClicked(this::CreateClicked);
        cancelButton.setOnMouseClicked(this::CancelClicked);

        InitControls();
    }

    private final WarehouseRoomHolder warehouseRoomHolder = WarehouseRoomHolder.getInstance();

    private void InitControls() {
        for (ClimateCondition condition : climateService.getAllClimateConditions()) {
            climateComboBox.getItems().add(condition.getConditions());
        }

        if(warehouseRoomHolder.getWarehouseRoomsOpenMode() == Enums.OpenMode.UpdateMode)
        {
            sizeTextField.setText(String.valueOf(warehouseRoomHolder.getWarehouseRoom().getSize()));
            priceTextField.setText(String.valueOf(warehouseRoomHolder.getWarehouseRoom().getPrice()));
            climateComboBox.getSelectionModel().select(warehouseRoomHolder.getWarehouseRoom().getCondition().getConditions());
        }

    }

    private final RoomWindowModel roomWindowModel = new RoomWindowModel();

    private void CreateClicked(MouseEvent mouseEvent) {
        if(sizeTextField.getText().trim().isEmpty() || priceTextField.getText().trim().isEmpty() ||
                climateComboBox.getSelectionModel().isEmpty())
        {
            AlertMessages.alertWarningMessage(roomWindowModel.getAlertTitle(), roomWindowModel.getAlertEmptyFields());
            return;
        }

        float size = Float.parseFloat(sizeTextField.getText());
        float price = Float.parseFloat(priceTextField.getText());
        String condition = climateComboBox.getValue();

        if(warehouseRoomHolder.getWarehouseRoomsOpenMode() == Enums.OpenMode.InsertMode)
            insertWarehouseRoomDb(size, price, condition);
        if(warehouseRoomHolder.getWarehouseRoomsOpenMode() == Enums.OpenMode.UpdateMode)
            updateWarehouseRoomDb(size, price, condition);

        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
    }

    private final ClimateService climateService = ClimateService.getInstance();
    private final WarehouseRoomService warehouseRoomService = WarehouseRoomService.getInstance();
    private final WarehouseWithRoomsService warehouseWithRoomsService = WarehouseWithRoomsService.getInstance();
    private final WarehouseHolder warehouseHolder = WarehouseHolder.getInstance();
    private final WarehouseWithRoomsHolder warehouseWithRoomsHolder = WarehouseWithRoomsHolder.getInstance();

    private void insertWarehouseRoomDb(float size, float price, String condition) {
        WarehouseRoom warehouseRoom = new WarehouseRoom();

        warehouseRoom.setSize(size);
        warehouseRoom.setPrice(price);
        ClimateCondition climateCondition = climateService.getClimateCondition(condition);
        warehouseRoom.setCondition(climateCondition);

        if(!checkRoomSize(size))
            return;

        warehouseWithRoomsHolder.getWarehouseWithRooms().getRooms().add(warehouseRoom);
    }

    private boolean checkRoomSize(float size) {
        float totalRoomSize = 0;
        for(WarehouseRoom room : warehouseWithRoomsService.getRoomsListByID(warehouseHolder.getID()))
            totalRoomSize += room.getSize();

        if(size + totalRoomSize > warehouseHolder.getSize())
        {
            AlertMessages.alertWarningMessage(roomWindowModel.getAlertTitle(), roomWindowModel.getAlertRoomSize());
            return false;
        }

        return true;
    }

    private void updateWarehouseRoomDb(float size, float price, String condition) {
        warehouseRoomHolder.getWarehouseRoom().setSize(size);
        warehouseRoomHolder.getWarehouseRoom().setPrice(price);
        ClimateCondition climateCondition = climateService.getClimateCondition(condition);
        warehouseRoomHolder.getWarehouseRoom().setCondition(climateCondition);

        for(WarehouseRoom room : warehouseWithRoomsHolder.getWarehouseWithRooms().getRooms())
        {
            if(room.getId() == warehouseRoomHolder.getWarehouseRoom().getId()) {
                room.setSize(size);
                room.setPrice(price);
                room.setCondition(climateCondition);
            }
        }
    }

    private void CancelClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
