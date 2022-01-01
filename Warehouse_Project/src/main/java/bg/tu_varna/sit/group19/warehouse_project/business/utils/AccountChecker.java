package bg.tu_varna.sit.group19.warehouse_project.business.utils;

import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.*;
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


    public boolean checkPassword(String username, String password, Enums enums){
        for (AdminAccount adminAccount : adminAccounts) {
            if(username.equals(adminAccount.getUsername()) && password.equals(adminAccount.getPassword())) {
                enums.setAccountType(Enums.AccountType.Admin);
                return true;
            }
        }
        for (OwnerAccount ownerAccount : ownerAccounts) {
            if(username.equals(ownerAccount.getUsername()) && password.equals(ownerAccount.getPassword())) {
                enums.setAccountType(Enums.AccountType.Owner);
                return true;
            }
        }
        for (AgentAccount agentAccount : agentAccounts) {
            if(username.equals(agentAccount.getUsername()) && password.equals(agentAccount.getPassword())) {
                enums.setAccountType(Enums.AccountType.Agent);
                return true;
            }
        }
        return false;
    }

    public boolean checkPasswordSettings(Object user, String username, String password, Enums.AccountType accountType){
        switch (accountType)
        {
            case Admin:
                Admin admin = (Admin) user;
                for (AdminAccount adminAccount : adminAccounts) {
                    if(admin.getAdminAccount().equals(adminAccount)) {
                        return true;
                    }
                }
                break;
            case Owner:
                Owner owner = (Owner) user;
                for (OwnerAccount ownerAccount : ownerAccounts) {
                    if(owner.getOwnerAccount().equals(ownerAccount)) {
                        return true;
                    }
                }
                break;
            case Agent:
                Agent agent = (Agent) user;
                for (AgentAccount agentAccount : agentAccounts) {
                    if(agent.getAgentAccount().equals(agentAccount)) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }
}
