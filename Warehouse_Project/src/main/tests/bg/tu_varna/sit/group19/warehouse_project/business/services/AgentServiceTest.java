package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.AgentAccount;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AgentServiceTest {
    private AgentService agentService;
    private AgentAccountService agentAccountService;
    private Agent agent;
    private AgentAccount agentAccount;

    @BeforeEach
    void setUp() {
        agentService = AgentService.getInstance();
        agentAccountService = AgentAccountService.getInstance();
        agent = new Agent();
        agentAccount = new AgentAccount();
    }

    @Test
    @Order(1)
    void insertAgent() {
        agent.setFirstName("AgentTest");
        agent.setLastName("AgentTestov");
        agentAccount.setUsername("agent_test123");
        agentAccount.setPassword("test123");
        agentAccount.setAgent(agent);
        Assertions.assertAll(() -> assertTrue(agentService.insertAgent(agent)),
                () -> assertTrue(agentAccountService.insertAgentAccount(agentAccount)));
    }

    @Test
    @Order(2)
    void updateAgent() {
        agent = agentAccountService.getAgentByUsername("agent_test123");
        agent.setFirstName("AGENT_TEST");
        agent.setLastName("AGENT_TESTOV");
        agentAccount = agent.getAgentAccount();
        agentAccount.setUsername("AGENT_TEST123");

        Assertions.assertAll(() -> assertTrue(agentService.updateAgent(agent)),
                () -> assertTrue(agentAccountService.updateAgentAccount(agentAccount)));
    }

    @Test
    @Order(3)
    void deleteAgent() {
        agent = agentAccountService.getAgentByUsername("AGENT_TEST123");
        assertTrue(agentService.deleteAgent(agent));
    }
}