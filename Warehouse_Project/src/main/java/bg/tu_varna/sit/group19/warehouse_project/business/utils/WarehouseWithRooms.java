package bg.tu_varna.sit.group19.warehouse_project.business.utils;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseRoom;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseRoomListViewModel;

import java.util.ArrayList;
import java.util.List;

public class WarehouseWithRooms {

    private Warehouse warehouse;
    private List<WarehouseRoom> warehouseRooms = new ArrayList<>();

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<WarehouseRoom> getRooms() {
        return warehouseRooms;
    }

    public void setWarehouseRooms(List<WarehouseRoom> warehouseRooms) {
        this.warehouseRooms = warehouseRooms;
    }
}
