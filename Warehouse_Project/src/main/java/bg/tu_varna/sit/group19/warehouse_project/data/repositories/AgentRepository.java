package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;

public class AgentRepository extends BaseRepository<Agent> {

    public AgentRepository() {
        super(Agent.class);
    }

    public static AgentRepository getInstance()
    {
        return AgentRepository.AgentRepositoryHolder.INSTANCE;
    }

    private static class AgentRepositoryHolder {
        public static final AgentRepository INSTANCE = new AgentRepository();
    }
}
