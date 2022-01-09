package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.WarehouseTypeHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseTypeService;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AlertMessages;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.ListContextMenu;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.ScenePaneSwitcher;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseType;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.BaseWindowModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseTypeListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.net.URL;

public class WarehouseTypeListController {
    @FXML
    private ListView<WarehouseTypeListViewModel> typeListView;

    private final WarehouseTypeService warehouseTypeService = WarehouseTypeService.getInstance();

    private final ListContextMenu listContextMenu = new ListContextMenu();

    public void initialize(){
        typeListView.setItems(warehouseTypeService.getTypeObservable());
        typeListView.setContextMenu(listContextMenu.getMyContext());

        listContextMenu.getMenuItemInsert().setOnAction((event)->{
            menuInsertTypeAction();
        });

        listContextMenu.getMenuItemUpdate().setOnAction((event) ->{
            menuUpdateTypeAction();
        });

        listContextMenu.getMenuItemDelete().setOnAction((event)->{
            menuDeleteTypeAction();
        });
    }

    private final WarehouseTypeHolder warehouseTypeHolder = WarehouseTypeHolder.getInstance();
    private final URL pathType = getClass().getResource(Constants.View.TYPE_VIEW);
    private void menuInsertTypeAction() {
        warehouseTypeHolder.setTypeOpenMode(Enums.OpenMode.InsertMode);
        ScenePaneSwitcher.openNewStageAndWait(pathType);

        WarehouseTypeListViewModel warehouseTypeListViewModel = new WarehouseTypeListViewModel(
                warehouseTypeHolder.getWarehouseType().getId(), warehouseTypeHolder.getWarehouseType().getType());

        typeListView.getItems().add(warehouseTypeListViewModel);
    }

    private void menuUpdateTypeAction() {
        warehouseTypeHolder.setTypeOpenMode(Enums.OpenMode.UpdateMode);
        WarehouseTypeListViewModel warehouseTypeListViewModel = typeListView.getSelectionModel().getSelectedItem();

        warehouseTypeHolder.getWarehouseType().setId(warehouseTypeListViewModel.getTypeID());
        warehouseTypeHolder.getWarehouseType().setType(warehouseTypeListViewModel.getType());
        ScenePaneSwitcher.openNewStageAndWait(pathType);

        warehouseTypeListViewModel.setType(warehouseTypeHolder.getWarehouseType().getType());
        typeListView.getItems().set(typeListView.getSelectionModel().getSelectedIndex(), warehouseTypeListViewModel);

    }

    private final BaseWindowModel baseWindowModel = new BaseWindowModel();
    private void menuDeleteTypeAction() {
        boolean forDeletion = AlertMessages.alertYesNoResult(baseWindowModel.getAlertDeleteMessage(), baseWindowModel.getAlertDeleteTitle());
        if(!forDeletion)
            return;

        WarehouseTypeListViewModel warehouseTypeListViewModel = typeListView.getSelectionModel().getSelectedItem();
        WarehouseType warehouseType = new WarehouseType();
        warehouseType.setId(warehouseTypeListViewModel.getTypeID());
        warehouseType.setType(warehouseTypeListViewModel.getType());

        warehouseTypeService.deleteWarehouseType(warehouseType);
        typeListView.getItems().remove(warehouseTypeListViewModel);
    }


}
