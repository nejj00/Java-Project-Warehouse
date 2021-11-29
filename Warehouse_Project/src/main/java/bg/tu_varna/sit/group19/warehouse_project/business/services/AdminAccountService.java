package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.AdminAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.AdminAccountRepository;

import java.util.List;

public class AdminAccountService {
    private final AdminAccountRepository adminAccountRepository = AdminAccountRepository.getInstance();

    public static AdminAccountService getInstance(){
        return AdminAccountService.AdminAccountServiceHolder.INSTANCE;
    }

    private static class AdminAccountServiceHolder {
        public static final AdminAccountService INSTANCE = new AdminAccountService();
    }

    public void insertAdminAccount(AdminAccount adminAccount){
        adminAccountRepository.save(adminAccount);
    }

    public void updateAdminAccount(AdminAccount adminAccount){
        adminAccountRepository.update(adminAccount);
    }

    public void deleteAdminAccount(AdminAccount adminAccount){
        adminAccountRepository.delete(adminAccount);
    }

    public Admin getAdminByUsername(String username){
        List<AdminAccount> adminAccounts = adminAccountRepository.getAll();
        Admin admin = new Admin();

        for (AdminAccount account: adminAccounts) {
            if(account.getUsername().equals(username))
                admin = account.getAdmin();
        }

        return admin;
    }
}
