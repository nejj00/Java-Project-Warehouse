package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseRoomHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseWithRoomsHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.ClimateService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseRoomService;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseRoom;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private ComboBox climateComboBox;
    @FXML
    private Button createButton;
    @FXML
    private Button cancelButton;

    private WarehouseRoomHolder warehouseRoomHolder = WarehouseRoomHolder.getInstance();

    @FXML
    private void initialize(){
        createButton.setOnMouseClicked(this::CreateClicked);
        cancelButton.setOnMouseClicked(this::CancelClicked);

        for (String s : Arrays.asList("warm", "cold")) {
            climateComboBox.getItems().add(s);
        }
    }

    private void CreateClicked(MouseEvent mouseEvent) {
        float size = Float.parseFloat(sizeTextField.getText());
        float price = Float.parseFloat(priceTextField.getText());
        String condition = (String) climateComboBox.getValue();

        if(warehouseRoomHolder.getWarehouseRoomsOpenMode() == Enums.OpenMode.InsertMode)
            insertWarehouseRoomDb(size, price, condition);
    }

    private final ClimateService climateService = ClimateService.getInstance();
    private final WarehouseRoomService warehouseRoomService = WarehouseRoomService.getInstance();
    private WarehouseWithRoomsHolder warehouseWithRoomsHolder = WarehouseWithRoomsHolder.getInstance();

    private void insertWarehouseRoomDb(float size, float price, String condition) {
        WarehouseRoom warehouseRoom = new WarehouseRoom();

        //warehouseRoom.setWarehouse(warehouseRoomHolder.getWarehouse());
        warehouseRoom.setSize(size);
        warehouseRoom.setPrice(price);
        ClimateCondition climateCondition = climateService.getClimateCondition(condition);
        warehouseRoom.setCondition(climateCondition);


        warehouseWithRoomsHolder.getWarehouseWithRooms().getRooms().add(warehouseRoom);
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
    }

    private void CancelClicked(MouseEvent mouseEvent) {

    }


}
