package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;

public class OwnerAccountRepository extends BaseRepository<OwnerAccount> {

    public OwnerAccountRepository() {
        super(OwnerAccount.class);
    }

    public static OwnerAccountRepository getInstance()
    {
        return OwnerAccountRepository.OwnerAccountRepositoryHolder.INSTANCE;
    }

    private static class OwnerAccountRepositoryHolder {
        public static final OwnerAccountRepository INSTANCE = new OwnerAccountRepository();
    }
}
