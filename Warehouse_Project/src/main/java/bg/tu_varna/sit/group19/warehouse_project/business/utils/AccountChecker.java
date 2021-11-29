package bg.tu_varna.sit.group19.warehouse_project.business.utils;

import bg.tu_varna.sit.group19.warehouse_project.common.AccountTypeEnum;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.AdminAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.AgentAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.AdminAccountRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.AgentAccountRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.OwnerAccountRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountChecker {

    private final AdminAccountRepository adminAccountRepository = AdminAccountRepository.getInstance();
    private final OwnerAccountRepository ownerAccountRepository = OwnerAccountRepository.getInstance();
    private final AgentAccountRepository agentAccountRepository = AgentAccountRepository.getInstance();

    List<AdminAccount> adminAccounts = adminAccountRepository.getAll();
    List<OwnerAccount> ownerAccounts = ownerAccountRepository.getAll();
    List<AgentAccount> agentAccounts = agentAccountRepository.getAll();

    private final Set<String> accountUsernames = new HashSet<>();

    public AccountChecker() {
        for (AdminAccount adminAccount : adminAccounts) {
            this.accountUsernames.add(adminAccount.getUsername());
        };

        for (OwnerAccount ownerAccount : ownerAccounts) {
            this.accountUsernames.add(ownerAccount.getUsername());
        };

        for (AgentAccount agentAccount : agentAccounts) {
            this.accountUsernames.add(agentAccount.getUsername());
        };
    }

    public boolean accountExists(String username){
        return accountUsernames.contains(username);
    }


    public boolean checkPassword(String username, String password, AccountTypeEnum accountTypeEnum){
        for (AdminAccount adminAccount : adminAccounts) {
            if(username.equals(adminAccount.getUsername()) && password.equals(adminAccount.getPassword())) {
                accountTypeEnum.setAccountType(AccountTypeEnum.AccountType.Admin);
                return true;
            }
        }
        for (OwnerAccount ownerAccount : ownerAccounts) {
            if(username.equals(ownerAccount.getUsername()) && password.equals(ownerAccount.getPassword())) {
                accountTypeEnum.setAccountType(AccountTypeEnum.AccountType.Owner);
                return true;
            }
        }
        for (AgentAccount agentAccount : agentAccounts) {
            if(username.equals(agentAccount.getUsername()) && password.equals(agentAccount.getPassword())) {
                accountTypeEnum.setAccountType(AccountTypeEnum.AccountType.Agent);
                return true;
            }
        }
        return false;
    }

}
