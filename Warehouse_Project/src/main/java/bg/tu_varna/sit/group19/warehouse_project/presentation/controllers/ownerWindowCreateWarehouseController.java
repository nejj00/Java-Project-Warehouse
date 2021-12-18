package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.EnumHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseStatusService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseTypeService;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.common.ScenePaneSwitcher;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class ownerWindowCreateWarehouseController {
    @FXML
    public TextField SizeTextField;
    @FXML
    public TextArea addressTextArea;
    @FXML
    public ComboBox statusComboBox;
    @FXML
    public ComboBox typeComboBox;
    @FXML
    public Label NameLabel;
    @FXML
    public Label FamilyLabel;
    @FXML
    public Button CreateButton;
    @FXML
    public Button CancelButton;
    @FXML
    public AnchorPane mainAnchorPane;

    private EnumHolder enumHolder = EnumHolder.getInstance();

    private WarehouseHolder warehouseHolder = WarehouseHolder.getInstance();

    private final WarehouseTypeService warehouseTypeService = WarehouseTypeService.getInstance();
    private final WarehouseStatusService warehouseStatusService = WarehouseStatusService.getInstance();

    @FXML
    private void initialize(){
        CreateButton.setOnMouseClicked(this::CreateClicked);
        CancelButton.setOnMouseClicked(this::CancelClicked);

        NameLabel.setText(warehouseHolder.getOwner().getFirstName());
        FamilyLabel.setText(warehouseHolder.getOwner().getLastName());

        if(enumHolder.getOpenMode() == Enums.OpenMode.UpdateMode)
        {
            SizeTextField.setText(String.valueOf(warehouseHolder.getSize()));
            addressTextArea.setText(warehouseHolder.getAddress());
            statusComboBox.getSelectionModel().select(warehouseHolder.getWarehouseStatus().getStatus());
            typeComboBox.getSelectionModel().select(warehouseHolder.getWarehouseType().getType());
        }

        for (String s : Arrays.asList("taken", "free")) {
            statusComboBox.getItems().add(s);
        }

        for(WarehouseType type: warehouseTypeService.getAllTypes()){
            typeComboBox.getItems().add(type.getType());
        }


    }

    private final WarehouseService warehouseService = WarehouseService.getInstance();

    URL pathWarehouseList = getClass().getResource(Constants.View.WAREHOUSES_LIST_VIEW);

    private void CreateClicked(MouseEvent mouseEvent) {
        float size = Float.parseFloat(SizeTextField.getText());
        String address = addressTextArea.getText();
        String type = (String) typeComboBox.getValue();
        String status = (String) statusComboBox.getValue();

        if(enumHolder.getOpenMode() == Enums.OpenMode.InsertMode)
            insertWarehouseToDb(size, address, type, status);
        else
        {

        }

        alertWarehouseAdded();

        mainAnchorPane.getChildren().setAll(ScenePaneSwitcher.getPaneToSwitchTo(pathWarehouseList));
    }

    private void alertWarehouseAdded() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warehouse message!");
        alert.setHeaderText("Congratulations");
        alert.setContentText("Warehouse was inserted successfully");
        alert.showAndWait();
    }

    private void insertWarehouseToDb(float size, String address, String type, String status) {
        WarehouseType warehouseType = new WarehouseType();
        warehouseType.setType(type);
        warehouseTypeService.insertWarehouseType(warehouseType);

        WarehouseStatus warehouseStatus = warehouseStatusService.getWarehouseStatusByStatus(status);
        Warehouse warehouse = new Warehouse();

        warehouse.setOwner(warehouseHolder.getOwner());
        warehouse.setSize(size);
        warehouse.setWarehouseAddress(address);
        warehouse.setStatus(warehouseStatus);
        warehouse.setType(warehouseType);

        warehouseService.insertWarehouse(warehouse);
    }

    private void CancelClicked(MouseEvent mouseEvent) {
        mainAnchorPane.getChildren().setAll(ScenePaneSwitcher.getPaneToSwitchTo(pathWarehouseList));

    }


}
