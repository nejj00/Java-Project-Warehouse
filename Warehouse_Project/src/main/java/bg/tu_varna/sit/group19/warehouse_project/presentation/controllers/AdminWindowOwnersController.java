package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.services.OwnerService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

public class AdminWindowOwnersController implements EventHandler<MouseEvent> {

    @FXML
    private ListView ownersListView;

    private final OwnerService ownerService = OwnerService.getInstance();


    private final ContextMenu myContext = new ContextMenu();
    MenuItem menuItemInsert = new MenuItem("Insert");
    MenuItem menuItemUpdate = new MenuItem("Update");
    MenuItem menuItemDelete = new MenuItem("Delete");


    @FXML
    public void initialize() {
        ownersListView.setItems(ownerService.getAllOwners());
        myContext.getItems().addAll(menuItemInsert, menuItemUpdate, menuItemDelete);
        ownersListView.setContextMenu(myContext);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
