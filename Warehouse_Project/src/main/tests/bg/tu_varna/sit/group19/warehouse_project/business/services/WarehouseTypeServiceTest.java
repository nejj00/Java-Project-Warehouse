package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseType;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WarehouseTypeServiceTest {

    private WarehouseTypeService warehouseTypeService;
    private WarehouseType warehouseType;

    @BeforeEach
    void setUp() {
        warehouseTypeService = WarehouseTypeService.getInstance();
        warehouseType = new WarehouseType();
    }

    @Test
    @Order(1)
    void insertWarehouseType() {
        warehouseType.setType("type_test");
        assertTrue(warehouseTypeService.insertWarehouseType(warehouseType));
    }

    @Test
    @Order(2)
    void updateWarehouseType() {
        warehouseType = warehouseTypeService.getWarehouseType("type_test");
        warehouseType.setType("TYPE_TEST");
        assertTrue(warehouseTypeService.updateWarehouseType(warehouseType));
    }

    @Test
    @Order(3)
    void deleteWarehouseType() {
        warehouseType = warehouseTypeService.getWarehouseType("TYPE_TEST");
        assertTrue(warehouseTypeService.deleteWarehouseType(warehouseType));
    }
}