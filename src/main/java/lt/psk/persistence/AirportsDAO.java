package lt.psk.persistence;

import lt.psk.entities.Airport;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class AirportsDAO implements IAirportsDAO {

    @Inject
    private EntityManager em;

    public List<Airport> loadAll() {
        return em.createNamedQuery("Airport.findAll", Airport.class).getResultList();
    }

    public void persist(Airport airport){
        this.em.persist(airport);
    }

    public Airport findOne(Integer id) {
        return em.find(Airport.class, id);
    }

    public Airport update(Airport airport){
        return em.merge(airport);
    }
}
