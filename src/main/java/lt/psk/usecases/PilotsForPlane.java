package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.entities.Pilot;
import lt.psk.entities.Plane;
import lt.psk.interceptors.LoggedInvocation;
import lt.psk.persistence.PilotsDAO;
import lt.psk.persistence.PlanesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class PilotsForPlane {

    @Inject
    private PlanesDAO planesDAO;

    @Inject
    private PilotsDAO pilotsDAO;

    @Getter @Setter
    private Plane plane;

    @Getter @Setter
    private Pilot pilotToCreate = new Pilot();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer planeId = Integer.parseInt(requestParameters.get("planeId"));
        this.plane = planesDAO.findOne(planeId);
    }

    @Transactional
    @LoggedInvocation
    public String createPilot() {
        List<Plane> planes = new ArrayList<>();
        planes.add(this.plane);
        pilotToCreate.setPlanes(planes);
        pilotsDAO.persist(pilotToCreate);
        return "planedetails?faces-redirect=true&planeId=" + this.plane.getId();
    }
}
