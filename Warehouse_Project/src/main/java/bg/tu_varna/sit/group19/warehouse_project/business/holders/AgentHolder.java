package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;

public class AgentHolder {
    private Agent agent = new Agent();

    private final static AgentHolder INSTANCE = new AgentHolder();
    public AgentHolder(){}
    public static AgentHolder getInstance(){
        return INSTANCE;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
