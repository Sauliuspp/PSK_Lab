package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.mybatis.model.Airport;
import lt.psk.mybatis.model.Plane;
import lt.psk.mybatis.dao.AirportMapper;
import lt.psk.mybatis.dao.PlaneMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class PlanesForAirportMyBatis {

    @Inject
    private AirportMapper airportMapper;

    @Inject
    private PlaneMapper planeMapper;

    @Getter @Setter
    private Airport airport;

    @Getter @Setter
    private Plane planeToCreate = new Plane();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer airportId = Integer.parseInt(requestParameters.get("airportId"));
        this.airport = airportMapper.selectByPrimaryKey(airportId);
    }

    @Transactional
    public String createPlane() {
        planeToCreate.setAirport(this.airport);
        planeToCreate.setAirportId(this.airport.getId());
        planeMapper.insert(planeToCreate);
        return "/MyBatis/airportdetails?faces-redirect=true&airportId=" + this.airport.getId();
    }
}
