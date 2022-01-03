package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseType;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.ClimateRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.OwnerRepository;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.ClimateListViewModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.StatusListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class ClimateService {
    private final ClimateRepository climateRepository = ClimateRepository.getInstance();

    public static ClimateService getInstance(){
        return ClimateService.ClimateServiceHolder.INSTANCE;
    }

    private static class ClimateServiceHolder {
        public static final ClimateService INSTANCE = new ClimateService();
    }

    public boolean insertClimate(ClimateCondition climateCondition){
        return climateRepository.save(climateCondition);
    }

    public boolean updateClimate(ClimateCondition climateCondition){
        return climateRepository.update(climateCondition);
    }

    public boolean deleteClimate(ClimateCondition climateCondition) {
        return climateRepository.delete(climateCondition);
    }

    public ClimateCondition getClimateCondition(String condition) {
        List<ClimateCondition> climateConditions = climateRepository.getAll();

        for (ClimateCondition c : climateConditions) {
            if(c.getConditions().equals(condition))
                return c;
        }

        return null;
    }

    public ObservableList<ClimateListViewModel> getClimateObservable(){
        List<ClimateCondition> conditions = climateRepository.getAll();

        return FXCollections.observableList(
                conditions.stream().map(condition -> new ClimateListViewModel(
                        condition.getId(),
                        condition.getConditions()
                )).collect(Collectors.toList()));
    }

    public List<ClimateCondition> getAllClimateConditions(){
        return climateRepository.getAll();
    }
}

