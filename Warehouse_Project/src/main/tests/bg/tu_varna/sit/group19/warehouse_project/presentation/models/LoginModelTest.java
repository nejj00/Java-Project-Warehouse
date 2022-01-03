package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginModelTest {

    private LoginModel loginModel;

    @BeforeEach
    void setUp() {
        loginModel = new LoginModel();
    }

    @Test
    void getWrongMessage() {
        assertEquals("Incorrect username or password. Please try again.", loginModel.getWrongMessage());
    }

}