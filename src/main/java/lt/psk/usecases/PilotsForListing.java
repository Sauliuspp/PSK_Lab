package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.entities.Pilot;
import lt.psk.persistence.PilotsDAO;
import org.apache.ibatis.annotations.Select;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model
public class PilotsForListing {

    @Inject
    private PilotsDAO pilotsDAO;

    @Getter @Setter
    private Pilot pilot;

    @Getter @Setter
    private List<SelectItem> pilots;

    @PostConstruct
    private void init() {
        this.pilots = loadAllPilots();
    }

    private List<SelectItem> loadAllPilots() {
        List<SelectItem> pilotsForSelection = new ArrayList<>();
        List<Pilot> pilotList = pilotsDAO.loadAll();
        for(Pilot pilot : pilotList) {
            pilotsForSelection.add(new SelectItem(pilot.getId(), pilot.getName()));
        }
        return pilotsForSelection;
    }
}
