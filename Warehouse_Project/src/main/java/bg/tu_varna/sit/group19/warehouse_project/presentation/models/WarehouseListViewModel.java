package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

import bg.tu_varna.sit.group19.warehouse_project.business.services.OwnerService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseStatusService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseTypeService;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseType;

public class WarehouseListViewModel {
    private double size;
    private Owner owner;
    private OwnerService ownerService;
    private WarehouseType type;
    private WarehouseTypeService warehouseTypeService;
    private WarehouseStatus status;
    private WarehouseStatusService warehouseStatusService;

    public WarehouseListViewModel(double Size, String FirtsName, String LastName, String WarehouseType, String WarehouseStatus) {
        this.size=Size;
        this.owner = ownerService.getOwnerByName(FirtsName,LastName); // тук не мисля че трябва да се викат service класове
        this.type = warehouseTypeService.getWarehouseType(WarehouseType); // просто се задават стойностите от параметрире
        this.status = warehouseStatusService.getWarehouseStatusByStatus(WarehouseStatus); // а самите стойности се взимат в service класа
    }

    @Override
    public String toString() {
        return String.format("%f %s %s %s %s",size,owner.getFirstName(),owner.getLastName(),type.getType(),status.getStatus());
    }
}
