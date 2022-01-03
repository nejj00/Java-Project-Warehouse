package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterModelTest {

    private RegisterModel registerModel;

    @BeforeEach
    void setUp() {
        registerModel = new RegisterModel();
    }

    @Test
    void getWrongPasswordMessage() {
        assertEquals("Incorrect password. Please try again.", registerModel.getWrongPasswordMessage());
    }

    @Test
    void getEmptyFieldMessage() {
        assertEquals("Please don't leave empty fields.", registerModel.getEmptyFieldMessage());
    }

    @Test
    void getSelectAccountTypeMessage() {
        assertEquals("Please select account type, by choosing between the two\n options below.", registerModel.getSelectAccountTypeMessage());
    }

    @Test
    void getUsernameExists() {
        assertEquals("This username already exists.", registerModel.getUsernameExists());
    }
}