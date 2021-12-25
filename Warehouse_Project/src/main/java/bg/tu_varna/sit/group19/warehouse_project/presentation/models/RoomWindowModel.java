package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

public class RoomWindowModel {
    private final String alertTitle;
    private final String alertRoomSize;
    private final String alertEmptyFields;

    public RoomWindowModel(){
        this.alertTitle = "Warehouse Room Message";
        this.alertRoomSize = "Total room size can't be bigger than warehouse size.";
        this.alertEmptyFields = "Please fill in all empty fields.";
    }

    public String getAlertRoomSize() {
        return alertRoomSize;
    }

    public String getAlertTitle() {
        return alertTitle;
    }

    public String getAlertEmptyFields() {
        return alertEmptyFields;
    }
}
