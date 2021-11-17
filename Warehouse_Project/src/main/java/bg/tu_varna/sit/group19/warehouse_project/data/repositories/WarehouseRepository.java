package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;

public class WarehouseRepository extends BaseRepository<Warehouse>{

    public WarehouseRepository() {
        super(Warehouse.class);
    }

    public static WarehouseRepository getInstance()
    {
        return WarehouseRepository.WarehouseRepositoryHolder.INSTANCE;
    }

    private static class WarehouseRepositoryHolder {
        public static final WarehouseRepository INSTANCE = new WarehouseRepository();
    }
}
