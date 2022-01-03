package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseType;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseStatusRepository;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.StatusListViewModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class WarehouseStatusService {
    private final WarehouseStatusRepository warehouseStatusRepository = WarehouseStatusRepository.getInstance();

    public static WarehouseStatusService getInstance() { return WarehouseStatusServiceHolder.INSTANCE; }

    private static class WarehouseStatusServiceHolder {
        public static final WarehouseStatusService INSTANCE = new WarehouseStatusService();
    }

    public boolean insertWarehouseStatus(WarehouseStatus warehouseStatus){
        return warehouseStatusRepository.save(warehouseStatus);
    }

    public boolean updateWarehouseStatus(WarehouseStatus warehouseStatus){
        return warehouseStatusRepository.update(warehouseStatus);
    }

    public boolean deleteWarehouseStatus(WarehouseStatus warehouseStatus){
        return warehouseStatusRepository.delete(warehouseStatus);
    }


    public WarehouseStatus getWarehouseStatusByStatus (String status) {
        List<WarehouseStatus> statusList = warehouseStatusRepository.getAll();

        for( WarehouseStatus w : statusList) {
            if(w.getStatus().equals(status))
                return w;
        }

        return null;
    }

    public ObservableList<StatusListViewModel> getStatusObservable(){
        List<WarehouseStatus> warehousesStatus = warehouseStatusRepository.getAll();

        return FXCollections.observableList(
                warehousesStatus.stream().map(status -> new StatusListViewModel(
                        status.getId(),
                        status.getStatus()
                )).collect(Collectors.toList()));
    }

    public List<WarehouseStatus> getAllStatus(){
        return warehouseStatusRepository.getAll();
    }
}
