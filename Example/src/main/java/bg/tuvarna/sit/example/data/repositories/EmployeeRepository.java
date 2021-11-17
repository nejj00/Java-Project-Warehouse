package bg.tuvarna.sit.example.data.repositories;

import bg.tuvarna.sit.example.data.access.Connection;
import bg.tuvarna.sit.example.data.enities.Employees;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class EmployeeRepository implements DAORepository<Employees>{

    private static final Logger log = Logger.getLogger(EmployeeRepository.class);

    private static EmployeeRepository getInstance(){return EmployeeRepository.EmplyeeRepositoryHolder.INSTANCE;}

    private static class EmplyeeRepositoryHolder {
        public static final EmployeeRepository INSTANCE = new EmployeeRepository();
    }


    @Override
    public void save(Employees object) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(object);
            log.info("Employee saved successfuly.");
        }catch (Exception exception){
            log.error("Employee save failed. Error: "+exception.getMessage());
        }finally {
            transaction.commit();
        }

    }

    @Override
    public void update(Employees object) {

    }

    @Override
    public void delete(Employees object) {

    }

    @Override
    public Optional<Employees> getByIg(Long ID) {
        return Optional.empty();
    }

    @Override
    public List<Employees> getAll() {
        return null;
    }

}
