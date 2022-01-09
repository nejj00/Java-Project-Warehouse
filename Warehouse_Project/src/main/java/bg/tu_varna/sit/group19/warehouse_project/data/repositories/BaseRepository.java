package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.access.Connection;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class BaseRepository<T> implements DAORepository<T>{

    protected static  final Logger log = Logger.getLogger(BaseRepository.class);
    protected final Class<T> typeParameterClass;

    public BaseRepository(Class<T> typeParameterClass){
        this.typeParameterClass = typeParameterClass;
    }

    /*
    public static BaseRepository getInstance()
    {
        return BaseRepository.BaseRepositoryHolder.INSTANCE;
    }

    private static class BaseRepositoryHolder {
        public static final BaseRepository INSTANCE = new BaseRepository();
    }

     */

    @Override
    public boolean save(T obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(obj);
            log.info("Saved successfully");
        }catch (Exception ex){
            log.error("Save error " + ex.getMessage());
            showErrorBox("ERROR Saving record!");
            return false;
        }finally {
            transaction.commit();
            Connection.openSessionClose();
        }
        return true;
    }

    @Override
    public boolean update(T obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(obj);
            log.info("Updated successfully");
        }catch (Exception ex){
            log.error("Update error " + ex.getMessage());
            showErrorBox("ERROR Updating record!");
            return false;
        }finally {
            transaction.commit();
            Connection.openSessionClose();
        }
        return true;
    }



    @Override
    public boolean delete(T obj) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(obj);
            log.info("Deleted successfully");
        }catch (Exception ex){
            log.error("Delete error " + ex.getMessage());
            showErrorBox("ERROR Deleting record!");
            return false;
        }finally {
            transaction.commit();
            Connection.openSessionClose();
        }
        return true;
    }

    @Override
    public Optional<T> getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<T> obj = Optional.of(session.get(typeParameterClass, id));
        try{
             obj = Optional.of(session.get(typeParameterClass, id));
        }catch (Exception ex){
            log.error("Failed getById " + ex.getMessage());
            showErrorBox("ERROR Getting record!");
        }finally {
            transaction.commit();
            Connection.openSessionClose();
        }

        return obj;
    }


    @Override
    public List<T> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<T> records = new LinkedList<>();
        try{
            String jpql = "SELECT u FROM " + typeParameterClass.getName() + " u";
            records.addAll(session.createQuery(jpql, typeParameterClass).getResultList());
            log.info("Got all tasks");
        }catch (Exception ex){
            log.error("Get Task error: " + ex.getMessage());
            showErrorBox("ERROR Getting all records!");
        }finally {
            transaction.commit();
            Connection.openSessionClose();
        }

        return records;
    }

    void showErrorBox(String errorString) {
        Alert alert = new Alert(Alert.AlertType.ERROR, errorString);
        alert.showAndWait();
    }
}
