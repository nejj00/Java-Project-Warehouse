package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.access.Connection;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

public class WarehouseStatusRepository extends BaseRepository<WarehouseStatus> {

    public static WarehouseStatusRepository getInstance() {
        return WarehouseStatusRepository.WarehouseStatusRepositoryHolder.INSTANCE;
    }

    private static class WarehouseStatusRepositoryHolder {
        public static final WarehouseStatusRepository INSTANCE = new WarehouseStatusRepository();
    }

    @Override
    public List<WarehouseStatus> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<WarehouseStatus> tableRows = new LinkedList<>();
        try{
            String jpql = "SELECT u FROM WarehouseStatus u";
            tableRows.addAll(session.createQuery(jpql, WarehouseStatus.class).getResultList());
            log.info("Got all tasks");
        }catch (Exception ex){
            log.error("Get Task error: " + ex.getMessage());
        }finally {
            transaction.commit();
            Connection.openSessionClose();
        }

        return tableRows;
    }
}
