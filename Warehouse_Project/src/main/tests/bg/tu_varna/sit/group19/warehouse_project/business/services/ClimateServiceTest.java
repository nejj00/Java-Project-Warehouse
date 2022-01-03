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
        climateCondition.setConditions("test_cond");
        assertTrue(climateService.insertClimate(climateCondition));
    }

    @Test
    @Order(2)
    void updateClimate() {
        climateCondition = climateService.getClimateCondition("test_cond");
        climateCondition.setConditions("TEST_COND");
        assertTrue(climateService.updateClimate(climateCondition));
    }

    @Test
    @Order(3)
    void deleteClimate() {
        climateCondition = climateService.getClimateCondition("TEST_COND");
        assertTrue(climateService.deleteClimate(climateCondition));
    }
}