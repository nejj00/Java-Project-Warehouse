package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Renter;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RenterServiceTest {
    private RenterService renterService;
    private Renter renter;

    @BeforeEach
    void setUp() {
        renterService = RenterService.getInstance();
        renter = new Renter();
    }

    @Test
    @Order(1)
    void insertRenter() {
        renter.setFirstName("renter_test");
        renter.setLastName("renter_test");
        renter.setPhoneNumber("0000000");

        assertTrue(renterService.insertRenter(renter));
    }

    @Test
    @Order(2)
    void updateRenter() {
        renter = renterService.getRenterByNumber("0000000");
        renter.setFirstName("RENTER_TEST");

        assertTrue(renterService.updateRenter(renter));
    }

    @Test
    @Order(3)
    void deleteClimate() {
        renter = renterService.getRenterByNumber("0000000");
        assertTrue(renterService.deleteClimate(renter));
    }
}