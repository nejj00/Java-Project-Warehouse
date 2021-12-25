package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

public class AdminWindowModel {
    private final String alertDeleteTitle;
    private final String alertDeleteMessage;

    public AdminWindowModel(){
        this.alertDeleteTitle = "Delete";
        this.alertDeleteMessage = "Are you sure you want to delete this record?";
    }

    public String getAlertDeleteTitle() {
        return alertDeleteTitle;
    }

    public String getAlertDeleteMessage() {
        return alertDeleteMessage;
    }
}
