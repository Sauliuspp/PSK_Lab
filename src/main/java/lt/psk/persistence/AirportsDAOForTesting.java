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
public class AirportsDAOForTesting implements IAirportsDAO {

    private boolean isFilled = false;
    private List<Airport> airports = new ArrayList<>();

    @Inject
    private PlanesDAOForTesting planesDAOForTesting;

    public List<Airport> loadAll() {
        if(!isFilled) {
            fillList();
            isFilled = true;
        }
        return airports;
    }

    public void persist(Airport airport){
        airports.add(airport);
    }

    public Airport findOne(Integer id) {
        for (Airport airport : airports) {
            if(id.equals(airport.getId())) {
                return airport;
            }
        }
        return null;
    }

    private void fillList() {
        List<Airport> ap = createAirports();
        airports.addAll(ap);
    }

    private List<Airport> createAirports() {
        List<Airport> airportList = new ArrayList<>();
        List<Plane> planeList = planesDAOForTesting.loadAll();

        Airport airport = new Airport();
        airport.setId(1);
        airport.setName("Oro uostas 1");
        airport.setAirportAddress("Vilniaus g.");
        List<Plane> airportsPlaneList = airport.getPlanes();
        airportsPlaneList.add(planeList.get(0));
        airportsPlaneList.add(planeList.get(1));
        airport.setPlanes(airportsPlaneList);
        airportList.add(airport);

        airport = new Airport();
        airport.setId(2);
        airport.setName("Oro uostas 2");
        airport.setAirportAddress("Kauno g.");
        airportsPlaneList = airport.getPlanes();
        airportsPlaneList.add(planeList.get(2));
        airport.setPlanes(airportsPlaneList);
        airportList.add(airport);

        airport = new Airport();
        airport.setId(3);
        airport.setName("Oro uostas 3");
        airport.setAirportAddress("Klaipedos g.");
        airportList.add(airport);

        return airportList;
    }
}
