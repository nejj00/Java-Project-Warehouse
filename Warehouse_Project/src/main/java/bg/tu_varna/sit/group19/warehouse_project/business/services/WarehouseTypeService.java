package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseType;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseTypeRepository;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.ClimateListViewModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseTypeListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class WarehouseTypeService {
    private final WarehouseTypeRepository warehouseTypeRepository = WarehouseTypeRepository.getInstance();

    public static WarehouseTypeService getInstance() {return WarehouseTypeServiceHolder.INSTANCE;}

    private static class WarehouseTypeServiceHolder {
        public static final WarehouseTypeService INSTANCE =new WarehouseTypeService();
    }

    public boolean insertWarehouseType(WarehouseType warehouseType){
        return warehouseTypeRepository.save(warehouseType);
    }

    public boolean updateWarehouseType(WarehouseType warehouseType){
        return warehouseTypeRepository.update(warehouseType);
    }

    public boolean deleteWarehouseType(WarehouseType warehouseType){
        return warehouseTypeRepository.delete(warehouseType);
    }


    public WarehouseType getWarehouseType(String type) {
        List<WarehouseType> types = warehouseTypeRepository.getAll();

        for (WarehouseType w : types) {
            if(w.getType().equals(type))
                return w;
        }

        return null;
    }

    public ObservableList<WarehouseTypeListViewModel> getTypeObservable(){
        List<WarehouseType> warehouseTypes = warehouseTypeRepository.getAll();

        return FXCollections.observableList(
                warehouseTypes.stream().map(type -> new WarehouseTypeListViewModel(
                        type.getId(),
                        type.getType()
                )).collect(Collectors.toList()));
    }

    public List<WarehouseType> getAllTypes(){
        return warehouseTypeRepository.getAll();
    }
}
