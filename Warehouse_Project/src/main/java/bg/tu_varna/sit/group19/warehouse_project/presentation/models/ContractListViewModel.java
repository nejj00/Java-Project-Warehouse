package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Renter;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseRoom;

import java.util.Date;

public class ContractListViewModel {
    private long contractID;
    private Date fromDate;
    private Date toDate;
    private float price;
    private WarehouseRoom room;
    private Agent agent;
    private Renter renter;
    private Owner owner;

    public ContractListViewModel(long ContractID,Date FromDate,Date ToDate,float Price,WarehouseRoom Room, Agent Agent,Renter Renter,Owner Owner) {
        this.contractID=ContractID;
        this.fromDate=FromDate;
        this.toDate=ToDate;
        this.price=Price;
        this.room=Room;
        this.agent=Agent;
        this.renter=Renter;
        this.owner=Owner;
    }

    public String toString() {
        return String.format("%s | %s | %f | %s",fromDate.toString(),toDate.toString(),price,room.toString());
    }

    public void setContractID(long contractID) {
        this.contractID = contractID;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRoom(WarehouseRoom room) {
        this.room = room;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public long getContractID() {
        return contractID;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public float getPrice() {
        return price;
    }

    public WarehouseRoom getRoom() {
        return room;
    }

    public Agent getAgent() {
        return agent;
    }

    public Renter getRenter() {
        return renter;
    }

    public Owner getOwner() {
        return owner;
    }
}
