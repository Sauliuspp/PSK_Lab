package lt.psk.persistence;

import lt.psk.entities.Airport;
import lt.psk.entities.Plane;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PlanesDAO {

    @Inject
    private EntityManager em;

    public void persist(Plane plane){
        this.em.persist(plane);
    }

    public List<Plane> loadAll() {
        return em.createNamedQuery("Plane.findAll", Plane.class).getResultList();
    }

    public Plane findOne(Integer id){
        return em.find(Plane.class, id);
    }

    public void update(Plane plane) {
        em.merge(plane);
    }
}