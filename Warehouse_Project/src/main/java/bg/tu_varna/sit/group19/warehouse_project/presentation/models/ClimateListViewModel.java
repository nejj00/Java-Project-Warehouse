package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

public class ClimateListViewModel {
    private Long climateID;
    private int temperature;
    private int humidity;

    public ClimateListViewModel(Long ID, int temperature, int humidity){
        this.climateID = ID;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return String.format("temp: %d, humid: %d%", temperature, humidity);
    }

    public Long getClimateID() {
        return climateID;
    }

    public void setClimateID(Long climateID) {
        this.climateID = climateID;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
