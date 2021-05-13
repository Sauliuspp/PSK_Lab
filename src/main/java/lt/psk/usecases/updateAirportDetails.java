package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.entities.Airport;
import lt.psk.persistence.AirportsDAO;

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
public class updateAirportDetails implements Serializable {

    private Airport airport;

    @Inject
    private AirportsDAO airportsDAO;

    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer airportId = Integer.parseInt(requestParameters.get("airportId"));
        this.airport = airportsDAO.findOne(airportId);
    }

    @Transactional
    public String updateAirportName() {
        try{
            airportsDAO.update(this.airport);
        } catch (OptimisticLockException e) {
            return "/airportdetails.xhtml?faces-redirect=true&airportId=" + this.airport.getId() + "&error=optimistic-lock-exception";
        }
        return "airports.xhtml?faces-redirect=true";
    }
}
