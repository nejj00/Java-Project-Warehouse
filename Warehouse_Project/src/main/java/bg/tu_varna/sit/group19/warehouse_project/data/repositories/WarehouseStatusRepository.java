package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;

public class WarehouseStatusRepository extends BaseRepository<WarehouseStatus> {

    public WarehouseStatusRepository() {
        super(WarehouseStatus.class);
    }

    public static WarehouseStatusRepository getInstance() {
        return WarehouseStatusRepository.WarehouseStatusRepositoryHolder.INSTANCE;
    }

    private static class WarehouseStatusRepositoryHolder {
        public static final WarehouseStatusRepository INSTANCE = new WarehouseStatusRepository();
    }
}
