package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.business.utils.AccountChecker;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OwnerServiceTest {
    private OwnerService ownerService;
    private OwnerAccountService ownerAccountService;
    private Owner owner;
    private OwnerAccount ownerAccount;

    @BeforeEach
    void setUp() {
        ownerService = OwnerService.getInstance();
        ownerAccountService = OwnerAccountService.getInstance();
        owner = new Owner();
        ownerAccount = new OwnerAccount();
    }

    @Test
    @Order(1)
    void insertOwner() {
        owner.setFirstName("Test");
        owner.setLastName("Testov");
        ownerAccount.setUsername("test123");
        ownerAccount.setPassword("test123");
        ownerAccount.setOwner(owner);
        Assertions.assertAll(() -> assertTrue(ownerService.insertOwner(owner)),
                             () -> assertTrue(ownerAccountService.insertOwnerAccount(ownerAccount)));
    }

    @Test
    @Order(2)
    void updateOwner() {
        owner = ownerAccountService.getOwnerByUsername("test123");
        owner.setFirstName("TEST");
        owner.setLastName("TESTOV");
        ownerAccount = owner.getOwnerAccount();
        ownerAccount.setUsername("TEST123");

        Assertions.assertAll(() -> assertTrue(ownerService.updateOwner(owner)),
                () -> assertTrue(ownerAccountService.updateOwnerAccount(ownerAccount)));
    }

    @Test
    @Order(3)
    void deleteOwner() {
        owner = ownerAccountService.getOwnerByUsername("TEST123");
        assertTrue(ownerService.deleteOwner(owner));
    }
}