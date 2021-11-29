package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.OwnerRepository;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.OwnerListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class OwnerService {
    private final OwnerRepository ownerRepository = OwnerRepository.getInstance();

    public static OwnerService getInstance(){
        return OwnerServiceHolder.INSTANCE;
    }

    private static class OwnerServiceHolder {
        public static final OwnerService INSTANCE = new OwnerService();
    }

    public void insertOwner(Owner owner){
        ownerRepository.save(owner);
    }

    public void updateOwner(Owner agent){
        ownerRepository.update(agent);
    }

    public void deleteOwner(Owner agent){
        ownerRepository.delete(agent);
    }

    public ObservableList<OwnerListViewModel> getAllOwners(){
        List<Owner> owners = ownerRepository.getAll();

        return FXCollections.observableList(
                owners.stream().map(owner -> new OwnerListViewModel(
                        owner.getFirstName(),
                        owner.getLastName()
                )).collect(Collectors.toList()));
    }
}
