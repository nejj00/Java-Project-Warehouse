package bg.tu_varna.sit.group19.warehouse_project.data.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Warehouse_Types", schema = "dbo", catalog = "JavaWarehouse")
public class WarehouseType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "WarehouseType", nullable = false)
    private String type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "type")
    private Set<Warehouse> warehouses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public String toString() {
        return "WarehouseType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
