package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.EnumHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.OwnerHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseContractService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseWithRoomsService;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AlertMessages;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.ListContextMenu;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.WarehouseWithRooms;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.ScenePaneSwitcher;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.OwnerWindowModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseListViewModel;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;

import static bg.tu_varna.sit.group19.warehouse_project.presentation.controllers.OwnerWindowController.OwnerWindowButtonClicked;

public class OwnerWindowListController implements EventHandler<MouseEvent> {
    @FXML
    public ListView warehouseListView;
    @FXML
    private AnchorPane mainAnchorPane;

    private final WarehouseService warehouseService = WarehouseService.getInstance();
    private final ListContextMenu listContextMenu = new ListContextMenu();
    private final WarehouseContractService contractService = new WarehouseContractService();
    private final OwnerWindowModel ownerWindowModel = new OwnerWindowModel();
    private final WarehouseWithRoomsService warehouseWithRoomsService = WarehouseWithRoomsService.getInstance();


    private final EnumHolder enumHolder = EnumHolder.getInstance();
    // private final WarehouseListViewModel model = WarehouseListViewModel();

    @FXML
    public void initialize() {
        switch (OwnerWindowButtonClicked) {
            case Constants.Owner.ShowContractsClicked -> LoadContracts();
            case Constants.Owner.ShowAvailableWarehousesClicked -> LoadAvailableWarehouses();
            case Constants.Owner.ShowAllWarehousesClicked -> LoadAllWareHouses();
        }
    }

    private final OwnerHolder ownerHolder = OwnerHolder.getInstance();
    private void LoadContracts() {
        Owner owner = ownerHolder.getOwner();
        warehouseListView.setItems(contractService.getOwnersContracts(owner));
    }

    private void LoadAllWareHouses() {
        CreateWarehouseContextMenu();
        warehouseListView.setItems(warehouseService.getWarehousesForOwner(warehouseHolder.getOwner()));
    }

    private void LoadAvailableWarehouses() {
        CreateWarehouseContextMenu();
        warehouseListView.setItems(warehouseService.getAvailableWarehousesForOwner(warehouseHolder.getOwner()));
    }

    private void CreateWarehouseContextMenu() {
        warehouseListView.setContextMenu(listContextMenu.getMyContext());

        listContextMenu.getMenuItemInsert().setOnAction((event)->{
            menuInsertAction();
        });

        listContextMenu.getMenuItemUpdate().setOnAction((event) ->{
            menuUpdateAction();
        });

        listContextMenu.getMenuItemDelete().setOnAction((event)->{
            menuDeleteAction();
        });
    }

    URL pathCreateWarehouse = getClass().getResource(Constants.View.CREATE_WAREHOUSE_VIEW);

    private void menuInsertAction() {
        enumHolder.setOpenMode(Enums.OpenMode.InsertMode);
        mainAnchorPane.getChildren().setAll(ScenePaneSwitcher.getPaneToSwitchTo(pathCreateWarehouse));
    }

    private final WarehouseHolder warehouseHolder = WarehouseHolder.getInstance();

    private void menuUpdateAction() {
        enumHolder.setOpenMode(Enums.OpenMode.UpdateMode);

        ObservableList<WarehouseListViewModel> warehouseList = warehouseService.getAllWarehouses();
        WarehouseListViewModel warehouseListViewModel = warehouseList.get(warehouseListView.getSelectionModel().getSelectedIndex());

        warehouseHolder.setID(warehouseListViewModel.getWarehouseID());
        warehouseHolder.setSize(warehouseListViewModel.getSize());
        warehouseHolder.setAddress(warehouseListViewModel.getWarehouseAddress());
        warehouseHolder.setWarehouseType(warehouseListViewModel.getType());
        warehouseHolder.setWarehouseStatus(warehouseListViewModel.getStatus());

        mainAnchorPane.getChildren().setAll(ScenePaneSwitcher.getPaneToSwitchTo(pathCreateWarehouse));
    }

    private void menuDeleteAction() {
        boolean forDeletion = AlertMessages.alertYesNoResult(ownerWindowModel.getAlertDeleteMessage(), ownerWindowModel.getAlertDeleteTitle());
        if(!forDeletion)
            return;

        WarehouseListViewModel warehouseListViewModel = (WarehouseListViewModel) warehouseListView.getSelectionModel().getSelectedItem();
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseListViewModel.getWarehouseID());

        WarehouseWithRooms warehouseWithRooms = new WarehouseWithRooms();
        warehouseWithRooms.setWarehouse(warehouse);
        warehouseWithRooms.setWarehouseRooms(warehouseWithRoomsService.getRoomsListByID(warehouse.getId()));

        warehouseWithRoomsService.deleteWarehouseWithRooms(warehouseWithRooms);

        warehouseListView.getItems().remove(warehouseListViewModel);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
