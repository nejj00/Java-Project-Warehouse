package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
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
    @FXML
    public void initialize() {
        switch (AgentWindowButtonClicked) {
            case Constants.Agent.ShowOwnersWarehousesClicked -> ShowOwnersWarehouses();
            case Constants.Agent.ShowAvailableWarehousesClicked -> ShowAvailableWarehousesC();
        }
    }

    private void ShowOwnersWarehouses() {

    }

    private void ShowAvailableWarehousesC() {

    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
