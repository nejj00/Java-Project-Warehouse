package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseContract;

public class WarehouseContractRepository extends BaseRepository<WarehouseContract> {

    public WarehouseContractRepository() {
        super(WarehouseContract.class);
    }

    public static WarehouseContractRepository getInstance()
    {
        return WarehouseContractRepository.WarehouseContractRepositoryHolder.INSTANCE;
    }

    private static class WarehouseContractRepositoryHolder {
        public static final WarehouseContractRepository INSTANCE = new WarehouseContractRepository();
    }
}
