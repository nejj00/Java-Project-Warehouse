package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Renter;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.RenterRepository;

public class RenterService {
    private final RenterRepository renterRepository = RenterRepository.getInstance();

    public static RenterService getInstance(){
        return RenterService.RenterServiceHolder.INSTANCE;
    }

    private static class RenterServiceHolder {
        public static final RenterService INSTANCE = new RenterService();
    }

    public void insertRenter(Renter renter){
        renterRepository.save(renter);
    }

    public void updateRenter(Renter renter){
        renterRepository.update(renter);
    }

    public void deleteClimate(Renter renter) {
        renterRepository.delete(renter);
    }

}
