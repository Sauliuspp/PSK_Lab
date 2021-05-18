package lt.psk.usecases;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GeneratePersonId implements Serializable {

    @Inject
    private PersonIdGenerator personIdGenerator;

    private CompletableFuture<Integer> generatePersonIdTask = null;

    public String generateNewPersonId() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int personId = Integer.parseInt(requestParameters.get("personId"));

        generatePersonIdTask = CompletableFuture.supplyAsync(() -> personIdGenerator.generatePersonId());

        return "pilotdetails?personId=" + personId + "&faces-redirect=true";
    }

    public boolean isPersonIdGeneratorRunning() {
        return generatePersonIdTask != null && !generatePersonIdTask.isDone();
    }

    public String getPersonIdGeneratorStatus() throws ExecutionException, InterruptedException {
        if (generatePersonIdTask == null) {
            return null;
        } else if (isPersonIdGeneratorRunning()) {
            return "Piloto ID generuojamas.";
        }
        return "Sugeneruotas piloto ID: " + generatePersonIdTask.get();
    }
}
