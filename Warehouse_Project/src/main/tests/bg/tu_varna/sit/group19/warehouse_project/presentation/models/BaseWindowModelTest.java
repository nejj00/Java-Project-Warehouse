package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseWindowModelTest {

    private BaseWindowModel baseWindowModel;

    @BeforeEach
    void setUp() {
        baseWindowModel = new BaseWindowModel();
    }

    @Test
    void getAlertTitle() {
        assertEquals("Warning", baseWindowModel.getAlertTitle());
    }

    @Test
    void getAlertEmptyFields() {
        assertEquals("Please fill in all empty fields.", baseWindowModel.getAlertEmptyFields());
    }

    @Test
    void getAlertDeleteTitle() {
        assertEquals("Delete", baseWindowModel.getAlertDeleteTitle());
    }

    @Test
    void getAlertDeleteMessage() {
        assertEquals("Are you sure you want to delete this record?", baseWindowModel.getAlertDeleteMessage());
    }
}