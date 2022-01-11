package bg.tu_varna.sit.group19.warehouse_project.business.services;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClimateServiceTest {

    private ClimateService climateService;
    private ClimateCondition climateCondition;

    @BeforeEach
    void setUp() {
        climateService = ClimateService.getInstance();
        climateCondition = new ClimateCondition();
    }

    @Test
    @Order(1)
    void insertClimate() {
        climateCondition.setTemperature(1);
        climateCondition.setHumidity(1);
        assertTrue(climateService.insertClimate(climateCondition));
    }

    @Test
    @Order(2)
    void updateClimate() {
        climateCondition = climateService.getClimateCondition(1, 1);
        climateCondition.setTemperature(2);
        climateCondition.setHumidity(2);
        assertTrue(climateService.updateClimate(climateCondition));
    }

    @Test
    @Order(3)
    void deleteClimate() {
        climateCondition = climateService.getClimateCondition(2, 2);
        assertTrue(climateService.deleteClimate(climateCondition));
    }
}