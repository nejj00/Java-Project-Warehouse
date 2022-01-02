package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Renter;

import java.util.List;

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

    public Renter getByName(String Name) {
        List<Renter> renters = getAll();

        for(Renter renter:renters) {
            if((renter.getFirstName()+" "+renter.getLastName()).equals(Name))
                return renter;
        }

        return null;
    }
}
