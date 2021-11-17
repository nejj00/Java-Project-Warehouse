package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Renter;

public class RenterRepository extends BaseRepository<Renter>{

    public RenterRepository() {
        super(Renter.class);
    }

    public static RenterRepository getInstance()
    {
        return RenterRepository.RenterRepositoryHolder.INSTANCE;
    }

    private static class RenterRepositoryHolder {
        public static final RenterRepository INSTANCE = new RenterRepository();
    }
}
