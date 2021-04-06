package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.mybatis.dao.PlaneMapper;
import lt.psk.mybatis.model.Plane;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PlanesMyBatis {

    @Inject
    private PlaneMapper planeMapper;

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
        planeMapper.insert(planeToCreate);
        return "planes?faces-redirect=true";
    }

    private void loadAllPlanes(){
        this.allPlanes = planeMapper.selectAll();
    }

}
