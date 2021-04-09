package lt.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.psk.mybatis.model.Pilot;
import lt.psk.mybatis.dao.PilotMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PilotsMyBatis {

    @Inject
    private PilotMapper pilotMapper;

    @Getter @Setter
    private Pilot pilotToCreate = new Pilot();

    @Getter
    private List<Pilot> allPilots;

    @PostConstruct
    public void init(){
        loadAllPilots();
    }

    @Transactional
    public String createPilot(){
        pilotMapper.insert(pilotToCreate);
        return "pilots?faces-redirect=true";
    }

    public void loadAllPilots() {
        this.allPilots = pilotMapper.selectAll();
    }
}
