package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

import org.hibernate.service.ServiceRegistry;

public class SettingsModel {
    private final String labelNewPassword;
    private final String labelPassword;
    private final String alertTitle;
    private final String alertHeader;
    private final String alertUsername;
    private final String alertProfileUpdated;

    public SettingsModel(){
        this.labelNewPassword = "New password fields do not match";
        this.labelPassword = "Password is incorrect";
        this.alertTitle = "Settings";
        this.alertHeader = "Update";
        this.alertUsername = "This username already exists.";
        this.alertProfileUpdated = "Your profile has been successfully updated.";
    }

    public String getLabelNewPassword() {
        return labelNewPassword;
    }

    public String getLabelPassword() {
        return labelPassword;
    }

    public String getAlertTitle() {
        return alertTitle;
    }

    public String getAlertUsername() {
        return alertUsername;
    }

    public String getAlertHeader() {
        return alertHeader;
    }

    public String getAlertProfileUpdated() {
        return alertProfileUpdated;
    }
}
