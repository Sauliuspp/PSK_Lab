package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.entities.Pilot;
import lt.psk.persistence.PilotsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Pilots {

    @Inject
    private PilotsDAO pilotsDAO;

    @Getter @Setter
    private Pilot pilotToCreate = new Pilot();

    @Getter
    private List<Pilot> allPilots;

    @PostConstruct
    public void init(){
        loadAllPilots();
    }

    public void loadAllPilots() {
        this.allPilots = pilotsDAO.loadAll();
    }

    @Transactional
    public String createPilot() {
        pilotsDAO.persist(pilotToCreate);
        return "pilots?faces-redirect=true";
    }
}
