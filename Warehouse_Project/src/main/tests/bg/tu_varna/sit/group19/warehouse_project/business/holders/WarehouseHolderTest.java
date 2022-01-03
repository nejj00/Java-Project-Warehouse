package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseHolderTest {
    private WarehouseHolder holder;
    private Warehouse warehouse = new Warehouse();
    private WarehouseType warehouseType = new WarehouseType();
    private WarehouseStatus warehouseStatus = new WarehouseStatus();
    private Owner owner = new Owner();

    @BeforeEach
    void setUp() {
        holder = WarehouseHolder.getInstance();
        owner.setFirstName("Mitko");
        owner.setLastName("Vladimirov");
        warehouseType.setType("food");
        warehouseStatus.setStatus("free");
        warehouse.setStatus(warehouseStatus);
        warehouse.setOwner(owner);
        warehouse.setSize(250);
        warehouse.setWarehouseAddress("Address");
        warehouse.setType(warehouseType);
    }

    @Test
    void getInstance() {
        assertEquals(WarehouseHolder.getInstance(),holder);
    }

    @Test
    void getSize() {
        assertEquals(250,warehouse.getSize());
    }

    @Test
    void setSize() {
        warehouse.setSize(300);
        assertEquals(300,warehouse.getSize());
    }

    @Test
    void getAddress() {
        assertEquals("Address",warehouse.getWarehouseAddress());
    }

    @Test
    void setAddress() {
        warehouse.setWarehouseAddress("street");
        assertEquals("street",warehouse.getWarehouseAddress());
    }

    @Test
    void getWarehouseType() {
        assertEquals(warehouseType,warehouse.getType());
    }

    @Test
    void setWarehouseType() {
        assertEquals("food",warehouse.getType().getType());
    }

    @Test
    void getWarehouseStatus() {
        assertEquals(warehouseStatus,warehouse.getStatus());
    }

    @Test
    void setWarehouseStatus() {
        assertEquals("free",warehouse.getStatus().getStatus());
    }

    @Test
    void getOwner() {
        assertEquals(owner,warehouse.getOwner());
    }

    @Test
    void setOwner() {
        assertAll(()->assertEquals("Mitko",owner.getFirstName()),
                ()->assertEquals("Vladimirov",owner.getLastName()));
    }
}