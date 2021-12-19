package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.EnumHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseWithRoomsService;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.ListContextMenu;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.WarehouseWithRooms;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.common.ScenePaneSwitcher;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseListViewModel;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public class OwnerWindowListController implements EventHandler<MouseEvent> {
    @FXML
    public ListView warehouseListView;
    @FXML
    private AnchorPane mainAnchorPane;

    private final WarehouseService warehouseService = WarehouseService.getInstance();
    private final ListContextMenu listContextMenu = new ListContextMenu();

    private EnumHolder enumHolder = EnumHolder.getInstance();
    // private final WarehouseListViewModel model = WarehouseListViewModel();

    @FXML
    public void initialize() {
        warehouseListView.setItems(warehouseService.getAllWarehouses());
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

    private WarehouseWithRoomsService warehouseWithRoomsService = WarehouseWithRoomsService.getInstance();
    private void menuDeleteAction() {
        ButtonType yesDelete = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType noDelete = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Are you sure you want to delete this record.", yesDelete, noDelete);

        alert.setTitle("Deletion Warning");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(noDelete) != yesDelete)
            return;

        int selectedIndex = warehouseListView.getSelectionModel().getSelectedIndex();
        ObservableList<WarehouseListViewModel> warehouseList = warehouseService.getAllWarehouses();
        WarehouseListViewModel warehouseListViewModel = warehouseList.get(selectedIndex);
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseListViewModel.getWarehouseID());

        WarehouseWithRooms warehouseWithRooms = new WarehouseWithRooms();
        warehouseWithRooms.setWarehouse(warehouse);
        warehouseWithRooms.setWarehouseRooms(warehouseWithRoomsService.getRoomsListByID(warehouse.getId()));

        warehouseWithRoomsService.deleteWarehouseWithRooms(warehouseWithRooms);

        warehouseListView.getItems().remove(selectedIndex);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
