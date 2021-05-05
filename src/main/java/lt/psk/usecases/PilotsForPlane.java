package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.entities.Pilot;
import lt.psk.entities.Plane;
import lt.psk.persistence.IPilotsDAO;
import lt.psk.persistence.IPlanesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class PilotsForPlane {

    @Inject
    private IPlanesDAO planesDAO;

    @Inject
    private IPilotsDAO pilotsDAO;

    @Getter @Setter
    private Integer chosenPilotId;

    @Getter @Setter
    private Plane plane;

    @Getter @Setter
    private List<Pilot> pilots;

    @Getter @Setter
    private Pilot pilotToCreate = new Pilot();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer planeId = Integer.parseInt(requestParameters.get("planeId"));
        this.plane = planesDAO.findOne(planeId);
    }

    public List<SelectItem> getPilots() {
        List<SelectItem> pilotsForSelection = new ArrayList<>();
//        List<Pilot> pilotList = pilotsDAO.loadAll();
        List<Pilot> pilotList = pilotsDAO.loadFilteredPilots(plane.getPilots());

        for(Pilot pilot : pilotList) {
            pilotsForSelection.add(new SelectItem(pilot.getId(), pilot.getName()));
        }
        return pilotsForSelection;
    }

    @Transactional
    public String assignPilot() {
        Pilot pilotToAdd = pilotsDAO.findOne(chosenPilotId);
        plane.getPilots().add(pilotToAdd);
        planesDAO.persist(plane);
        return "planedetails?faces-redirect=true&planeId=" + this.plane.getId();
    }
}
