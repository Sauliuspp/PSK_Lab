package lt.psk.persistence;

import lt.psk.entities.Plane;

import java.util.List;

public interface IPlanesDAO {
    List<Plane> loadAll();

    void persist(Plane plane);

    Plane findOne(Integer id);
}
