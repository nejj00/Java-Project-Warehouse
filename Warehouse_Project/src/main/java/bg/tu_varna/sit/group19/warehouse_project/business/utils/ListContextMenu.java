package bg.tu_varna.sit.group19.warehouse_project.business.utils;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class ListContextMenu {

    public ListContextMenu(){
        myContext.getItems().addAll(menuItemInsert, menuItemUpdate, menuItemDelete);
    }

    private final ContextMenu myContext = new ContextMenu();
    public static final MenuItem menuItemInsert = new MenuItem("Insert");
    public static final MenuItem menuItemUpdate = new MenuItem("Update");
    public static final MenuItem menuItemDelete = new MenuItem("Delete");

    public ContextMenu getMyContext() {
        return myContext;
    }

    public MenuItem getMenuItemInsert() {
        return menuItemInsert;
    }

    public MenuItem getMenuItemUpdate() {
        return menuItemUpdate;
    }

    public MenuItem getMenuItemDelete() {
        return menuItemDelete;
    }

}
