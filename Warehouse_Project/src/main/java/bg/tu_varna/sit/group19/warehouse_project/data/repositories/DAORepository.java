package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import java.util.List;
import java.util.Optional;

public interface DAORepository<T> {
    void save(T obj);
    void update(T obj);
    void delete(T obj);
    Optional<T> getById(Long obj);
    List<T> getAll();

}
