package bg.tu_varna.sit.group19.warehouse_project.data.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Owners", schema = "dbo", catalog = "JavaWarehouse")
public class Owner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @OneToOne(mappedBy = "owner")
    private OwnerAccount ownerAccount;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private Set<Warehouse> warehouses;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private Set<WarehouseContract> contracts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public OwnerAccount getOwnerAccount() {
        return ownerAccount;
    }

    public void setOwnerAccount(OwnerAccount ownerAccount) {
        this.ownerAccount = ownerAccount;
    }

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Set<WarehouseContract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<WarehouseContract> contracts) {
        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Owner owner = (Owner) o;
        return id == owner.id && firstName.equals(owner.firstName) && lastName.equals(owner.lastName) && ownerAccount.equals(owner.ownerAccount);
    }


}
