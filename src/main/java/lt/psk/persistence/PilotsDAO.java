package lt.psk.persistence;

import lt.psk.entities.Pilot;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PilotsDAO {

    @Inject
    private EntityManager em;

    public List<Pilot> loadAll() {
        return em.createNamedQuery("Pilot.findAll", Pilot.class).getResultList();
    }

    public void persist(Pilot pilot) {this.em.persist(pilot);}

    public Pilot findOne(Integer personId) {
        return em.find(Pilot.class, personId);
    }

    public List loadFilteredPilots(List<Pilot> pilots) {
        return em.createNamedQuery("Pilot.loadFilteredPilots").setParameter("pilots", pilots).getResultList();
    }
}
