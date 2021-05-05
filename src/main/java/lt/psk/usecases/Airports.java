package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.entities.Airport;
import lt.psk.persistence.IAirportsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Airports {

    @Inject
    private IAirportsDAO airportsDAO;

    @Getter @Setter
    private Airport airportToCreate = new Airport();

    @Getter
    private List<Airport> allAirports;

    @PostConstruct
    public void init(){
        loadAllAirports();
    }

    @Transactional
    public String createAirport(){
        this.airportsDAO.persist(airportToCreate);
        return "airports?faces-redirect=true";
    }

    private void loadAllAirports(){
        this.allAirports = airportsDAO.loadAll();
    }
}
