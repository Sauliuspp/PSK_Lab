package lt.psk.persistence;

import lt.psk.entities.Pilot;
import lt.psk.qualifiers.Standard;
import lt.psk.usecases.ValidNameChecker;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Alternative
public class PilotsDAOForTesting implements IPilotsDAO {

    @Inject @Standard
    private ValidNameChecker validNameChecker;

    private boolean isFilled = false;
    private List<Pilot> pilots = new ArrayList<>();

    public List<Pilot> loadAll() {
        if(!isFilled) {
            fillList();
            isFilled = true;
        }
        return pilots;
    }

    public void persist(Pilot pilot) {
        if (validNameChecker.isValidName(pilot.getName())) {
            pilots.add(pilot);
        }
    }

    public Pilot findOne(Integer personId) {
        for (Pilot pilot : pilots) {
            if(personId.equals(pilot.getId())) {
                return pilot;
            }
        }
        return null;
    }

    public List loadFilteredPilots(List<Pilot> pilotList) {
        loadAll();
        List<Pilot> filteredPilots = new ArrayList<>(pilots);

        for (Pilot pilot : filteredPilots) {
            for (Pilot removablePilot : pilotList) {
                Integer pilotId = pilot.getId();
                if (pilotId.equals(removablePilot.getId())) {
                    filteredPilots.remove(pilot);
                }
            }
        }
        return filteredPilots;
    }

    private void fillList() {
        List<Pilot> pil = createPilots();
        pilots.addAll(pil);
    }

    private List<Pilot> createPilots() {
        List<Pilot> pilotList = new ArrayList<>();

        Pilot pilot = new Pilot();
        pilot.setId(1);
        pilot.setName("Jonas");
        pilot.setAge("35");
        pilot.setPersonId(1111);
        pilotList.add(pilot);

        pilot = new Pilot();
        pilot.setId(2);
        pilot.setName("Petras");
        pilot.setAge("40");
        pilot.setPersonId(2222);
        pilotList.add(pilot);

        pilot = new Pilot();
        pilot.setId(3);
        pilot.setName("Karolis");
        pilot.setAge("42");
        pilot.setPersonId(3333);
        pilotList.add(pilot);

        return pilotList;
    }
}
