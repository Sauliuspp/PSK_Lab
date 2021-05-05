package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.entities.Pilot;
import lt.psk.entities.Plane;
import lt.psk.persistence.IPilotsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class PlanesForPilot {

    @Inject
    private IPilotsDAO pilotsDAO;

    @Getter @Setter
    private Pilot pilot;

    @Getter @Setter
    private Plane planeToCreate = new Plane();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer personId = Integer.parseInt(requestParameters.get("personId"));
        this.pilot = pilotsDAO.findOne(personId);
    }
}
