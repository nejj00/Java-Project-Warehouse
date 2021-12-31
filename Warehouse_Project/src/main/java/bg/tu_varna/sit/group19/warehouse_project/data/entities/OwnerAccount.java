package bg.tu_varna.sit.group19.warehouse_project.data.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Owner_Accounts", schema = "dbo", catalog = "JavaWarehouse")
public class OwnerAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "OwnerUsername", nullable = false)
    private String username;

    @Column(name = "OwnerPassword", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OwnerID", referencedColumnName = "ID")
    private Owner owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "OwnerAccount{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerAccount that = (OwnerAccount) o;
        return id == that.id && username.equals(that.username) && password.equals(that.password);
    }

}
