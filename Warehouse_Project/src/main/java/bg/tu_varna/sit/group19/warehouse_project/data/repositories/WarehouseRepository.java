package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.access.Connection;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

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
