package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.EnumHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseRoomHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseWithRoomsHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.*;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.ListContextMenu;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.common.ScenePaneSwitcher;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.*;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseRoomListViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

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
    public Button CreateButton;
    @FXML
    public Button CancelButton;
    @FXML
    public ListView warehouseRoomsListView;
    @FXML
    public AnchorPane mainAnchorPane;

    private EnumHolder enumHolder = EnumHolder.getInstance();

    private WarehouseHolder warehouseHolder = WarehouseHolder.getInstance();

    private WarehouseRoomHolder warehouseRoomHolder = WarehouseRoomHolder.getInstance();

    private final WarehouseTypeService warehouseTypeService = WarehouseTypeService.getInstance();
    private final WarehouseStatusService warehouseStatusService = WarehouseStatusService.getInstance();

    private final ListContextMenu listContextMenu = new ListContextMenu();


    @FXML
    private void initialize(){
        CreateButton.setOnMouseClicked(this::CreateClicked);
        CancelButton.setOnMouseClicked(this::CancelClicked);

        InitListView();

        InitControls();
    }

    private final WarehouseRoomService warehouseRoomService = WarehouseRoomService.getInstance();
    private final WarehouseWithRoomsService warehouseWithRoomsService = WarehouseWithRoomsService.getInstance();

    private void InitListView() {

        if(enumHolder.getOpenMode() == Enums.OpenMode.UpdateMode)
            warehouseRoomsListView.setItems(warehouseWithRoomsService.getRoomsByID(warehouseHolder.getID()));

        warehouseRoomsListView.setContextMenu(listContextMenu.getMyContext());

        listContextMenu.getMenuItemInsert().setOnAction((event)->{
            menuInsertRoomAction();
        });

        listContextMenu.getMenuItemUpdate().setOnAction((event) ->{
            menuUpdateRoomAction();
        });

        listContextMenu.getMenuItemDelete().setOnAction((event)->{
            menuDeleteRoomAction();
        });
    }

    private final URL pathWarehouseRoom = getClass().getResource(Constants.View.WAREHOUSE_ROOMS_VIEW);
    private WarehouseWithRoomsHolder warehouseWithRoomsHolder = WarehouseWithRoomsHolder.getInstance();

    private void menuDeleteRoomAction() {

    }

    private void menuUpdateRoomAction() {

    }

    private void menuInsertRoomAction() {
        warehouseRoomHolder.setWarehouseRoomsOpenMode(Enums.OpenMode.InsertMode);
        ScenePaneSwitcher.openNewStage(pathWarehouseRoom);
        List<WarehouseRoom> warehouseRooms = warehouseWithRoomsHolder.getWarehouseWithRooms().getRooms();
        WarehouseRoom warehouseRoom = warehouseRooms.get(warehouseRooms.size() - 1);

        WarehouseRoomListViewModel warehouseRoomListViewModel = new WarehouseRoomListViewModel(
                warehouseRoom.getId(), warehouseRoom.getSize(), warehouseRoom.getPrice(), warehouseRoom.getCondition());

        warehouseRoomsListView.getItems().add(warehouseRoomListViewModel);
    }

    private void InitControls() {
        NameLabel.setText(warehouseHolder.getOwner().getFirstName() + " " + warehouseHolder.getOwner().getLastName());

        for (String s : Arrays.asList("Taken", "Free")) {
            statusComboBox.getItems().add(s);
        }

        for(WarehouseType type: warehouseTypeService.getAllTypes()){
            typeComboBox.getItems().add(type.getType());
        }

        if(enumHolder.getOpenMode() == Enums.OpenMode.UpdateMode)
        {
            SizeTextField.setText(String.valueOf(warehouseHolder.getSize()));
            addressTextArea.setText(warehouseHolder.getAddress());
            statusComboBox.getSelectionModel().select(warehouseHolder.getWarehouseStatus().getStatus());
            typeComboBox.getSelectionModel().select(warehouseHolder.getWarehouseType().getType());
        }
    }

    private final WarehouseService warehouseService = WarehouseService.getInstance();

    private final URL pathWarehouseList = getClass().getResource(Constants.View.WAREHOUSES_LIST_VIEW);

    private void CreateClicked(MouseEvent mouseEvent) {
        float size = Float.parseFloat(SizeTextField.getText());
        String address = addressTextArea.getText();
        String type = (String) typeComboBox.getValue();
        String status = (String) statusComboBox.getValue();

        if(enumHolder.getOpenMode() == Enums.OpenMode.InsertMode)
            insertWarehouseToDb(size, address, type, status);

        if(enumHolder.getOpenMode() == Enums.OpenMode.UpdateMode)
            updateWarehouseDb(size, address);

        alertWarehouseAdded();

        warehouseWithRoomsHolder.getWarehouseWithRooms().getRooms().clear();
        mainAnchorPane.getChildren().setAll(ScenePaneSwitcher.getPaneToSwitchTo(pathWarehouseList));
    }

    private void updateWarehouseDb(float size, String address) {
        WarehouseType warehouseType = warehouseTypeService.getWarehouseType(warehouseHolder.getWarehouseType().getType());
        WarehouseStatus warehouseStatus = warehouseStatusService.getWarehouseStatusByStatus(warehouseHolder.getWarehouseStatus().getStatus());
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseHolder.getID());

        warehouse.setSize(size);
        warehouse.setWarehouseAddress(address);
        warehouse.setStatus(warehouseStatus);
        warehouse.setType(warehouseType);

        warehouseService.updateWarehouse(warehouse);
    }

    private void alertWarehouseAdded() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warehouse message!");
        alert.setHeaderText("Congratulations");
        alert.setContentText("Warehouse was inserted successfully");
        alert.showAndWait();
    }



    private void insertWarehouseToDb(float size, String address, String type, String status) {
        WarehouseType warehouseType = warehouseTypeService.getWarehouseType(type);

        WarehouseStatus warehouseStatus = warehouseStatusService.getWarehouseStatusByStatus(status);
        Warehouse warehouse = new Warehouse();

        warehouse.setOwner(warehouseHolder.getOwner());
        warehouse.setSize(size);
        warehouse.setWarehouseAddress(address);
        warehouse.setStatus(warehouseStatus);
        warehouse.setType(warehouseType);

        warehouseWithRoomsHolder.getWarehouseWithRooms().setWarehouse(warehouse);
        warehouseWithRoomsService.insertWarehouseWithRooms(warehouseWithRoomsHolder.getWarehouseWithRooms());

    }

    private void CancelClicked(MouseEvent mouseEvent) {
        warehouseWithRoomsHolder.getWarehouseWithRooms().getRooms().clear();
        mainAnchorPane.getChildren().setAll(ScenePaneSwitcher.getPaneToSwitchTo(pathWarehouseList));

    }


}
