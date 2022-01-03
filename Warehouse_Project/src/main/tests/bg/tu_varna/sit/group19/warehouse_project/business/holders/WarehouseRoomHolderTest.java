package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseRoomHolderTest {
    WarehouseRoomHolder holder;
    WarehouseRoom room = new WarehouseRoom();
    ClimateCondition condition = new ClimateCondition();


    @BeforeEach
    void setUp() {
        holder = WarehouseRoomHolder.getInstance();
        condition.setConditions("dry");
        room.setCondition(condition);
        room.setSize(250);
        room.setPrice(6750);
        holder.setWarehouseRoom(room);
    }

    @Test
    void getInstance() {
        assertEquals(WarehouseRoomHolder.getInstance(),holder);
    }

    @Test
    void getWarehouseRoom() {
        assertEquals(room,holder.getWarehouseRoom());
    }

    @Test
    void setWarehouseRoom() {
        room.setPrice(2000);
        holder.setWarehouseRoom(room);
        assertEquals(room,holder.getWarehouseRoom());
    }
}