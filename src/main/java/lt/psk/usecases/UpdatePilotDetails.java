package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.entities.Pilot;
import lt.psk.persistence.PilotsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdatePilotDetails implements Serializable {

    private Pilot pilot;

    @Inject
    private PilotsDAO pilotsDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer personId = Integer.parseInt(requestParameters.get("personId"));
        this.pilot = pilotsDAO.findOne(personId);
    }

    @Transactional
    public String updatePersonId() {
        try{
            pilotsDAO.update(this.pilot);
        } catch (OptimisticLockException e) {
            return "/pilotdetails?faces-redirect=true&personId=" + this.pilot.getId();
        }
        return "pilots?faces-redirect=true";
    }
}
