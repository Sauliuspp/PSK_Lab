package lt.psk.usecases;

import javax.enterprise.inject.Specializes;

@Specializes
public class ValidNameWithNoNumbersChecker extends ValidNameChecker {
    public boolean isValidName(String input) {
        if (input.length() < 3) {
            return false;
        }
        else {
            for (int i=0; i < input.length(); i++) {
                if (Character.isDigit(input.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }
}
