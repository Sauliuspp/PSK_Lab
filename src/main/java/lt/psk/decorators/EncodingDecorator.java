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
                String decodedNameToString = decodeString(name);
                String decodedAddressToString = decodeString(airportAddress);

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

    public Airport findOne(Integer id) {
        Airport decodedAirport = new Airport();
        try {
            Airport airport = airportsDAO.findOne(id);
            String name = airport.getName();
            String airportAddress = airport.getAirportAddress();
            List<Plane> planes = airport.getPlanes();

            try {
                String decodedNameToString = decodeString(name);
                String decodedAddressToString = decodeString(airportAddress);

                setFields(decodedAirport, id, decodedNameToString, decodedAddressToString, planes);
                return decodedAirport;

            } catch (IllegalArgumentException e) {
                setFields(decodedAirport, id, name, airportAddress, planes);
                return decodedAirport;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Airport update(Airport airp) {
        Airport decodedAirport = new Airport();

        try {
            int id = airp.getId();
            String name = airp.getName();
            String airportAddress = airp.getAirportAddress();
            List<Plane> planes = airp.getPlanes();

            try {
                String encodedName = Base64.
                        getEncoder().
                        encodeToString(name.getBytes(StandardCharsets.UTF_16));
                String encodedAirportAddress = Base64.
                        getEncoder().
                        encodeToString(airportAddress.getBytes(StandardCharsets.UTF_16));

                setFields(decodedAirport, id, encodedName, encodedAirportAddress, planes);
                return airportsDAO.update(decodedAirport);

            } catch (IllegalArgumentException e) {
                setFields(decodedAirport, id, name, airportAddress, planes);
                return decodedAirport;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    private String decodeString(String str) {
        byte[] decodedName = Base64.
                getDecoder().
                decode(str);
        return new String(decodedName, StandardCharsets.UTF_16);
    }
}
