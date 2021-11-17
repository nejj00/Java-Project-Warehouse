package bg.tuvarna.sit.example.data.enities;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "POSITIONS")
@Entity
public class Positions implements Serializable {
    private static final long serialValidationUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",nullable = false)
    private Long ID;

    @Column(name = "POSITION",nullable = false)
    private String Position;

    public Long getID() {
        return ID;
    }

    public String getPosition() {
        return Position;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setPosition(String position) {
        Position = position;
    }

    @Override
    public String toString() {
        return "Positions{" +
                "ID=" + ID +
                ", Position='" + Position + '\'' +
                '}';
    }
}
