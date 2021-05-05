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
        @NamedQuery(name = "Pilot.findAll", query = "select p from Pilot as p"),
        @NamedQuery(name = "Pilot.loadFilteredPilots", query = "select p from Pilot as p where p not in :pilots")
})
@JsonIgnoreProperties({"planes"})
@Table(name = "PILOT")
@Getter @Setter
public class Pilot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "NAME")
    private String name;

    @Column
    private String age;

    @Column(name = "PERSON_ID", nullable = false)
    private int personId;

    @ManyToMany(mappedBy = "pilots")
    private List<Plane> planes = new ArrayList<>();

    public Pilot() { }
}
