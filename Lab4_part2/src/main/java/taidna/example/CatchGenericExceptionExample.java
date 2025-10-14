package taidna.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CatchGenericExceptionExample {
    private static final Logger logger = Logger.getLogger(CatchGenericExceptionExample.class.getName());

    public static void main(String[] args) {
        try {
            String s = getNullableString();

            if (s != null) {
                logger.log(Level.INFO, "Length: {0}", s.length());
            } else {
                logger.warning("Variable 's' is null");
            }

        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "NullPointerException occurred: {0}", e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error: {0}", e.getMessage());
        }
    }

    private static String getNullableString() {
        // Giả lập dữ liệu có thể null, ví dụ như đọc từ cơ sở dữ liệu hoặc input
        return Math.random() > 0.5 ? "Hello" : null;
    }
}
