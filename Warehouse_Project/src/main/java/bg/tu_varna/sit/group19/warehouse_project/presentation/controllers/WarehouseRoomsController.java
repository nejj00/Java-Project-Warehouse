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
    private TextField temperatureTextField;
    @FXML
    private TextField humidityTextField;
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
        if(warehouseRoomHolder.getWarehouseRoomsOpenMode() == Enums.OpenMode.UpdateMode)
        {
            sizeTextField.setText(String.valueOf(warehouseRoomHolder.getWarehouseRoom().getSize()));
            priceTextField.setText(String.valueOf(warehouseRoomHolder.getWarehouseRoom().getPrice()));
            temperatureTextField.setText(String.valueOf(warehouseRoomHolder.getWarehouseRoom().getCondition().getTemperature()));
            humidityTextField.setText(String.valueOf(warehouseRoomHolder.getWarehouseRoom().getCondition().getHumidity()));
        }
    }

    private final RoomWindowModel roomWindowModel = new RoomWindowModel();

    private void CreateClicked(MouseEvent mouseEvent) {
        if(sizeTextField.getText().trim().isEmpty() || priceTextField.getText().trim().isEmpty() ||
                temperatureTextField.getText().trim().isEmpty() || humidityTextField.getText().trim().isEmpty())
        {
            AlertMessages.alertWarningMessage(roomWindowModel.getAlertTitle(), roomWindowModel.getAlertEmptyFields());
            return;
        }

        float size = Float.parseFloat(sizeTextField.getText());
        float price = Float.parseFloat(priceTextField.getText());
        int temperature = Integer.parseInt(temperatureTextField.getText());
        int humidity = Integer.parseInt(humidityTextField.getText());

        if(warehouseRoomHolder.getWarehouseRoomsOpenMode() == Enums.OpenMode.InsertMode)
            insertWarehouseRoomDb(size, price, temperature, humidity);
        if(warehouseRoomHolder.getWarehouseRoomsOpenMode() == Enums.OpenMode.UpdateMode)
            updateWarehouseRoomDb(size, price, temperature, humidity);

        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
    }

    private final ClimateService climateService = ClimateService.getInstance();
    private final WarehouseRoomService warehouseRoomService = WarehouseRoomService.getInstance();
    private final WarehouseWithRoomsService warehouseWithRoomsService = WarehouseWithRoomsService.getInstance();
    private final WarehouseHolder warehouseHolder = WarehouseHolder.getInstance();
    private final WarehouseWithRoomsHolder warehouseWithRoomsHolder = WarehouseWithRoomsHolder.getInstance();

    private void insertWarehouseRoomDb(float size, float price, int temperature, int humidity) {
        WarehouseRoom warehouseRoom = new WarehouseRoom();

        warehouseRoom.setSize(size);
        warehouseRoom.setPrice(price);
        ClimateCondition climateCondition = climateService.getClimateCondition(temperature, humidity);
        if(climateCondition != null)
            warehouseRoom.setCondition(climateCondition);
        else
        {
            climateCondition = new ClimateCondition();
            climateCondition.setTemperature(temperature);
            climateCondition.setHumidity(humidity);
            climateService.insertClimate(climateCondition);
        }

        warehouseRoom.setCondition(climateCondition);

        if(!checkRoomSize(size))
        {
            warehouseWithRoomsHolder.setSizeCheck(false);
            return;
        }
        else
        {
            warehouseWithRoomsHolder.setSizeCheck(true);
        }

        warehouseWithRoomsHolder.getWarehouseWithRooms().getRooms().add(warehouseRoom);
    }

    private boolean checkRoomSize(float size) {
        float totalRoomSize = 0;
        for(WarehouseRoom room : warehouseWithRoomsHolder.getWarehouseWithRooms().getRooms())
            totalRoomSize += room.getSize();

        if(size + totalRoomSize > warehouseHolder.getSize())
        {
            AlertMessages.alertWarningMessage(roomWindowModel.getAlertTitle(), roomWindowModel.getAlertRoomSize());
            return false;
        }

        return true;
    }

    private void updateWarehouseRoomDb(float size, float price, int temperature, int humidity) {
        warehouseRoomHolder.getWarehouseRoom().setSize(size);
        warehouseRoomHolder.getWarehouseRoom().setPrice(price);
        ClimateCondition climateCondition = climateService.getClimateCondition(temperature, humidity);
        if(climateCondition != null)
            warehouseRoomHolder.getWarehouseRoom().setCondition(climateCondition);
        else
        {
            climateCondition = new ClimateCondition();
            climateCondition.setTemperature(temperature);
            climateCondition.setHumidity(humidity);
            climateService.insertClimate(climateCondition);
        }

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
