package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseRoom;

public class WarehouseRoomHolder {

    private WarehouseRoom warehouseRoom = new WarehouseRoom();

    private Enums.OpenMode warehouseRoomsOpenMode;

    private final static WarehouseRoomHolder INSTANCE = new WarehouseRoomHolder();

    private WarehouseRoomHolder() {}

    public static WarehouseRoomHolder getInstance() {
        return INSTANCE;
    }

    public WarehouseRoom getWarehouseRoom() {
        return warehouseRoom;
    }

    public void setWarehouseRoom(WarehouseRoom warehouseRoom) {
        this.warehouseRoom = warehouseRoom;
    }

    public Enums.OpenMode getWarehouseRoomsOpenMode() {
        return warehouseRoomsOpenMode;
    }

    public void setWarehouseRoomsOpenMode(Enums.OpenMode warehouseRoomsOpenMode) {
        this.warehouseRoomsOpenMode = warehouseRoomsOpenMode;
    }
}
