package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

public class BaseWindowModel {
    private final String alertTitle;
    private final String alertEmptyFields;
    private final String alertDeleteTitle;
    private final String alertDeleteMessage;

    public BaseWindowModel(){
        this.alertTitle = "Warning";
        this.alertEmptyFields = "Please fill in all empty fields.";
        this.alertDeleteTitle = "Delete";
        this.alertDeleteMessage = "Are you sure you want to delete this record?";
    }

    public String getAlertTitle() {
        return alertTitle;
    }

    public String getAlertEmptyFields() {
        return alertEmptyFields;
    }

    public String getAlertDeleteTitle() {
        return alertDeleteTitle;
    }

    public String getAlertDeleteMessage() {
        return alertDeleteMessage;
    }
}
