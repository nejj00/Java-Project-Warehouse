package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Renter;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.RenterRepository;

import java.util.List;

public class RenterService {
    private final RenterRepository renterRepository = RenterRepository.getInstance();

    public static RenterService getInstance(){
        return RenterService.RenterServiceHolder.INSTANCE;
    }

    private static class RenterServiceHolder {
        public static final RenterService INSTANCE = new RenterService();
    }

    public boolean insertRenter(Renter renter){
        return renterRepository.save(renter);
    }

    public boolean updateRenter(Renter renter){
        return renterRepository.update(renter);
    }

    public boolean deleteClimate(Renter renter) {
        return renterRepository.delete(renter);
    }

    public Renter getRenterByID(Long id){
        return renterRepository.getById(id).get();
    }

    public Renter getRenterByNumber(String number){
        List<Renter> renters = renterRepository.getAll();
        Renter renter = new Renter();

        for (Renter renter1: renters) {
            if(renter1.getPhoneNumber().equals(number))
                renter = renter1;
        }

        return renter;
    }
}
