package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.EnumHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseRoomHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseWithRoomsHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.*;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AlertMessages;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.ListContextMenu;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.common.ScenePaneSwitcher;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.*;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.OwnerWindowModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseRoomListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;

public class ownerWindowCreateWarehouseController {
    @FXML
    public TextField SizeTextField;
    @FXML
    public TextField addressTextArea;
    @FXML
    public ComboBox<String> statusComboBox;
    @FXML
    public ComboBox<String> typeComboBox;
    @FXML
    public Label NameLabel;
    @FXML
    public Button CreateButton;
    @FXML
    public Button CancelButton;
    @FXML
    public ListView<WarehouseRoomListViewModel> warehouseRoomsListView;
    @FXML
    public AnchorPane mainAnchorPane;

    private final EnumHolder enumHolder = EnumHolder.getInstance();

    private final WarehouseHolder warehouseHolder = WarehouseHolder.getInstance();

    private final WarehouseRoomHolder warehouseRoomHolder = WarehouseRoomHolder.getInstance();

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

    private final WarehouseWithRoomsHolder warehouseWithRoomsHolder = WarehouseWithRoomsHolder.getInstance();

    private void InitListView() {

        if (enumHolder.getOpenMode() == Enums.OpenMode.UpdateMode)
        {
            warehouseRoomsListView.setItems(warehouseWithRoomsService.getRoomsByID(warehouseHolder.getID()));
            warehouseWithRoomsHolder.getWarehouseWithRooms().setWarehouseRooms(warehouseWithRoomsService.getRoomsListByID(warehouseHolder.getID()));
        }

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

    private void menuDeleteRoomAction() {
        boolean forDeletion = AlertMessages.alertYesNoResult(ownerWindowModel.getAlertDeleteMessage(), ownerWindowModel.getAlertDeleteTitle());
        if(!forDeletion)
            return;

        WarehouseRoomListViewModel warehouseRoomListViewModel = warehouseRoomsListView.getSelectionModel().getSelectedItem();
        warehouseWithRoomsHolder.getWarehouseWithRooms().getRooms().removeIf(room -> warehouseRoomListViewModel.getWarehouseRoomID() == room.getId());
        warehouseRoomsListView.getItems().remove(warehouseRoomListViewModel);
    }

    private void menuUpdateRoomAction() {
        warehouseRoomHolder.setWarehouseRoomsOpenMode(Enums.OpenMode.UpdateMode);

        int selectedIndex = warehouseRoomsListView.getSelectionModel().getSelectedIndex();
        //ObservableList<WarehouseRoomListViewModel> warehouseRoomListViewModels = warehouseWithRoomsService.getRoomsByID(warehouseHolder.getID());
        //WarehouseRoomListViewModel warehouseRoomListViewModel = warehouseRoomListViewModels.get(selectedIndex);
        WarehouseRoomListViewModel warehouseRoomListViewModel = warehouseRoomsListView.getSelectionModel().getSelectedItem();

        warehouseRoomHolder.getWarehouseRoom().setId(warehouseRoomListViewModel.getWarehouseRoomID());
        warehouseRoomHolder.getWarehouseRoom().setSize(warehouseRoomListViewModel.getSize());
        warehouseRoomHolder.getWarehouseRoom().setPrice(warehouseRoomListViewModel.getPrice());
        warehouseRoomHolder.getWarehouseRoom().setCondition(warehouseRoomListViewModel.getClimateCondition());
        warehouseRoomHolder.getWarehouseRoom().setWarehouse(warehouseService.getWarehouseById(warehouseHolder.getID()));

        initWarehouseSize();
        ScenePaneSwitcher.openNewStageAndWait(pathWarehouseRoom);

        warehouseRoomListViewModel.setPrice(warehouseRoomHolder.getWarehouseRoom().getPrice());
        warehouseRoomListViewModel.setSize(warehouseRoomHolder.getWarehouseRoom().getSize());
        warehouseRoomListViewModel.setClimateCondition(warehouseRoomHolder.getWarehouseRoom().getCondition());
        warehouseRoomsListView.getItems().set(selectedIndex, warehouseRoomListViewModel);
    }

    private void menuInsertRoomAction() {
        warehouseRoomHolder.setWarehouseRoomsOpenMode(Enums.OpenMode.InsertMode);
        initWarehouseSize();
        ScenePaneSwitcher.openNewStageAndWait(pathWarehouseRoom);

        List<WarehouseRoom> warehouseRooms = warehouseWithRoomsHolder.getWarehouseWithRooms().getRooms();
        WarehouseRoom warehouseRoom = warehouseRooms.get(warehouseRooms.size() - 1);

        WarehouseRoomListViewModel warehouseRoomListViewModel = new WarehouseRoomListViewModel(
                warehouseRoom.getId(), warehouseRoom.getSize(), warehouseRoom.getPrice(), warehouseRoom.getCondition());

        warehouseRoomsListView.getItems().add(warehouseRoomListViewModel);
    }

    private void initWarehouseSize() {
        if(SizeTextField.getText().trim().isEmpty())
            AlertMessages.alertWarningMessage(ownerWindowModel.getAlertTitle(), ownerWindowModel.getAlertWarehouseSizeNull());
        warehouseHolder.setSize(Float.parseFloat(SizeTextField.getText()));
    }

    private void InitControls() {
        NameLabel.setText(warehouseHolder.getOwner().getFirstName() + " " + warehouseHolder.getOwner().getLastName());

        for(WarehouseStatus status : warehouseStatusService.getAllStatus()) {
            statusComboBox.getItems().add(status.getStatus());
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
    private final OwnerWindowModel ownerWindowModel = new OwnerWindowModel();

    private void CreateClicked(MouseEvent mouseEvent) {
        if(SizeTextField.getText().trim().isEmpty() || addressTextArea.getText().trim().isEmpty() ||
        typeComboBox.getSelectionModel().isEmpty() || statusComboBox.getSelectionModel().isEmpty())
        {
            AlertMessages.alertWarningMessage(ownerWindowModel.getAlertTitle(), ownerWindowModel.getEmptyFields());
            return;
        }

        float size = Float.parseFloat(SizeTextField.getText());
        String address = addressTextArea.getText();
        String type = typeComboBox.getValue();
        String status = statusComboBox.getValue();

        if(enumHolder.getOpenMode() == Enums.OpenMode.InsertMode)
        {
            insertWarehouseToDb(size, address, type, status);
            AlertMessages.alertInformationMessage(ownerWindowModel.getAlertTitle(), ownerWindowModel.getAlertCongratulations(), ownerWindowModel.getAlertInsert());
        }

        if(enumHolder.getOpenMode() == Enums.OpenMode.UpdateMode)
        {
            updateWarehouseDb(size, address);
            AlertMessages.alertInformationMessage(ownerWindowModel.getAlertTitle(), ownerWindowModel.getAlertCongratulations(), ownerWindowModel.getAlertUpdate());
        }

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

        warehouseWithRoomsHolder.getWarehouseWithRooms().setWarehouse(warehouse);
        warehouseWithRoomsService.updateWarehouseWithRooms(warehouseWithRoomsHolder.getWarehouseWithRooms());
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
