package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseType;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseTypeRepository;

import java.util.List;

public class WarehouseTypeService {
    private final WarehouseTypeRepository warehouseTypeRepository = WarehouseTypeRepository.getInstance();

    public static WarehouseTypeService getInstance() {return WarehouseTypeServiceHolder.INSTANCE;}

    private static class WarehouseTypeServiceHolder {
        public static final WarehouseTypeService INSTANCE =new WarehouseTypeService();
    }

    public void insertWarehouseType(WarehouseType warehouseType){
        warehouseTypeRepository.save(warehouseType);
    }

    public void updateWarehouseType(WarehouseType warehouseType){
        warehouseTypeRepository.update(warehouseType);
    }

    public void deleteWarehouseType(WarehouseType warehouseType){
        warehouseTypeRepository.delete(warehouseType);
    }


    public WarehouseType getWarehouseType(String type) {
        List<WarehouseType> types = warehouseTypeRepository.getAll();

        for (WarehouseType w : types) {
            if(w.getType().equals(type))
                return w;
        }

        return null;
    }

    public List<WarehouseType> getAllTypes(){
        return warehouseTypeRepository.getAll();
    }
}
