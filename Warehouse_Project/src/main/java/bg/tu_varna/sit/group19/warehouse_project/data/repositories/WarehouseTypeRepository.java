package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseType;

public class WarehouseTypeRepository extends BaseRepository<WarehouseType> {

    public WarehouseTypeRepository() {
        super(WarehouseType.class);
    }

    public static WarehouseTypeRepository getInstance()
    {
        return WarehouseTypeRepository.WarehouseTypeRepositoryHolder.INSTANCE;
    }

    private static class WarehouseTypeRepositoryHolder {
        public static final WarehouseTypeRepository INSTANCE = new WarehouseTypeRepository();
    }
}
