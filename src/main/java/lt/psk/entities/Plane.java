package lt.psk.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Plane.findAll", query = "select p from Plane as p")
})
@JsonIgnoreProperties({"pilots", "airport"})
@Table(name = "PLANE")
@Getter @Setter
public class Plane implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name="PLANE_NUMBER", nullable = false)
    private String planeNumber;

    @Column(name="MODEL")
    private String model;

    @ManyToOne
    private Airport airport;

    @ManyToMany
    @JoinTable(name = "PLANES_PILOTS")
    private List<Pilot> pilots = new ArrayList<>();

    public Plane() { }
}
