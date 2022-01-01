package bg.tu_varna.sit.group19.warehouse_project.business.holders;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;

public class OwnerHolder {
    private Owner owner = new Owner();

    private final static OwnerHolder INSTANCE = new OwnerHolder();
    public OwnerHolder(){}
    public static OwnerHolder getInstance(){
        return INSTANCE;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
