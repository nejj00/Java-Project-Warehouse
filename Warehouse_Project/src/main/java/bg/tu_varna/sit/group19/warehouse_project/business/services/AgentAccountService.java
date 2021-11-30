package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.AdminAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.AgentAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.AgentAccountRepository;

import java.util.List;

public class AgentAccountService {
    private final AgentAccountRepository agentAccountRepository = AgentAccountRepository.getInstance();

    public static AgentAccountService getInstance(){
        return AgentAccountService.AgentAccountServiceHolder.INSTANCE;
    }

    private static class AgentAccountServiceHolder {
        public static final AgentAccountService INSTANCE = new AgentAccountService();
    }

    public void insertAgentAccount(AgentAccount agentAccount){
        agentAccountRepository.save(agentAccount);
    }

    public void updateAgentAccount(AgentAccount agentAccount){
        agentAccountRepository.update(agentAccount);
    }

    public void deleteAgentAccount(AgentAccount agentAccount){
        agentAccountRepository.delete(agentAccount);
    }

    public Agent getAgentByUsername(String username){
        List<AgentAccount> agentAccounts = agentAccountRepository.getAll();
        Agent agent = new Agent();

        for (AgentAccount account: agentAccounts) {
            if(account.getUsername().equals(username))
                agent = account.getAgent();
        }

        return agent;
    }
}
