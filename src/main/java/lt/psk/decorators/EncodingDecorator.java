package lt.psk.decorators;

import lt.psk.entities.Airport;
import lt.psk.entities.Plane;
import lt.psk.persistence.IAirportsDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Decorator
public abstract class EncodingDecorator implements IAirportsDAO {

    @Inject
    @Delegate
    @Any
    private IAirportsDAO airportsDAO;

    public List<Airport> loadAll() {
        List<Airport> airports = airportsDAO.loadAll();
        List<Airport> decodedAirports = new ArrayList<>();

        for (Airport airport : airports) {
            int id = airport.getId();
            String name = airport.getName();
            String airportAddress = airport.getAirportAddress();
            List<Plane> planes = airport.getPlanes();

            try {
                byte[] decodedName = Base64.
                        getDecoder().
                        decode(name);
                String decodedNameToString = new String(decodedName, StandardCharsets.UTF_16);

                byte[] decodedAddress = Base64.
                        getDecoder().
                        decode(airportAddress);
                String decodedAddressToString = new String(decodedAddress, StandardCharsets.UTF_16);

                Airport decodedAirport = new Airport();
                setFields(decodedAirport, id, decodedNameToString, decodedAddressToString, planes);
                decodedAirports.add(decodedAirport);

            } catch (IllegalArgumentException e) {
                Airport airp = new Airport();
                setFields(airp, id, name, airportAddress, planes);
                decodedAirports.add(airp);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return decodedAirports;
    }

    public void persist(Airport airport) {
        String name = airport.getName();
        String airportAddress = airport.getAirportAddress();

        String encodedName = Base64.
                getEncoder().
                encodeToString(name.getBytes(StandardCharsets.UTF_16));
        String encodedAirportAddress = Base64.
                getEncoder().
                encodeToString(airportAddress.getBytes(StandardCharsets.UTF_16));

        airport.setName(encodedName);
        airport.setAirportAddress(encodedAirportAddress);

        System.out.println("Encoded airport name: " + airport.getName());
        System.out.println("Encoded airport address: " + airport.getAirportAddress());
        airportsDAO.persist(airport);
    }

    private void setFields(Airport airport, int id, String name, String address, List<Plane> planes) {
        airport.setId(id);
        airport.setName(name);
        airport.setAirportAddress(address);
        airport.setPlanes(planes);
    }
}
