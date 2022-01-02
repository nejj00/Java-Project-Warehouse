package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;

import java.util.List;

public class OwnerRepository extends BaseRepository<Owner>{

    public OwnerRepository() {
        super(Owner.class);
    }

    public static OwnerRepository getInstance()
    {
        return OwnerRepository.OwnerRepositoryHolder.INSTANCE;
    }

    private static class OwnerRepositoryHolder {
        public static final OwnerRepository INSTANCE = new OwnerRepository();
    }

    public Owner getByName(String Name) {
        List<Owner> owners = getAll();

        for(Owner owner: owners) {
            if((owner.getFirstName()+" "+owner.getLastName()).equals(Name))
                return owner;
        }

        return null;
    }
}
