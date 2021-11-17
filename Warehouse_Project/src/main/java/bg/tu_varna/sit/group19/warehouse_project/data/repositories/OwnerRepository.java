package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;

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
}
