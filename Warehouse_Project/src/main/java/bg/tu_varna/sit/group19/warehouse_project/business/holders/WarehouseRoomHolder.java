package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;

public class WarehouseRoomHolder {

    private Long ID;
    private float size;
    private float price;
    private ClimateCondition climateCondition;
    private Warehouse warehouse;

    private Enums.OpenMode warehouseRoomsOpenMode;

    private final static WarehouseRoomHolder INSTANCE = new WarehouseRoomHolder();

    private WarehouseRoomHolder() {}

    public static WarehouseRoomHolder getInstance() {
        return INSTANCE;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ClimateCondition getClimateCondition() {
        return climateCondition;
    }

    public void setClimateCondition(ClimateCondition climateCondition) {
        this.climateCondition = climateCondition;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Enums.OpenMode getWarehouseRoomsOpenMode() {
        return warehouseRoomsOpenMode;
    }

    public void setWarehouseRoomsOpenMode(Enums.OpenMode warehouseRoomsOpenMode) {
        this.warehouseRoomsOpenMode = warehouseRoomsOpenMode;
    }
}
