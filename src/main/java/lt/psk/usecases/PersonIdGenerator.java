package lt.psk.usecases;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class PersonIdGenerator implements Serializable, INumberGenerator {
    public Integer generatePersonId() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        return new Random().nextInt(9000) + 1000;
    }
}
