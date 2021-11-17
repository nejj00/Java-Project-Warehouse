package bg.tuvarna.sit.example.data.enities;

import javax.persistence.*;

@Table(name = "EMPLOYEES")
@Entity
public class Employees {
    private static final long serialValidationUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false)
    private Long ID;

    @Column(name = "NAME",nullable = false)
    private String Name;

    @Column(name = "ADDRESS",nullable = false)
    private String Address;

    @ManyToOne
    @JoinColumn(name = "POSITION_ID",nullable = false)
    private Positions Position;

    public Long getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public Positions getPosition() {
        return Position;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setPosition(Positions position) {
        Position = position;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Position=" + Position +
                '}';
    }
}
