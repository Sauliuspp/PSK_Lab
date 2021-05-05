package lt.psk.persistence;

import lt.psk.entities.Pilot;

import java.util.List;

public interface IPilotsDAO {
    List<Pilot> loadAll();

    void persist(Pilot pilot);

    Pilot findOne(Integer personId);

    List loadFilteredPilots(List<Pilot> pilots);
}
