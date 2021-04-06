//package lt.psk.usecases;
//
//import lombok.Getter;
//import lombok.Setter;
//import lt.psk.mybatis.model.Plane;
//import lt.psk.mybatis.model.Pilot;
//import lt.psk.mybatis.dao.PilotMapper;
//import lt.psk.mybatis.dao.PlaneMapper;
//
//import javax.annotation.PostConstruct;
//import javax.enterprise.inject.Model;
//import javax.faces.context.FacesContext;
//import javax.faces.model.SelectItem;
//import javax.inject.Inject;
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@Model
//public class PilotsForPlaneMyBatis {
//
//    @Inject
//    private PlaneMapper planeMapper;
//
//    @Inject
//    private PilotMapper pilotMapper;
//
//    @Getter @Setter
//    private Integer chosenPilotId;
//
//    @Getter @Setter
//    private Plane plane;
//
//    @Getter @Setter
//    private List<Pilot> pilots;
//
//    @Getter @Setter
//    private Pilot pilotToCreate = new Pilot();
//
//    @PostConstruct
//    public void init() {
//        Map<String, String> requestParameters =
//                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        Integer planeId = Integer.parseInt(requestParameters.get("planeId"));
//        this.plane = planeMapper.selectByPrimaryKey(planeId);
//    }
//
//    public List<SelectItem> getFreePilots() {
//        List<SelectItem> pilotsForSelection = new ArrayList<>();
//        List<Pilot> pilotList = pilotMapper.selectAll();
//        for(Pilot pilot : pilotList) {
//            pilotsForSelection.add(new SelectItem(pilot.getId(), pilot.getName()));
//        }
//        return pilotsForSelection;
//    }
//
//    @Transactional
//    public String assignPilot() {
//        Pilot pilotToAdd = pilotMapper.selectByPrimaryKey(chosenPilotId);
//        plane.getPilots().add(pilotToAdd);
//        planeMapper.insert(plane);
//        return "planedetails?faces-redirect=true&planeId=" + this.plane.getId();
//    }
//}
