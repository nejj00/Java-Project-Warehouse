package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClimateHolderTest {
    private ClimateHolder holder;
    private ClimateCondition condition = new ClimateCondition();

    @BeforeEach
    void setUp() {
        holder = ClimateHolder.getInstance();
        condition.setConditions("dry");
    }

    @Test
    void getInstance() {
        assertEquals(ClimateHolder.getInstance(),holder);
    }

    @Test
    void getClimateCondition() {
        assertEquals("dry",condition.getConditions());
    }

    @Test
    void setClimateCondition() {
        ClimateCondition climateCondition = new ClimateCondition();
        climateCondition.setConditions("dry");
        assertEquals(condition,climateCondition);
    }
}