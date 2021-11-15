package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.access.Connection;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class ClimateRepository implements DAORepository<ClimateCondition> {

    private static final Logger log = Logger.getLogger(ClimateRepository.class);

    public static ClimateRepository getInstance(){
        return ClimateRepository.ClimateRepositoryHolder.INSTANCE;
    }

    public static class ClimateRepositoryHolder {
        public static final ClimateRepository INSTANCE = new ClimateRepository();
    }

    @Override
    public void save(ClimateCondition obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Climate saved successfully");
        }catch (Exception ex){
            log.error("Climate save error " + ex.getMessage());
        }finally {
            transaction.commit();
            Connection.openSessionClose();
        }
    }

    @Override
    public void update(ClimateCondition obj) {

    }

    @Override
    public void delete(ClimateCondition obj) {

    }

    @Override
    public Optional<ClimateCondition> getById(Long obj) {
        return Optional.empty();
    }

    @Override
    public List<ClimateCondition> getAll() {
        return null;
    }

}
