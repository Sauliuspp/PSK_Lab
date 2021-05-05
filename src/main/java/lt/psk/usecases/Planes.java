package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.entities.Plane;
import lt.psk.persistence.IPlanesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Planes {

    @Inject
    private IPlanesDAO planesDAO;

    @Getter @Setter
    private Plane planeToCreate = new Plane();

    @Getter
    private List<Plane> allPlanes;

    @PostConstruct
    public void init(){
        loadAllPlanes();
    }

    @Transactional
    public String createPlane(){
        this.planesDAO.persist(planeToCreate);
        return "planes?faces-redirect=true";
    }

    private void loadAllPlanes(){
        this.allPlanes = planesDAO.loadAll();
    }
}
