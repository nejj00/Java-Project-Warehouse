package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Renter;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseContract;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.OwnerRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseContractRepository;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.ContractListViewModel;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.WarehouseListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static bg.tu_varna.sit.group19.warehouse_project.presentation.controllers.AgentWindowController.owner;

public class WarehouseContractService {
    private final WarehouseContractRepository contractRepository = WarehouseContractRepository.getInstance();
    private final OwnerRepository ownerRepository = OwnerRepository.getInstance();

    public static WarehouseContractService getInstance() {
        return WarehouseContractService.WarehouseContractServiceHolder.INSTANCE;
    }

    private static class WarehouseContractServiceHolder {
        public static final WarehouseContractService INSTANCE = new WarehouseContractService();
    }

    public boolean insertContract(WarehouseContract contract) {
        return contractRepository.save(contract);
    }
    public boolean updateContract(WarehouseContract contract) {
        return contractRepository.update(contract);
    }
    public boolean deleteContract(WarehouseContract contract) {
        return contractRepository.delete(contract);
    }

    public ObservableList<ContractListViewModel> getAllContracts() {
        List<WarehouseContract> contractList = contractRepository.getAll();

        return FXCollections.observableList(
                contractList.stream().map(contract -> new ContractListViewModel(
                        contract.getId(),
                        contract.getFromDate(),
                        contract.getToDate(),
                        contract.getPrice(),
                        contract.getWarehouseRoom(),
                        contract.getAgent(),
                        contract.getRenter(),
                        contract.getOwner()
                )).collect(Collectors.toList()));
    }

    public ObservableList<ContractListViewModel> getOwnersContracts(Owner owner) {
        return FXCollections.observableList(
                owner.getContracts().stream().map(contract -> new ContractListViewModel(
                        contract.getId(),
                        contract.getFromDate(),
                        contract.getToDate(),
                        contract.getPrice(),
                        contract.getWarehouseRoom(),
                        contract.getAgent(),
                        contract.getRenter(),
                        contract.getOwner()
                )).collect(Collectors.toList()));
    }

    public ObservableList<ContractListViewModel> getRenterContracts(Renter renter) {
        return FXCollections.observableList(
                renter.getContracts().stream().map(contract -> new ContractListViewModel(
                        contract.getId(),
                        contract.getFromDate(),
                        contract.getToDate(),
                        contract.getPrice(),
                        contract.getWarehouseRoom(),
                        contract.getAgent(),
                        contract.getRenter(),
                        contract.getOwner()
                )).collect(Collectors.toList()));
    }
}
