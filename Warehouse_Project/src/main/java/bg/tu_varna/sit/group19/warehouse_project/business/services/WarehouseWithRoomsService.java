package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.business.utils.WarehouseWithRooms;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseRoom;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseRoomRepository;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseRoomListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WarehouseWithRoomsService {

    WarehouseRepository warehouseRepository = WarehouseRepository.getInstance();
    WarehouseRoomRepository warehouseRoomRepository = WarehouseRoomRepository.getInstance();

    public static WarehouseWithRoomsService getInstance(){
        return WarehouseWithRoomsService.WarehouseWithRoomsServiceHolder.INSTANCE;
    }

    private static class WarehouseWithRoomsServiceHolder {
        public static final WarehouseWithRoomsService INSTANCE = new WarehouseWithRoomsService();
    }

    public void insertWarehouseWithRooms(WarehouseWithRooms warehouseWithRooms){
        warehouseRepository.save(warehouseWithRooms.getWarehouse());
        for (WarehouseRoom room : warehouseWithRooms.getRooms())
        {
            room.setWarehouse(warehouseWithRooms.getWarehouse());
            warehouseRoomRepository.save(room);
        }
    }

    public void updateWarehouseWithRooms(WarehouseWithRooms warehouseWithRooms){

        for (WarehouseRoom room : warehouseWithRooms.getRooms())
        {
            if(room.getId() == 0)
            {
                room.setWarehouse(warehouseWithRooms.getWarehouse());
                warehouseRoomRepository.save(room);
            }
        }

        List<WarehouseRoom> warehouseRooms = getRoomsListByID(warehouseWithRooms.getWarehouse().getId());
        boolean isInList;

        WarehouseRoom warehouseRoomDB;
        WarehouseRoom warehouseRoom;

        for (int i = 0; i < warehouseRooms.size(); i++)
        {
            isInList = false;

            warehouseRoomDB = warehouseRooms.get(i);
            for(int j = 0; j < warehouseWithRooms.getRooms().size(); j++)
            {
                warehouseRoom = warehouseWithRooms.getRooms().get(j);

                if(warehouseRoom.getId() == 0)
                    continue;

                if(warehouseRoomDB.getId() == warehouseRoom.getId())
                {
                    warehouseRoomRepository.update(warehouseRoom);

                    isInList = true;
                    break;
                }
            }

            if(!isInList)
                warehouseRoomRepository.delete(warehouseRoomDB);

        }

        warehouseRepository.update(warehouseWithRooms.getWarehouse());
    }

    public void deleteWarehouseWithRooms(WarehouseWithRooms warehouseWithRooms){
        for (WarehouseRoom room : warehouseWithRooms.getRooms())
        {
            room.setWarehouse(warehouseWithRooms.getWarehouse());
            warehouseRoomRepository.delete(room);
        }

        warehouseRepository.delete(warehouseWithRooms.getWarehouse());
    }

    public List<WarehouseRoom> getRoomsListByID(Long warehouseID){
        List<WarehouseRoom> warehouseRooms = warehouseRoomRepository.getAll();

        List<WarehouseRoom> warehouseRoomList = new ArrayList<>();

        for(WarehouseRoom warehouseRoom : warehouseRooms)
        {
            if(warehouseRoom.getWarehouse().getId() == warehouseID)
            {
                warehouseRoomList.add(warehouseRoom);
            }
        }

        return warehouseRoomList;
    }

    public ObservableList<WarehouseRoomListViewModel> getRoomsByID(Long warehouseID){
        List<WarehouseRoom> warehouseRooms = warehouseRoomRepository.getAll();

        List<WarehouseRoom> warehouseRoomList = new ArrayList<>();

        for(WarehouseRoom warehouseRoom : warehouseRooms)
        {
            if(warehouseRoom.getWarehouse().getId() == warehouseID)
            {
                warehouseRoomList.add(warehouseRoom);
            }
        }

        return FXCollections.observableList(
                warehouseRoomList.stream().map(warehouseRoom -> new WarehouseRoomListViewModel(
                        warehouseRoom.getId(),
                        warehouseRoom.getSize(),
                        warehouseRoom.getPrice(),
                        warehouseRoom.getCondition()
                        //warehouseRoom.getWarehouse()
                )).collect(Collectors.toList()));
    }

}
