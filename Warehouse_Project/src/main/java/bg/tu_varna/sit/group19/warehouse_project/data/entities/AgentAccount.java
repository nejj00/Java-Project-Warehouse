package bg.tu_varna.sit.group19.warehouse_project.data.entities;


import javax.persistence.*;

@Entity
@Table(name = "Agent_Accounts", schema = "dbo", catalog = "JavaWarehouse")
public class AgentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Column(name = "AgentUsername", nullable = false)
    private String username;

    @Column(name = "AgentPassword", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AgentID", referencedColumnName = "ID")
    private Agent agent;

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

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "AgentAccount{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", agent=" + agent +
                '}';
    }
}
