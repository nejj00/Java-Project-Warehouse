package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.access.Connection;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class BaseRepository<T> implements DAORepository<T>{

    private static  final Logger log = Logger.getLogger(BaseRepository.class);

    public static BaseRepository getInstance()
    {
        return BaseRepository.BaseRepositoryHolder.INSTANCE;
    }

    private static class BaseRepositoryHolder {
        public static final BaseRepository INSTANCE = new BaseRepository();
    }

    @Override
    public void save(T obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Saved successfully");
        }catch (Exception ex){
            log.error("Save error " + ex.getMessage());
        }finally {
            transaction.commit();
            Connection.openSessionClose();
        }
    }

    @Override
    public void update(T obj) {

    }

    @Override
    public void delete(T obj) {

    }

    @Override
    public Optional<T> getById(Long obj) {
        return Optional.empty();
    }

    @Override
    public List<T> getAll() {
        return null;
    }
}
