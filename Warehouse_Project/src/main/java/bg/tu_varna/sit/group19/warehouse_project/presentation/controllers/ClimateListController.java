package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.ClimateHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.ClimateService;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AlertMessages;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.ListContextMenu;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.ScenePaneSwitcher;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.BaseWindowModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.ClimateListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.net.URL;

public class ClimateListController {
    @FXML
    private ListView<ClimateListViewModel> climateListView;

    private final ClimateService climateService = ClimateService.getInstance();

    private final ListContextMenu listContextMenu = new ListContextMenu();

    public void initialize(){
        climateListView.setItems(climateService.getClimateObservable());
        climateListView.setContextMenu(listContextMenu.getMyContext());

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

    private final ClimateHolder climateHolder = ClimateHolder.getInstance();
    private final URL pathClimate = getClass().getResource(Constants.View.CLIMATE_VIEW);
    private void menuInsertStatusAction() {
        climateHolder.setClimateOpenMode(Enums.OpenMode.InsertMode);
        ScenePaneSwitcher.openNewStageAndWait(pathClimate);

        ClimateListViewModel climateListViewModel = new ClimateListViewModel(
                climateHolder.getClimateCondition().getId(), climateHolder.getClimateCondition().getTemperature(),
                climateHolder.getClimateCondition().getHumidity());

        climateListView.getItems().add(climateListViewModel);
    }

    private void menuUpdateStatusAction() {
        climateHolder.setClimateOpenMode(Enums.OpenMode.UpdateMode);
        ClimateListViewModel climateListViewModel = climateListView.getSelectionModel().getSelectedItem();

        climateHolder.getClimateCondition().setId(climateListViewModel.getClimateID());
        climateHolder.getClimateCondition().setTemperature(climateListViewModel.getTemperature());
        climateHolder.getClimateCondition().setHumidity(climateListViewModel.getHumidity());
        ScenePaneSwitcher.openNewStageAndWait(pathClimate);

        climateListViewModel.setTemperature(climateHolder.getClimateCondition().getTemperature());
        climateListViewModel.setHumidity(climateHolder.getClimateCondition().getHumidity());
        climateListView.getItems().set(climateListView.getSelectionModel().getSelectedIndex(), climateListViewModel);
    }

    private final BaseWindowModel baseWindowModel = new BaseWindowModel();
    private void menuDeleteStatusAction() {
        boolean forDeletion = AlertMessages.alertYesNoResult(baseWindowModel.getAlertDeleteMessage(), baseWindowModel.getAlertDeleteTitle());
        if(!forDeletion)
            return;

        ClimateListViewModel climateListViewModel = climateListView.getSelectionModel().getSelectedItem();
        ClimateCondition climateCondition = new ClimateCondition();
        climateCondition.setId(climateListViewModel.getClimateID());
        climateCondition.setTemperature(climateListViewModel.getTemperature());
        climateCondition.setHumidity(climateListViewModel.getHumidity());

        climateService.deleteClimate(climateCondition);
        climateListView.getItems().remove(climateListViewModel);
    }
}
