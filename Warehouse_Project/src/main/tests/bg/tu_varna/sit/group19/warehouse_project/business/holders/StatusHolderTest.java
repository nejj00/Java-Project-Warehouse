package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusHolderTest {
    private StatusHolder holder;
    WarehouseStatus status = new WarehouseStatus();

    @BeforeEach
    void setUp() {
        holder = StatusHolder.getInstance();
        status.setStatus("in construction");
        holder.setWarehouseStatus(status);
    }

    @Test
    void getInstance() {
        assertEquals(holder,StatusHolder.getInstance());
    }

    @Test
    void getWarehouseStatus() {
        assertEquals(status,holder.getWarehouseStatus());
    }

    @Test
    void setWarehouseStatus() {
        assertAll(()->assertEquals("in construction",status.getStatus()));
    }
}