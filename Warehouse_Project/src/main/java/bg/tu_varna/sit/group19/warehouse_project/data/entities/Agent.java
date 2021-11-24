package bg.tu_varna.sit.group19.warehouse_project.data.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Agents", schema = "dbo", catalog = "JavaWarehouse")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "Raiting", nullable = false)
    private float rating;

    @OneToOne(mappedBy = "agent")
    private AgentAccount agentAccount;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "agent")
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

    public AgentAccount getAgentAccount() {
        return agentAccount;
    }

    public void setAgentAccount(AgentAccount agentAccount) {
        this.agentAccount = agentAccount;
    }

    public Set<WarehouseContract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<WarehouseContract> contracts) {
        this.contracts = contracts;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
