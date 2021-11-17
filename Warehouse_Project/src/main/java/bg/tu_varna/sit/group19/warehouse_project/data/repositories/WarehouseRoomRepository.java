package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseRoom;

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
}
