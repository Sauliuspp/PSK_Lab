package lt.psk.decorators;

import lt.psk.entities.Airport;
import lt.psk.entities.Plane;
import lt.psk.persistence.IPlanesDAO;
import lt.psk.persistence.PlanesDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.List;

@Decorator
public class PlaneDecorator implements IPlanesDAO {

    @Inject
    @Delegate
    @Any
    private IPlanesDAO planesDAO;

    public List<Plane> loadAll() {
        return planesDAO.loadAll();
    }

    public void persist(Plane plane) {
        String planeNumber = plane.getPlaneNumber();
        String newPlaneNumber = generatePlaneNumber(planeNumber);
        plane.setPlaneNumber(newPlaneNumber);
        planesDAO.persist(plane);
    }

    public Plane findOne(Integer id) {
        return planesDAO.findOne(id);
    }

    private String generatePlaneNumber(String input) {
        StringBuilder newPlaneNumber = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            newPlaneNumber.append((char) (input.charAt(i) + 2));
        }
        return newPlaneNumber.toString();
    }
}
