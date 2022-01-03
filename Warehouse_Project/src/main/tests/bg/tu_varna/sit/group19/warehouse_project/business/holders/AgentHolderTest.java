package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgentHolderTest {

    private AgentHolder holder;

    Agent agent = new Agent();

    @BeforeEach
    void setUp() {
        holder = AgentHolder.getInstance();
        agent.setFirstName("Pesho");
        agent.setLastName("Petrov");
        agent.setRating(20);
        holder.setAgent(agent);

    }

    @Test
    void getInstance() {
        assertEquals(AgentHolder.getInstance(),holder);
    }

    @Test
    void getAgent() {
        assertEquals(agent,holder.getAgent());
    }

    @Test
    void setAgent() {
        assertAll(()->assertEquals("Pesho",agent.getFirstName()),
                ()->assertEquals("Petrov",agent.getLastName()),
                ()->assertEquals(20,agent.getRating()));
    }
}