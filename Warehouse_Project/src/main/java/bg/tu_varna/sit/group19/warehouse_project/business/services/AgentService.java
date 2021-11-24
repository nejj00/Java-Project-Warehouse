package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.AgentRepository;

public class AgentService {
    private final AgentRepository agentRepository = AgentRepository.getInstance();

    public static AgentService getInstance(){
        return AgentService.AgentServiceHolder.INSTANCE;
    }

    private static class AgentServiceHolder {
        public static final AgentService INSTANCE = new AgentService();
    }

    public void insertAgent(Agent agent){
        agentRepository.save(agent);
    }

    public void updateAgent(Agent agent){
        agentRepository.update(agent);
    }

    public void deleteAgent(Agent agent){
        agentRepository.delete(agent);
    }
}
