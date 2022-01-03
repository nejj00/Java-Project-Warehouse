package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.OwnerAccountRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.OwnerRepository;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.UserListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class OwnerService {
    private final OwnerRepository ownerRepository = OwnerRepository.getInstance();
    private final OwnerAccountRepository ownerAccountRepository = OwnerAccountRepository.getInstance();

    public static OwnerService getInstance(){
        return OwnerServiceHolder.INSTANCE;
    }

    private static class OwnerServiceHolder {
        public static final OwnerService INSTANCE = new OwnerService();
    }

    public boolean insertOwner(Owner owner){
        return ownerRepository.save(owner);
    }

    public boolean updateOwner(Owner owner){
        return ownerRepository.update(owner);
    }

    public boolean deleteOwner(Owner owner){
        return ownerAccountRepository.delete(owner.getOwnerAccount()); // when you call delete account it also deletes the user
        //ownerRepository.delete(owner);                        // because of to 1:1 relation user:account
    }

    public Owner getOwnerById(Long id){
        return ownerRepository.getById(id).get();
    }

    public ObservableList<UserListViewModel<OwnerAccount>> getAllOwners(){
        List<Owner> owners = ownerRepository.getAll();

        return FXCollections.observableList(
                owners.stream().map(owner -> new UserListViewModel<OwnerAccount>(
                        owner.getFirstName(),
                        owner.getLastName(),
                        owner.getId(),
                        owner.getOwnerAccount(),
                        owner.getOwnerAccount().getUsername()
                )).collect(Collectors.toList()));
    }

    public Owner getOwnerByName(String firstName,String lastName) {
        List<Owner> owners = ownerRepository.getAll();

        for (Owner o : owners){
            if(o.getFirstName().equals(firstName) && o.getLastName().equals(lastName))
                return o;
        }

        return null;
    }
}
