package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

public class LoginModel {
    private final String wrongMessage;

    public LoginModel() {
        this.wrongMessage = "Incorrect username or password. Please try again.";
    }

    public String getWrongMessage() {
        return wrongMessage;
    }
}
