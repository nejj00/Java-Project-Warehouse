package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.access.Connection;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AdminRepository implements DAORepository<Admin> {

    private static  final Logger log = Logger.getLogger(AdminRepository.class);

    public static AdminRepository getInstance()
    {
        return AdminRepository.AdminRepositoryHolder.INSTANCE;
    }

    private static class AdminRepositoryHolder {
        public static final AdminRepository INSTANCE = new AdminRepository();
    }

    @Override
    public void save(Admin obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Admin saved successfully");
        }catch (Exception ex){
            log.error("Admin save error " + ex.getMessage());
        }finally {
            transaction.commit();
            Connection.openSessionClose();
        }
    }

    @Override
    public void update(Admin obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(obj);
            log.info("User updated");
        }catch (Exception ex){
            log.error("User update error " + ex.getMessage());
        }finally {
            transaction.commit();
            Connection.openSessionClose();
        }
    }

    @Override
    public void delete(Admin obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(obj);
            log.info("User saved successfully");
        }catch (Exception ex){
            log.error("User save error " + ex.getMessage());
        }finally {
            transaction.commit();
            Connection.openSessionClose();
        }
    }

    @Override
    public Optional<Admin> getById(Long obj) {
        return Optional.empty();
    }

    @Override
    public List<Admin> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Admin> users = new LinkedList<>();
        try{
            String jpql = "SELECT u FROM Admin u";
            users.addAll(session.createQuery(jpql, Admin.class).getResultList());
            log.info("Got all tasks");
        }catch (Exception ex){
            log.error("Get Task error: " + ex.getMessage());
        }finally {
            transaction.commit();
            Connection.openSessionClose();
        }

        return users;
    }
}
