package bg.tuvarna.sit.example.data.repositories;

import bg.tuvarna.sit.example.data.access.Connection;
import bg.tuvarna.sit.example.data.enities.Positions;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PositionRepository implements DAORepository<Positions> {

    private static final Logger log = Logger.getLogger(PositionRepository.class);

    public static PositionRepository getInstance(){return PositionRepository.PositionRepositoryHolder.INSTANCE;}

    private static class PositionRepositoryHolder {
        public static final PositionRepository INSTANCE = new PositionRepository();
    }

    @Override
    public void save(Positions object) {

    }

    @Override
    public void update(Positions object) {

    }

    @Override
    public void delete(Positions object) {

    }

    @Override
    public Optional<Positions> getByIg(Long ID) {
        return Optional.empty();
    }

    @Override
    public List<Positions> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Positions> positions = new LinkedList<Positions>();
        try{
        String query = "SELECT t FROM Positions t";
        positions.addAll(session.createQuery(query,Positions.class).getResultList());
        log.info("All positions sellected successfuly.");
        }catch (Exception exception){
            log.error("Sellecting all positions failed. "+exception.getMessage());
        }finally {
            transaction.commit();
        }

        return positions;
    }
}
