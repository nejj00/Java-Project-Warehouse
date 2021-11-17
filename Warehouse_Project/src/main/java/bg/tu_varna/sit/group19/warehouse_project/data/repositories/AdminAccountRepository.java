package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.AdminAccount;

public class AdminAccountRepository extends BaseRepository<AdminAccount> {

    public AdminAccountRepository() {
        super(AdminAccount.class);
    }

    public static AdminAccountRepository getInstance() {
        return AdminAccountRepository.AdminAccountRepositoryHolder.INSTANCE;
    }

    private static class AdminAccountRepositoryHolder {
        public static final AdminAccountRepository INSTANCE = new AdminAccountRepository();
    }

}
