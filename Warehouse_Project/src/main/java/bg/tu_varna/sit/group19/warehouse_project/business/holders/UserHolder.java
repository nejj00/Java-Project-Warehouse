package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Admin;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;

public class UserHolder {
    private Owner owner = new Owner();
    private Agent agent = new Agent();
    private Admin admin = new Admin();

    private Enums.AccountType accountType;

    private final static UserHolder INSTANCE = new UserHolder();
    public UserHolder(){}
    public static UserHolder getInstance(){
        return INSTANCE;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Enums.AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(Enums.AccountType accountType) {
        this.accountType = accountType;
    }
}
