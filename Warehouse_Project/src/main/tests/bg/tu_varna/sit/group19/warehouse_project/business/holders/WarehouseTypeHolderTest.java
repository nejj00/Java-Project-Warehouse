package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTypeHolderTest {
    WarehouseTypeHolder holder;
    WarehouseType type = new WarehouseType();

    @BeforeEach
    void setUp() {
        holder = WarehouseTypeHolder.getInstance();
        type.setType("tobacco");
        holder.setWarehouseType(type);
    }

    @Test
    void getInstance() {
        assertEquals(WarehouseTypeHolder.getInstance(),holder);
    }

    @Test
    void getWarehouseType() {
        assertEquals(type,holder.getWarehouseType());
    }

    @Test
    void setWarehouseType() {
        type.setType("food");
        holder.setWarehouseType(type);
        assertEquals(type,holder.getWarehouseType());
    }
}