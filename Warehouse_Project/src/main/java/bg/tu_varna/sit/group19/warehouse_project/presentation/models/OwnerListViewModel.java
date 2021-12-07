package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

public class OwnerListViewModel {
    private String firstName;
    private String lasName;

    public OwnerListViewModel(String firstName, String lasName) {
        this.firstName = firstName;
        this.lasName = lasName;
    }


    @Override
    public String toString() {
        return String.format("%s %s", firstName, lasName);
    }
}
