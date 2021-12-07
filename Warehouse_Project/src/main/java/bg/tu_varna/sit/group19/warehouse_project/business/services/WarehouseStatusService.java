package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseStatusRepository;

import java.util.List;

public class WarehouseStatusService {
    private final WarehouseStatusRepository warehouseStatusRepository = WarehouseStatusRepository.getInstance();

    public static WarehouseStatusService getInstance() { return WarehouseStatusServiceHolder.INSTANCE; }

    private static class WarehouseStatusServiceHolder {
        public static final WarehouseStatusService INSTANCE = new WarehouseStatusService();
    }

    public WarehouseStatus getWarehouseStatusByStatus (String status) {
        List<WarehouseStatus> statusList = warehouseStatusRepository.getAll();

        for( WarehouseStatus w : statusList) {
            if(w.getStatus().equals(status))
                return w;
        }

        return null;
    }
}
