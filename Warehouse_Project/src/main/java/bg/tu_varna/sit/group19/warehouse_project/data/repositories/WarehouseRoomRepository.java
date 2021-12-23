package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.access.Connection;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseRoom;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class WarehouseRoomRepository extends BaseRepository<WarehouseRoom> {

    public WarehouseRoomRepository() {
        super(WarehouseRoom.class);
    }

    public static WarehouseRoomRepository getInstance()
    {
        return WarehouseRoomRepository.WarehouseRoomRepositoryHolder.INSTANCE;
    }

    private static class WarehouseRoomRepositoryHolder {
        public static final WarehouseRoomRepository INSTANCE = new WarehouseRoomRepository();
    }

    public List<WarehouseRoom> getAllRoomsFromWarehouseWithId(long WarehouseID) {
        List<WarehouseRoom> list = new LinkedList<>();
        for(WarehouseRoom room:getInstance().getAll()) {
            if(room.getWarehouse().getId()==WarehouseID)
                list.add(room);
        }

        return list;
    }
}
