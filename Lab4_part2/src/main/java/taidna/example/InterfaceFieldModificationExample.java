package taidna.example;

import java.util.logging.Logger;

final class AppConstants {
    private AppConstants() {
        // Ngăn không cho khởi tạo
    }

    public static final int MAX_USERS = 100;
}

public class InterfaceFieldModificationExample {
    private static final Logger logger = Logger.getLogger(InterfaceFieldModificationExample.class.getName());
    public static void main(String[] args) {
        logger.info("Max users allowed: " + AppConstants.MAX_USERS);
    }
}
