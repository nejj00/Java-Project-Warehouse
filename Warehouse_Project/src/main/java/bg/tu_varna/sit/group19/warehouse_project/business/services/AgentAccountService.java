package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.AgentAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.AgentAccountRepository;

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
}
