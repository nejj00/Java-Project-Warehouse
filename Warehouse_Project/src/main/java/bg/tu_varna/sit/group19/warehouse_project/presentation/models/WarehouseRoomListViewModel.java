package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.*;

public class WarehouseRoomListViewModel {
    private Long WarehouseRoomID;
    private float size;
    private float price;
    private ClimateCondition climateCondition;
    //private Warehouse warehouse;

    public WarehouseRoomListViewModel(Long ID, float size, float price, ClimateCondition climateCondition /*, Warehouse warehouse*/) {
        this.WarehouseRoomID = ID;
        this.size = size;
        this.price = price;
        this.climateCondition = climateCondition;
        //this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return String.format("%f | %f | %s", price, size, climateCondition.toString());
    }

    public Long getWarehouseRoomID() {
        return WarehouseRoomID;
    }

    public void setWarehouseRoomID(Long warehouseRoomID) {
        WarehouseRoomID = warehouseRoomID;
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
}
