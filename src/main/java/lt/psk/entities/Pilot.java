package lt.psk.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Pilot.findAll", query = "select p from Pilot as p")
})
@Table(name = "PILOT")
@Getter @Setter
public class Pilot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "NAME")
    private String name;

    @Column
    private String age;

    @Column(name = "PERSON_ID", nullable = false)
    private int personId;

    @ManyToMany
    @JoinTable(name = "PILOTS_PLANES")
    private List<Plane> planes = new ArrayList<>();

    public Pilot() { }
}
