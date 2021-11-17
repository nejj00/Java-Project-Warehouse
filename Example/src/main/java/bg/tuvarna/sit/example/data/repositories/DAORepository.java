package bg.tuvarna.sit.example.data.repositories;

import java.util.List;
import java.util.Optional;

public interface DAORepository<T> {
    void save(T object);
    void update(T object);
    void delete(T object);
    Optional<T> getByIg(Long ID);
    List<T> getAll();
}
