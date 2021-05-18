package lt.psk.decorators;

import lt.psk.entities.Plane;
import lt.psk.persistence.IPlanesDAO;
import lt.psk.usecases.INumberGenerator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.Random;

@Decorator
public abstract class BigNumberDecorator implements INumberGenerator {

    @Inject
    @Delegate
    @Any
    private INumberGenerator numberGenerator;

    public Integer generatePersonId() {
            Integer result = numberGenerator.generatePersonId();
            int valueToAdd = new Random().nextInt(89990001) + 10000000;
            return result + valueToAdd;
    }
}
