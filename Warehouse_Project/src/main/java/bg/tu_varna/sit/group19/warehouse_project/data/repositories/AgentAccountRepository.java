package bg.tu_varna.sit.group19.warehouse_project.data.repositories;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.AgentAccount;

public class AgentAccountRepository extends BaseRepository<AgentAccount> {

    public AgentAccountRepository() {
        super(AgentAccount.class);
    }

    public static AgentAccountRepository getInstance()
    {
        return AgentAccountRepository.AgentAccountRepositoryHolder.INSTANCE;
    }

    private static class AgentAccountRepositoryHolder {
        public static final AgentAccountRepository INSTANCE = new AgentAccountRepository();
    }
}
