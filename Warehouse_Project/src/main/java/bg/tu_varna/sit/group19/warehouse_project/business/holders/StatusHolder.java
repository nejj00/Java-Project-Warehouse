package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;

public class StatusHolder {
    private WarehouseStatus warehouseStatus = new WarehouseStatus();

    private Enums.OpenMode warehouseStatusOpenMode;

    private final static StatusHolder INSTANCE = new StatusHolder();

    private StatusHolder() {}

    public static StatusHolder getInstance() {
        return INSTANCE;
    }

    public WarehouseStatus getWarehouseStatus() {
        return warehouseStatus;
    }

    public void setWarehouseStatus(WarehouseStatus warehouseStatus) {
        this.warehouseStatus = warehouseStatus;
    }

    public Enums.OpenMode getWarehouseStatusOpenMode() {
        return warehouseStatusOpenMode;
    }

    public void setWarehouseStatusOpenMode(Enums.OpenMode warehouseStatusOpenMode) {
        this.warehouseStatusOpenMode = warehouseStatusOpenMode;
    }
}
