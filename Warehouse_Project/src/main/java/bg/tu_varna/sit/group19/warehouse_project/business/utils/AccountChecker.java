package bg.tu_varna.sit.group19.warehouse_project.business.utils;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.AdminAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.AgentAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.AdminAccountRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.AgentAccountRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.OwnerAccountRepository;

import java.util.HashSet;
import java.util.Set;

public class AccountChecker {
    private Set<String> accountUsernames = new HashSet<>();

    private AdminAccountRepository adminAccountRepository = AdminAccountRepository.getInstance();
    private OwnerAccountRepository ownerAccountRepository = OwnerAccountRepository.getInstance();
    private AgentAccountRepository agentAccountRepository = AgentAccountRepository.getInstance();

    public AccountChecker() {
        for (AdminAccount adminAccount : adminAccountRepository.getAll()) {
            this.accountUsernames.add(adminAccount.getUsername());
        };

        for (OwnerAccount ownerAccount : ownerAccountRepository.getAll()) {
            this.accountUsernames.add(ownerAccount.getUsername());
        };

        for (AgentAccount agentAccount : agentAccountRepository.getAll()) {
            this.accountUsernames.add(agentAccount.getUsername());
        };
    }

    public boolean accountExists(String username){
        if(accountUsernames.contains(username))
            return true;

        return false;
    }
}
