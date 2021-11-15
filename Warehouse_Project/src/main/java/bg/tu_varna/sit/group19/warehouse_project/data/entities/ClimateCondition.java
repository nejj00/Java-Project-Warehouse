package bg.tu_varna.sit.group19.warehouse_project.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Climate_Conditions", schema = "dbo", catalog = "JavaWarehouse")
public class ClimateCondition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "Conditions", nullable = false)
    private String conditions;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "condition")
    private Set<WarehouseRoom> warehouseRooms;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public Set<WarehouseRoom> getWarehouseRooms() {
        return warehouseRooms;
    }

    public void setWarehouseRooms(Set<WarehouseRoom> warehouseRooms) {
        this.warehouseRooms = warehouseRooms;
    }

    @Override
    public String toString() {
        return "ClimateCondition{" +
                "id=" + id +
                ", conditions='" + conditions + '\'' +
                '}';
    }
}
