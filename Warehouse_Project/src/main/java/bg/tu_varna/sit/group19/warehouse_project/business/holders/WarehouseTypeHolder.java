package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseType;

public class WarehouseTypeHolder {
    private WarehouseType warehouseType = new WarehouseType();

    private Enums.OpenMode typeOpenMode;

    private final static WarehouseTypeHolder INSTANCE = new WarehouseTypeHolder();

    private WarehouseTypeHolder() {}

    public static WarehouseTypeHolder getInstance() {
        return INSTANCE;
    }

    public WarehouseType getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(WarehouseType warehouseType) {
        this.warehouseType = warehouseType;
    }

    public Enums.OpenMode getTypeOpenMode() {
        return typeOpenMode;
    }

    public void setTypeOpenMode(Enums.OpenMode typeOpenMode) {
        this.typeOpenMode = typeOpenMode;
    }
}
