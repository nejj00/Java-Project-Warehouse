package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseContractService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseService;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Renter;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import static bg.tu_varna.sit.group19.warehouse_project.presentation.controllers.AgentWindowController.AgentWindowButtonClicked;

public class AgentWindowListController implements EventHandler<MouseEvent> {
    @FXML
    private ListView ListView;
    @FXML
    private AnchorPane mainAnchorPane;

    WarehouseService warehouseService= new WarehouseService();
    WarehouseContractService contractService = new WarehouseContractService();
    private Owner owner;
    private Renter renter;

    @FXML
    public void initialize() {
        owner = AgentWindowController.owner;
        renter = AgentWindowController.renter;
        switch (AgentWindowButtonClicked) {
            case Constants.AgentConstants.ShowOwnersWarehousesClicked -> ShowOwnersWarehouses(owner);
            case Constants.AgentConstants.ShowAvailableWarehousesClicked -> ShowAvailableWarehousesC();
            case Constants.AgentConstants.ShowRenterHistoryClicked -> ShowRenterHistory(renter);
        }
    }

    private void ShowOwnersWarehouses(Owner owner) {
        ListView.setItems(warehouseService.getAvailableWarehousesForOwner(owner));
    }

    private void ShowAvailableWarehousesC() {
        ListView.setItems(warehouseService.getAvailableWarehouses());
    }

    private void ShowRenterHistory(Renter renter) {
        ListView.setItems(contractService.getRenterContracts(renter));
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
