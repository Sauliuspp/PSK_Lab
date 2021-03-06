package lt.psk.persistence;

import lt.psk.entities.Airport;
import lt.psk.entities.Pilot;
import lt.psk.qualifiers.Standard;
import lt.psk.usecases.ValidNameChecker;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PilotsDAO implements IPilotsDAO {

    @Inject @Standard
    private ValidNameChecker validNameChecker;

    @Inject
    private EntityManager em;

    public List<Pilot> loadAll() {
        return em.createNamedQuery("Pilot.findAll", Pilot.class).getResultList();
    }

    public void persist(Pilot pilot) {
        if (validNameChecker.isValidName(pilot.getName())) {
            this.em.persist(pilot);
        }
    }

    public Pilot findOne(Integer personId) {
        return em.find(Pilot.class, personId);
    }

    public List loadFilteredPilots(List<Pilot> pilots) {
        return em.createNamedQuery("Pilot.loadFilteredPilots").setParameter("pilots", pilots).getResultList();
    }

    public Pilot update(Pilot pilot){
        return em.merge(pilot);
    }
}
