package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.business.utils.WarehouseWithRooms;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Warehouse;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseWithRoomsHolderTest {
    WarehouseWithRoomsHolder holder;
    WarehouseWithRooms warehouseWithRooms = new WarehouseWithRooms();
    Warehouse warehouse = new Warehouse();
    List<WarehouseRoom> rooms= new LinkedList<WarehouseRoom>();

    @BeforeEach
    void setUp() {
        holder = WarehouseWithRoomsHolder.getInstance();
        warehouse.setWarehouseAddress("address");
        warehouse.setSize(2345);
        WarehouseRoom room = new WarehouseRoom();
        room.setPrice(2344);
        room.setSize(123);
        rooms.add(room);
        warehouseWithRooms.setWarehouse(warehouse);
        warehouseWithRooms.setWarehouseRooms(rooms);
        holder.setWarehouseWithRooms(warehouseWithRooms);
    }

    @Test
    void getInstance() {
        assertEquals(WarehouseWithRoomsHolder.getInstance(),holder);
    }

    @Test
    void getWarehouseWithRooms() {
        assertEquals(warehouseWithRooms,holder.getWarehouseWithRooms());
    }

    @Test
    void setWarehouseWithRooms() {
        WarehouseRoom room = new WarehouseRoom();
        room.setSize(1232);
        room.setPrice(5322);
        rooms.add(room);
        warehouseWithRooms.setWarehouseRooms(rooms);
        assertEquals(warehouseWithRooms,holder.getWarehouseWithRooms());
    }
}