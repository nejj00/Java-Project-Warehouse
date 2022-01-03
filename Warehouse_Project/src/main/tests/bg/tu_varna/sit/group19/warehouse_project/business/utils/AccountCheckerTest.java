package bg.tu_varna.sit.group19.warehouse_project.business.utils;

import bg.tu_varna.sit.group19.warehouse_project.business.services.OwnerService;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.BaseWindowModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountCheckerTest {

    private AccountChecker accountChecker;
    private OwnerAccount ownerAccount;

    @BeforeEach
    void setUp() {
        accountChecker = new AccountChecker();
        ownerAccount = new OwnerAccount();
        ownerAccount.setUsername("ivan123");
        ownerAccount.setPassword("4321");
    }

    @Test
    void accountExists() {
        assertTrue(accountChecker.accountExists(ownerAccount.getUsername()),
                "Account does not exist");
    }

    @Test
    void checkPasswordSettings() {
        Enums enums = new Enums();
        assertTrue(accountChecker.checkPassword(ownerAccount.getUsername(), ownerAccount.getPassword(), enums),
                "Password does not match");
    }
}