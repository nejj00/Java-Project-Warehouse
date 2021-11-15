package bg.tu_varna.sit.group19.warehouse_project.data.entities;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Warehouse_Rooms", schema = "dbo", catalog = "JavaWarehouse")
public class WarehouseRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "Size", nullable = false)
    private float size;

    @Column(name = "Price", nullable = false)
    private float price;

    @ManyToOne
    @JoinColumn(name = "ClimateConditionsID", nullable = false)
    private ClimateCondition condition;

    @ManyToOne
    @JoinColumn(name = "WarehouseID", nullable = false)
    private Warehouse warehouse;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "warehouseRoom")
    private Set<WarehouseContract> contracts;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ClimateCondition getCondition() {
        return condition;
    }

    public void setCondition(ClimateCondition condition) {
        this.condition = condition;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Set<WarehouseContract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<WarehouseContract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return "WarehouseRoom{" +
                "id=" + id +
                ", size=" + size +
                ", price=" + price +
                ", condition=" + condition +
                ", warehouse=" + warehouse +
                '}';
    }
}
