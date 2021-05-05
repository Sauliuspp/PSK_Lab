package lt.psk.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lt.psk.entities.Airport;
import lt.psk.entities.Plane;
import lt.psk.persistence.IAirportsDAO;
import lt.psk.persistence.IPlanesDAO;

@Model
public class PlanesForAirport implements Serializable {

    @Inject
    private IAirportsDAO airportsDAO;

    @Inject
    private IPlanesDAO planesDAO;

    @Getter @Setter
    private Airport airport;

    @Getter @Setter
    private Plane planeToCreate = new Plane();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer airportId = Integer.parseInt(requestParameters.get("airportId"));
        this.airport = airportsDAO.findOne(airportId);
    }

    @Transactional
    public String createPlane() {
        planeToCreate.setAirport(this.airport);
        planesDAO.persist(planeToCreate);
        return "airportdetails?faces-redirect=true&airportId=" + this.airport.getId();
    }
}
