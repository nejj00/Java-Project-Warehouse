package bg.tu_varna.sit.group19.warehouse_project.data.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Warehouse_Status", schema = "dbo", catalog = "JavaWarehouse")
public class WarehouseStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "WarehouseStatus", nullable = false)
    private String status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "status")
    private Set<Warehouse> warehouses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public String toString() {
        return "WarehouseStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseStatus that = (WarehouseStatus) o;
        return id == that.id && status.equals(that.status);
    }

}
