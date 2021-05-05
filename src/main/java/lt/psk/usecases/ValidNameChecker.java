package lt.psk.usecases;

import lt.psk.qualifiers.Standard;

@Standard
public class ValidNameChecker {
    public boolean isValidName(String input) {
        if (input.length() < 3) {
            return false;
        }
        else return true;
    }
}
