package bg.tu_varna.sit.group19.warehouse_project.data.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Warehouses", schema = "dbo", catalog = "JavaWarehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "WarehouseAddress", nullable = false)
    private String WarehouseAddress;

    @Column(name = "Size", nullable = false)
    private float size;

    @ManyToOne
    @JoinColumn(name = "OwnerID", nullable = false)
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "TypeID", nullable = false)
    private WarehouseType type;

    @ManyToOne
    @JoinColumn(name = "StatusID", nullable = false)
    private WarehouseStatus status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "warehouse")
    private Set<WarehouseRoom> warehouseRooms;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getWarehouseAddress() {
        return WarehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        WarehouseAddress = warehouseAddress;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public WarehouseType getType() {
        return type;
    }

    public void setType(WarehouseType type) {
        this.type = type;
    }

    public WarehouseStatus getStatus() {
        return status;
    }

    public void setStatus(WarehouseStatus status) {
        this.status = status;
    }

    public Set<WarehouseRoom> getWarehouseRooms() {
        return warehouseRooms;
    }

    public void setWarehouseRooms(Set<WarehouseRoom> warehouseRooms) {
        this.warehouseRooms = warehouseRooms;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", size=" + size +
                ", owner=" + owner +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
