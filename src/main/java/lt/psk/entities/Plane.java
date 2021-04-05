package lt.psk.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Plane.findAll", query = "select p from Plane as p")
})
@Table(name = "PLANE")
@Getter @Setter
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name="PLANE_NUMBER", nullable = false)
    private String planeNumber;

    @Column(name="MODEL")
    private String model;

    @ManyToOne
    private Airport airport;

    @ManyToMany(mappedBy = "planes")
    private List<Pilot> pilots = new ArrayList<>();

    public Plane() { }
}
