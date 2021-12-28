package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

public class WarehouseTypeListViewModel {
    private Long typeID;
    private String type;

    public WarehouseTypeListViewModel(Long ID, String type){
        this.typeID = ID;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s", type);
    }

    public Long getTypeID() {
        return typeID;
    }

    public void setTypeID(Long typeID) {
        this.typeID = typeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
