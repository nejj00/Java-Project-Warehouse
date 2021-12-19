package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseRoom;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.OwnerRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseRoomRepository;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseListViewModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseRoomListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class WarehouseRoomService {

    public final WarehouseRoomRepository warehouseRoomRepository = WarehouseRoomRepository.getInstance();

    public static WarehouseRoomService getInstance(){
        return WarehouseRoomService.WarehouseRoomServiceHolder.INSTANCE;
    }

    private static class WarehouseRoomServiceHolder {
        public static final WarehouseRoomService INSTANCE = new WarehouseRoomService();
    }

    public void insertWarehouseRoom(WarehouseRoom warehouseRoom){
        warehouseRoomRepository.save(warehouseRoom);
    }

    public void updateWarehouseRoom(WarehouseRoom warehouseRoom){
        warehouseRoomRepository.update(warehouseRoom);
    }

    public void deleteWarehouseRoom(WarehouseRoom warehouseRoom){
        warehouseRoomRepository.delete(warehouseRoom);
    }

    public WarehouseRoom getWarehouseRoomById(Long Id){
        return warehouseRoomRepository.getById(Id).get();
    }

    public ObservableList<WarehouseRoomListViewModel> getAllWarehouseRooms(){
        List<WarehouseRoom> warehouseRooms = warehouseRoomRepository.getAll();

        return FXCollections.observableList(
                warehouseRooms.stream().map(warehouseRoom -> new WarehouseRoomListViewModel(
                        warehouseRoom.getId(),
                        warehouseRoom.getSize(),
                        warehouseRoom.getPrice(),
                        warehouseRoom.getCondition()
                        //warehouseRoom.getWarehouse()
                )).collect(Collectors.toList()));
    }
}
