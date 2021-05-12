package lt.psk.persistence;

import lt.psk.entities.Plane;
import lt.psk.interceptors.JSONInterceptor;
import lt.psk.interceptors.LoggingInterceptor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PlanesDAO implements IPlanesDAO {

    @Inject
    private EntityManager em;

    @LoggingInterceptor
    @JSONInterceptor
    public void persist(Plane plane){
        this.em.persist(plane);
    }

    public List<Plane> loadAll() {
        return em.createNamedQuery("Plane.findAll", Plane.class).getResultList();
    }

    public Plane findOne(Integer id){
        return em.find(Plane.class, id);
    }
}