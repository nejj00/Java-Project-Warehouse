package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

import bg.tu_varna.sit.group19.warehouse_project.business.services.OwnerService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseStatusService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseTypeService;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseType;

public class WarehouseListViewModel {
    private Long WarehouseID;
    private float size;
    private String warehouseAddress;
    private Owner owner;
    private WarehouseType type;
    private WarehouseStatus status;

    public WarehouseListViewModel(Long ID, float Size, String warehouseAddress, Owner owner, WarehouseType warehouseType, WarehouseStatus warehouseStatus) {
        this.WarehouseID = ID;
        this.size=Size;
        this.warehouseAddress = warehouseAddress;
        this.owner = owner;
        this.type = warehouseType;
        this.status = warehouseStatus;
    }

    @Override
    public String toString() {
        return String.format("%s | %f", warehouseAddress, size);
    }

    public Long getWarehouseID() {
        return WarehouseID;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public WarehouseType getType() {
        return type;
    }

    public void setType(WarehouseType type) {
        this.type = type;
    }

    public WarehouseStatus getStatus() {
        return status;
    }

    public void setStatus(WarehouseStatus status) {
        this.status = status;
    }
}
