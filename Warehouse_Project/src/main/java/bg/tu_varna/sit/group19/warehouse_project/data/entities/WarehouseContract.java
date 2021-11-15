package bg.tu_varna.sit.group19.warehouse_project.data.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Warehouse_Contracts", schema = "dbo", catalog = "JavaWarehouse")
public class WarehouseContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "FromDate", nullable = false)
    private Date fromDate;

    @Column(name = "ToDate", nullable = false)
    private Date toDate;

    @Column(name = "Price", nullable = false)
    private float price;

    @ManyToOne
    @JoinColumn(name = "WarehouseRoomID", nullable = false)
    private WarehouseRoom warehouseRoom;

    @ManyToOne
    @JoinColumn(name = "AgentID", nullable = false)
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "RenterID", nullable = false)
    private Renter renter;

    @ManyToOne
    @JoinColumn(name = "OwnerID", nullable = false)
    private Owner owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public WarehouseRoom getWarehouseRoom() {
        return warehouseRoom;
    }

    public void setWarehouseRoom(WarehouseRoom warehouseRoom) {
        this.warehouseRoom = warehouseRoom;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "WarehouseContract{" +
                "id=" + id +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", price=" + price +
                ", warehouseRoom=" + warehouseRoom +
                ", agent=" + agent +
                ", renter=" + renter +
                ", owner=" + owner +
                '}';
    }
}
