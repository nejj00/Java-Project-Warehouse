package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerHolderTest {
    private OwnerHolder holder;
    private Owner owner = new Owner();

    @BeforeEach
    void setUp() {
        holder = OwnerHolder.getInstance();
        owner.setFirstName("Ivan");
        owner.setLastName("Ivanov");
        holder.setOwner(owner);
    }

    @Test
    void getInstance() {
        assertEquals(OwnerHolder.getInstance(),holder);
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
}