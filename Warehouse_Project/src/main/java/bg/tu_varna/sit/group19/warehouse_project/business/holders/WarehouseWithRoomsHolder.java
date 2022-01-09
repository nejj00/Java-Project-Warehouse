package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.business.utils.WarehouseWithRooms;

public class WarehouseWithRoomsHolder {

    private WarehouseWithRooms warehouseWithRooms = new WarehouseWithRooms();

    boolean sizeCheck;

    private final static WarehouseWithRoomsHolder INSTANCE = new WarehouseWithRoomsHolder();

    private WarehouseWithRoomsHolder() {}

    public static WarehouseWithRoomsHolder getInstance() {
        return INSTANCE;
    }

    public WarehouseWithRooms getWarehouseWithRooms() {
        return warehouseWithRooms;
    }

    public void setWarehouseWithRooms(WarehouseWithRooms warehouseWithRooms) {
        this.warehouseWithRooms = warehouseWithRooms;
    }

    public boolean isSizeCheck() {
        return sizeCheck;
    }

    public void setSizeCheck(boolean sizeCheck) {
        this.sizeCheck = sizeCheck;
    }
}
