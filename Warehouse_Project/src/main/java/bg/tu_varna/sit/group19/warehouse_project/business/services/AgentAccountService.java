package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.*;
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

    public boolean insertAgentAccount(AgentAccount agentAccount){
        return agentAccountRepository.save(agentAccount);
    }

    public boolean updateAgentAccount(AgentAccount agentAccount){
        return agentAccountRepository.update(agentAccount);
    }

    public boolean deleteAgentAccount(AgentAccount agentAccount){
        return agentAccountRepository.delete(agentAccount);
    }

    public AgentAccount getAgentAccountById(Long id){
        return agentAccountRepository.getById(id).get();
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
