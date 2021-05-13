package lt.psk.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Entity
@NamedQueries({
        @NamedQuery(name = "Airport.findAll", query = "select a from Airport as a order by a.Id asc")
})
@JsonIgnoreProperties({"planes"})
@Table(name = "AIRPORT")
@Getter @Setter
public class Airport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name="NAME")
    private String name;

    @Column(name="AIRPORT_ADDRESS")
    private String airportAddress;

    @OneToMany(mappedBy = "airport")
    private List<Plane> planes = new ArrayList<>();

    @Version
    private Integer version;

    public Airport() { }
}
