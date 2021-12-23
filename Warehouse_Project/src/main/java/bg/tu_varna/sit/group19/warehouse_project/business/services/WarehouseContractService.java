package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseContract;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseContractRepository;

public class WarehouseContractService {
    private final WarehouseContractRepository contractRepository = WarehouseContractRepository.getInstance();

    public static WarehouseContractService getInstance() {
        return WarehouseContractService.WarehouseContractServiceHolder.INSTANCE;
    }

    private static class WarehouseContractServiceHolder {
        public static final WarehouseContractService INSTANCE = new WarehouseContractService();
    }

    public void insertContract(WarehouseContract contract) {
        contractRepository.save(contract);
    }
    public void updateContract(WarehouseContract contract) {
        contractRepository.update(contract);
    }
    public void deleteContract(WarehouseContract contract) {
        contractRepository.delete(contract);
    }
}
