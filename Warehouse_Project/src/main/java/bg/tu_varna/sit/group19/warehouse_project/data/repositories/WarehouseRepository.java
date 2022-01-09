package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.access.Connection;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public List<Warehouse> getWarehousesForOwner(Owner owner) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Warehouse> warehouses = new LinkedList<>();
        try {
            String hql = "FROM Warehouse W WHERE W.owner.id = " + owner.getId();
            warehouses.addAll(session.createQuery(hql, Warehouse.class).getResultList());
            log.info("Got all warehouses");
        }catch (Exception ex){
            log.error("Get Task error: " + ex.getMessage());
            showErrorBox("ERROR Getting warehouses for owner!");
        }finally {
            transaction.commit();
            Connection.openSessionClose();
        }

        return  warehouses;
    }
}
