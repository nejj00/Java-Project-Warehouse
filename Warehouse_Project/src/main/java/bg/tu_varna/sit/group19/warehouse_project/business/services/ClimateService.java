package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.ClimateRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.OwnerRepository;

public class ClimateService {
    private final ClimateRepository climateRepository = ClimateRepository.getInstance();

    public static ClimateService getInstance(){
        return ClimateService.ClimateServiceHolder.INSTANCE;
    }

    private static class ClimateServiceHolder {
        public static final ClimateService INSTANCE = new ClimateService();
    }

    public void insertClimate(ClimateCondition climateCondition){
        climateRepository.save(climateCondition);
    }

    public void updateClimate(ClimateCondition climateCondition){
        climateRepository.update(climateCondition);
    }

    public void deleteClimate(ClimateCondition climateCondition) {
        climateRepository.delete(climateCondition);
    }
}

