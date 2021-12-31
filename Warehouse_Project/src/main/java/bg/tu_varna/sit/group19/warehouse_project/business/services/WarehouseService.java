package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.*;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.UserListViewModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class WarehouseService {
    public final WarehouseRepository warehouseRepository = WarehouseRepository.getInstance();
    public final WarehouseStatusRepository warehouseStatusRepository = WarehouseStatusRepository.getInstance();
    public final WarehouseRoomRepository warehouseRoomRepository = WarehouseRoomRepository.getInstance();
    public final WarehouseTypeRepository warehouseTypeRepository = WarehouseTypeRepository.getInstance();
    public final OwnerRepository ownerRepository = OwnerRepository.getInstance();

    public static WarehouseService getInstance(){
        return WarehouseService.WarehouseServiceHolder.INSTANCE;
    }

    private static class WarehouseServiceHolder {
        public static final WarehouseService INSTANCE = new WarehouseService();
    }

    public void insertWarehouse(Warehouse warehouse){
        warehouseRepository.save(warehouse);
    }

    public void updateWarehouse(Warehouse warehouse){
        warehouseRepository.update(warehouse);
    }

    public void deleteWarehouse(Warehouse warehouse){
        warehouseRepository.delete(warehouse);
    }

    public Warehouse getWarehouseById(Long Id){
        return warehouseRepository.getById(Id).get();
    }

    public ObservableList<WarehouseListViewModel> getAllWarehouses(){
        List<Warehouse> warehouses = warehouseRepository.getAll();

        return FXCollections.observableList(
                warehouses.stream().map(warehouse -> new WarehouseListViewModel(
                        warehouse.getId(),
                        warehouse.getSize(),
                        warehouse.getWarehouseAddress(),
                        warehouse.getOwner(),
                        warehouse.getType(),
                        warehouse.getStatus()
                )).collect(Collectors.toList()));
    }

    public List<Warehouse> getWarehousesList(){
        return warehouseRepository.getAll();
    }

    public ObservableList<WarehouseListViewModel> getAvailableWarehouses() {
        List<WarehouseStatus> statuses = warehouseStatusRepository.getAll();
        WarehouseStatus status = null;
        for (WarehouseStatus s: statuses) {
            if(s.getStatus().equals("Free"))
                status = s;
        }

        return FXCollections.observableList(
                status.getWarehouses().stream().map(warehouse -> new WarehouseListViewModel(
                        warehouse.getId(),
                        warehouse.getSize(),
                        warehouse.getWarehouseAddress(),
                        warehouse.getOwner(),
                        warehouse.getType(),
                        warehouse.getStatus()
                )).collect(Collectors.toList()));
    }
}
