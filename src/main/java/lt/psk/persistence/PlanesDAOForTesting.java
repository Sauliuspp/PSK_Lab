package lt.psk.persistence;

import lt.psk.entities.Airport;
import lt.psk.entities.Plane;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Alternative
public class PlanesDAOForTesting implements IPlanesDAO {

    private boolean isFilled = false;
    private List<Plane> planes = new ArrayList<>();

    @Inject
    private AirportsDAOForTesting airportsDAOForTesting;

    public List<Plane> loadAll() {
        if(!isFilled) {
            fillList();
            isFilled = true;
        }
        return planes;
    }

    public void persist(Plane plane) {
        loadAll();
        List<Airport> airportList = airportsDAOForTesting.loadAll();
        Integer planesAirportId = plane.getAirport().getId();

        for (Airport airport : airportList) {
            if (planesAirportId.equals(airport.getId())) {
                List<Plane> airportPlaneList = airport.getPlanes();
                airportPlaneList.add(plane);
                airport.setPlanes(airportPlaneList);
            }
        }
        planes.add(plane);
    }

    public Plane findOne(Integer id){
        for (Plane plane : planes) {
            if(id.equals(plane.getId())) {
                return plane;
            }
        }
        return null;
    }

    private void fillList() {
        List<Plane> pl = createPlanes();
        planes.addAll(pl);
    }

    private List<Plane> createPlanes() {
        List<Plane> planeList = new ArrayList<>();

        Plane plane = new Plane();
        plane.setId(1);
        plane.setModel("Boeing 737");
        plane.setPlaneNumber("A161456");
        planeList.add(plane);

        plane = new Plane();
        plane.setId(2);
        plane.setModel("Boeing 747");
        plane.setPlaneNumber("B147258");
        planeList.add(plane);

        plane = new Plane();
        plane.setId(3);
        plane.setModel("Icarus");
        plane.setPlaneNumber("C986473");
        planeList.add(plane);

        return planeList;
    }
}
