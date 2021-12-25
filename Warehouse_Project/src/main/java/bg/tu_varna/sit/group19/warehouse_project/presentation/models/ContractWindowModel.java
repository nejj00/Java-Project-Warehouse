package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

public class ContractWindowModel {
    private final String alertTitle;
    private final String alertEmptyFields;
    private final String alertDoubleCheckHeader;
    private final String alertConfirmQuestion;

    public ContractWindowModel(){
        this.alertTitle = "Contract";
        this.alertEmptyFields = "Please fill the empty fields.";
        this.alertDoubleCheckHeader = "Double Check";
        this.alertConfirmQuestion = "Are you sure you want to make this contract?\n" +
                "Please double check you data before proceeding.";
    }

    public String getAlertTitle() {
        return alertTitle;
    }

    public String getAlertEmptyFields() {
        return alertEmptyFields;
    }

    public String getAlertDoubleCheckHeader() {
        return alertDoubleCheckHeader;
    }

    public String getAlertConfirmQuestion() {
        return alertConfirmQuestion;
    }
}
