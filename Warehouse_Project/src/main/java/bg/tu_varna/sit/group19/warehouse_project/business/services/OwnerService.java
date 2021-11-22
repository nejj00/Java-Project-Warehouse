package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.OwnerRepository;

public class OwnerService {
    private final OwnerRepository ownerRepository = OwnerRepository.getInstance();

    public static OwnerService getInstance(){
        return OwnerServiceHolder.INSTANCE;
    }

    private static class OwnerServiceHolder {
        public static final OwnerService INSTANCE = new OwnerService();
    }

    public void insertOwner(Owner owner){
        ownerRepository.save(owner);
    }

    
}
