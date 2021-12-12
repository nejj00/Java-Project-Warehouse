package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;

public class UserListViewModel<UA> {
    private final String firstName;
    private final String lasName;
    private final long userID;
    private final UA userAccount;
    private final String username;

    public UserListViewModel(String firstName, String lasName, long ID, UA userAccount, String username) {
        this.firstName = firstName;
        this.lasName = lasName;
        this.userID = ID;
        this.userAccount = userAccount;
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLasName() {
        return lasName;
    }

    public long getUserID() {
        return userID;
    }

    public UA getUserAccount() {
        return userAccount;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lasName);
    }
}
