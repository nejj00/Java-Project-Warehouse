package bg.tu_varna.sit.group19.warehouse_project.data.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Climate_Conditions", schema = "dbo", catalog = "JavaWarehouse")
public class ClimateCondition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "Temperature", nullable = false)
    private int temperature;

    @Column(name = "Humidity", nullable = false)
    private int humidity;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "condition")
    private Set<WarehouseRoom> warehouseRooms;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Set<WarehouseRoom> getWarehouseRooms() {
        return warehouseRooms;
    }

    public void setWarehouseRooms(Set<WarehouseRoom> warehouseRooms) {
        this.warehouseRooms = warehouseRooms;
    }

    @Override
    public String toString() {
        return "ClimateCondition:" + id +
                " temp =" + temperature + " humidity = " + humidity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClimateCondition that = (ClimateCondition) o;
        return id == that.id;
    }
}
