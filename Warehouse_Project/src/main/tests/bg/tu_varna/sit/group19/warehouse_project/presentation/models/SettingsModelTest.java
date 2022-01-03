package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsModelTest {

    private SettingsModel settingsModel;

    @BeforeEach
    void setUp() {
        settingsModel = new SettingsModel();
    }

    @Test
    void getLabelNewPassword() {
        assertEquals("New password fields do not match", settingsModel.getLabelNewPassword());
    }

    @Test
    void getLabelPassword() {
        assertEquals("Password is incorrect", settingsModel.getLabelPassword());

    }

    @Test
    void getAlertTitle() {
        assertEquals("Settings", settingsModel.getAlertTitle());

    }

    @Test
    void getAlertUsername() {
        assertEquals("This username already exists.", settingsModel.getAlertUsername());

    }

    @Test
    void getAlertHeader() {
        assertEquals("Update", settingsModel.getAlertHeader());

    }

    @Test
    void getAlertProfileUpdated() {
        assertEquals("Your profile has been successfully updated.", settingsModel.getAlertProfileUpdated());

    }
}