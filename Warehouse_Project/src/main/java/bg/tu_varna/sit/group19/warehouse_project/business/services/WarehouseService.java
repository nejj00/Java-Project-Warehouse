package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.repositories.*;

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

}
