package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

public class ClimateListViewModel {
    private Long climateID;
    private String conditions;

    public ClimateListViewModel(Long ID, String conditions){
        this.climateID = ID;
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        return String.format("%s", conditions);
    }

    public Long getClimateID() {
        return climateID;
    }

    public void setClimateID(Long climateID) {
        this.climateID = climateID;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
}
