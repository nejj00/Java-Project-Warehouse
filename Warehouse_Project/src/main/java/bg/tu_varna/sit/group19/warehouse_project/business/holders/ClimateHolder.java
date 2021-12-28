package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;

public class ClimateHolder {
    private ClimateCondition climateCondition = new ClimateCondition();

    private Enums.OpenMode climateOpenMode;

    private final static ClimateHolder INSTANCE = new ClimateHolder();

    private ClimateHolder() {}

    public static ClimateHolder getInstance() {
        return INSTANCE;
    }

    public ClimateCondition getClimateCondition() {
        return climateCondition;
    }

    public void setClimateCondition(ClimateCondition climateCondition) {
        this.climateCondition = climateCondition;
    }

    public Enums.OpenMode getClimateOpenMode() {
        return climateOpenMode;
    }

    public void setClimateOpenMode(Enums.OpenMode climateOpenMode) {
        this.climateOpenMode = climateOpenMode;
    }
}
