package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.StatusHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseStatusService;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AlertMessages;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.ListContextMenu;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.common.ScenePaneSwitcher;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.BaseWindowModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.StatusListViewModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseRoomListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.net.URL;

public class StatusListController {
    @FXML
    private ListView<StatusListViewModel> statusListView;

    private final WarehouseStatusService warehouseStatusService = WarehouseStatusService.getInstance();

    private final ListContextMenu listContextMenu = new ListContextMenu();

    public void initialize(){
        statusListView.setItems(warehouseStatusService.getStatusObservable());
        statusListView.setContextMenu(listContextMenu.getMyContext());

        listContextMenu.getMenuItemInsert().setOnAction((event)->{
            menuInsertStatusAction();
        });

        listContextMenu.getMenuItemUpdate().setOnAction((event) ->{
            menuUpdateStatusAction();
        });

        listContextMenu.getMenuItemDelete().setOnAction((event)->{
            menuDeleteStatusAction();
        });
    }

    private final StatusHolder statusHolder = StatusHolder.getInstance();
    private final URL pathStatus = getClass().getResource(Constants.View.STATUS_VIEW);
    private void menuInsertStatusAction() {
        statusHolder.setWarehouseStatusOpenMode(Enums.OpenMode.InsertMode);
        ScenePaneSwitcher.openNewStageAndWait(pathStatus);

        StatusListViewModel statusListViewModel = new StatusListViewModel(
                statusHolder.getWarehouseStatus().getId(), statusHolder.getWarehouseStatus().getStatus());

        statusListView.getItems().add(statusListViewModel);
    }

    private void menuUpdateStatusAction() {
        statusHolder.setWarehouseStatusOpenMode(Enums.OpenMode.UpdateMode);
        StatusListViewModel statusListViewModel = statusListView.getSelectionModel().getSelectedItem();

        statusHolder.getWarehouseStatus().setId(statusListViewModel.getStatusID());
        statusHolder.getWarehouseStatus().setStatus(statusListViewModel.getStatus());
        ScenePaneSwitcher.openNewStageAndWait(pathStatus);

        statusListViewModel.setStatus(statusHolder.getWarehouseStatus().getStatus());
        statusListView.getItems().set(statusListView.getSelectionModel().getSelectedIndex(), statusListViewModel);
    }

    private final BaseWindowModel baseWindowModel = new BaseWindowModel();
    private void menuDeleteStatusAction() {
        boolean forDeletion = AlertMessages.alertYesNoResult(baseWindowModel.getAlertDeleteMessage(), baseWindowModel.getAlertDeleteTitle());
        if(!forDeletion)
            return;

        StatusListViewModel statusListViewModel = statusListView.getSelectionModel().getSelectedItem();
        WarehouseStatus status = new WarehouseStatus();
        status.setId(statusListViewModel.getStatusID());
        status.setStatus(statusListViewModel.getStatus());

        warehouseStatusService.deleteWarehouseStatus(status);
        statusListView.getItems().remove(statusListViewModel);
    }
}
