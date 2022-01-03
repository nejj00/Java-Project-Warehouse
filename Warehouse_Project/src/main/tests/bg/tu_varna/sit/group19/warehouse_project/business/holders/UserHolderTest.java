package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserHolderTest {

    private UserHolder holder;
    private Owner owner = new Owner();
    private Agent agent = new Agent();
    private Admin admin = new Admin();

    @BeforeEach
    void setUp() {
        holder= UserHolder.getInstance();
        owner.setFirstName("Ivan");
        owner.setLastName("Ivanov");
        agent.setFirstName("Pesho");
        agent.setLastName("Petrov");
        agent.setRating(20);
        admin.setFirstName("Nedjib");
        admin.setLastName("Ahmed");
        holder.setOwner(owner);
        holder.setAgent(agent);
        holder.setAdmin(admin);
    }

    @Test
    void getInstance() {
        assertEquals(UserHolder.getInstance(),holder);
    }

    @Test
    void getOwner() {
        assertEquals(owner,holder.getOwner());
    }

    @Test
    void setOwner() {
        assertAll(()->assertEquals("Ivan",owner.getFirstName()),
                ()->assertEquals("Ivanov",owner.getLastName()));
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

    @Test
    void getAdmin() {
        assertEquals(admin,holder.getAdmin());
    }

    @Test
    void setAdmin() {
        assertAll(()->assertEquals("Nedjib",admin.getFirstName()),
                ()->assertEquals("Ahmed",admin.getLastName()));
    }
}