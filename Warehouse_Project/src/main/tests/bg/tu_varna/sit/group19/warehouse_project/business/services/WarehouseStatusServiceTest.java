package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WarehouseStatusServiceTest {
    private WarehouseStatusService warehouseStatusService;
    private WarehouseStatus warehouseStatus;

    @BeforeEach
    void setUp() {
        warehouseStatusService = WarehouseStatusService.getInstance();
        warehouseStatus = new WarehouseStatus();
    }

    @Test
    @Order(1)
    void insertWarehouseStatus() {
        warehouseStatus.setStatus("status_test");
        assertTrue(warehouseStatusService.insertWarehouseStatus(warehouseStatus));
    }

    @Test
    @Order(2)
    void updateWarehouseStatus() {
        warehouseStatus = warehouseStatusService.getWarehouseStatusByStatus("status_test");
        warehouseStatus.setStatus("STATUS_TEST");
        assertTrue(warehouseStatusService.updateWarehouseStatus(warehouseStatus));
    }

    @Test
    @Order(3)
    void deleteWarehouseStatus() {
        warehouseStatus = warehouseStatusService.getWarehouseStatusByStatus("STATUS_TEST");
        assertTrue(warehouseStatusService.deleteWarehouseStatus(warehouseStatus));
    }
}