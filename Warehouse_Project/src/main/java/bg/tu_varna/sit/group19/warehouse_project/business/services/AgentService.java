package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.AgentAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.AgentAccountRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.AgentRepository;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.UserListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AgentService {
    private final AgentRepository agentRepository = AgentRepository.getInstance();
    private final AgentAccountRepository agentAccountRepository = AgentAccountRepository.getInstance();

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
        agentAccountRepository.delete(agent.getAgentAccount());
        //agentRepository.delete(agent);
    }

    public Agent getAgentById(Long id){
        return agentRepository.getById(id).get();
    }

    public ObservableList<UserListViewModel<AgentAccount>> getAllAgents(){
        List<Agent> agents = agentRepository.getAll();

        return FXCollections.observableList(
                agents.stream().map(agent -> new UserListViewModel<AgentAccount>(
                        agent.getFirstName(),
                        agent.getLastName(),
                        agent.getId(),
                        agent.getAgentAccount(),
                        agent.getAgentAccount().getUsername()
                )).collect(Collectors.toList()));
    }

    public Agent getAgentByName(String firstName,String lastName){
        List<Agent> agents = agentRepository.getAll();

        for (Agent agent:agents) {
            if(agent.getFirstName().equals(firstName) &&agent.getLastName().equals(lastName))
                return agent;
        }
        return null;
    }
}
