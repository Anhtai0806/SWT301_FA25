package taidna.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UnreachableCodeExample {
    private static final Logger LOGGER = Logger.getLogger(UnreachableCodeExample.class.getName());

    public static int getNumber() {
        LOGGER.log(Level.INFO, "Returning constant number 42");
        return 42;
    }

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "Result: {0}", getNumber());
    }
}
