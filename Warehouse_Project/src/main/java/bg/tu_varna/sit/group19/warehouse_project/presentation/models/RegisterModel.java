package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

public class RegisterModel {
    private final String wrongPassword;
    private final String emptyField;
    private final String selectAccountType;
    private final String usernameExists;

    public RegisterModel() {
        this.wrongPassword = "Incorrect password. Please try again.";
        this.emptyField = "Please don't leave empty fields.";
        this.selectAccountType = "Please select account type, by choosing between the two\n options below.";
        this.usernameExists = "This username already exists.";
    }

    public String getWrongPasswordMessage() {
        return wrongPassword;
    }

    public String getEmptyFieldMessage() {
        return emptyField;
    }

    public String getSelectAccountTypeMessage() {
        return selectAccountType;
    }

    public String getUsernameExists() {
        return usernameExists;
    }
}
