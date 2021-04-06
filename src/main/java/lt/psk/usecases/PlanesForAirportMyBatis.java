//package lt.psk.usecases;
//
//import lombok.Getter;
//import lombok.Setter;
//import lt.psk.mybatis.dao.PlaneMapper;
//import lt.psk.mybatis.model.Plane;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.inject.Model;
//import javax.inject.Inject;
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Model
//public class PlanesForAirportMyBatis {
//
//    @Inject
//    private PlaneMapper planeMapper;
//
//    @Getter
//    private List<Plane> allPlanes;
//
//    @Getter @Setter
//    private Plane planeToCreate = new Plane();
//
//    @PostConstruct
//    public void init() {
//        this.loadAllPlanes();
//    }
//
//    private void loadAllPlanes() {
//        this.allPlanes = planeMapper.selectAll();
//    }
//
//    @Transactional
//    public String createPlane() {
//        planeMapper.insert(planeToCreate);
//        return "/MyBatis/airports?faces-redirect=true";
//    }
//}
