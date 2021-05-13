package lt.psk.persistence;

import lt.psk.entities.Airport;

import java.util.List;

public interface IAirportsDAO {
    List<Airport> loadAll();

    void persist(Airport airport);

    Airport findOne(Integer id);

    Airport update(Airport player);
}
