package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContractWindowModelTest {
    private ContractWindowModel contractWindowModel;

    @BeforeEach
    void setUp() {
        contractWindowModel = new ContractWindowModel();
    }

    @Test
    void getAlertTitle() {
        assertEquals("Contract", contractWindowModel.getAlertTitle());
    }

    @Test
    void getAlertEmptyFields() {
        assertEquals("Please fill the empty fields.", contractWindowModel.getAlertEmptyFields());

    }

    @Test
    void getAlertDoubleCheckHeader() {
        assertEquals("Double Check", contractWindowModel.getAlertDoubleCheckHeader());
    }

    @Test
    void getAlertConfirmQuestion() {
        assertEquals("Are you sure you want to make this contract?\n" +
                "Please double check you data before proceeding.", contractWindowModel.getAlertConfirmQuestion());
    }
}