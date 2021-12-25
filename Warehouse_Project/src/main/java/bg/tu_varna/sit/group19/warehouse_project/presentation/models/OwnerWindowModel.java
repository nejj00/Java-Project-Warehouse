package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

public class OwnerWindowModel {
    private final String alertTitle;
    private final String alertCongratulations;
    private final String alertInsert;
    private final String alertUpdate;
    private final String emptyFields;
    private final String alertRoomSize;
    private final String alertWarehouseSizeNull;
    private final String alertDeleteTitle;
    private final String alertDeleteMessage;

    public OwnerWindowModel(){
        this.alertTitle = "Warehouse message";
        this.alertCongratulations = "Congratulations";
        this.alertInsert = "Warehouse was inserted.";
        this.alertUpdate = "Warehouse was updated.";
        this.emptyFields = "Please fill in all empty fields.";
        this.alertRoomSize = "Total Room size can't be bigger than warehouse size.";
        this.alertWarehouseSizeNull = "Please input a warehouse size before\n" +
                " inserting or updating rooms.";
        this.alertDeleteTitle = "Delete";
        this.alertDeleteMessage = "Are you sure you want to delete this record?";
    }

    public String getAlertTitle() {
        return alertTitle;
    }

    public String getAlertCongratulations() {
        return alertCongratulations;
    }

    public String getAlertInsert() {
        return alertInsert;
    }

    public String getAlertUpdate() {
        return alertUpdate;
    }

    public String getEmptyFields() {
        return emptyFields;
    }

    public String getAlertRoomSize() {
        return alertRoomSize;
    }

    public String getAlertWarehouseSizeNull() {
        return alertWarehouseSizeNull;
    }

    public String getAlertDeleteTitle() {
        return alertDeleteTitle;
    }

    public String getAlertDeleteMessage() {
        return alertDeleteMessage;
    }
}
