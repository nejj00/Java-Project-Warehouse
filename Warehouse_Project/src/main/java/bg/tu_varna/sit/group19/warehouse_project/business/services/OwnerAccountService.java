package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.OwnerAccountRepository;

import java.util.List;

public class OwnerAccountService {
    private final OwnerAccountRepository ownerAccountRepository = OwnerAccountRepository.getInstance();

    public static OwnerAccountService getInstance(){
        return OwnerAccountService.OwnerAccountServiceHolder.INSTANCE;
    }

    private static class OwnerAccountServiceHolder {
        public static final OwnerAccountService INSTANCE = new OwnerAccountService();
    }

    public void insertOwnerAccount(OwnerAccount ownerAccount){
        ownerAccountRepository.save(ownerAccount);
    }

    public void updateOwnerAccount(OwnerAccount ownerAccount){
        ownerAccountRepository.update(ownerAccount);
    }

    public void deleteOwnerAccount(OwnerAccount ownerAccount){
        ownerAccountRepository.delete(ownerAccount);
    }

    public OwnerAccount getOwnerAccountById(Long id){
        return ownerAccountRepository.getById(id).get();
    }

    public Owner getOwnerByUsername(String username){
        List<OwnerAccount> ownerAccounts = ownerAccountRepository.getAll();
        Owner owner = new Owner();

        for (OwnerAccount account: ownerAccounts) {
            if(account.getUsername().equals(username))
                owner = account.getOwner();
        }

        return owner;
    }

}
