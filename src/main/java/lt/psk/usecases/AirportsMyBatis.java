package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.mybatis.model.Airport;
import lt.psk.mybatis.dao.AirportMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class AirportsMyBatis {
    @Inject
    private AirportMapper airportMapper;

    @Getter
    private List<Airport> allAirports;

    @Getter @Setter
    private Airport airportToCreate = new Airport();

    @PostConstruct
    public void init() {
        this.loadAllAirports();
    }

    private void loadAllAirports() {
        this.allAirports = airportMapper.selectAll();
    }

    @Transactional
    public String createAirport() {
        airportMapper.insert(airportToCreate);
        return "/MyBatis/airports?faces-redirect=true";
    }
}
