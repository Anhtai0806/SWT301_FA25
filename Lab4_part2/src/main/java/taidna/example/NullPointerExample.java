package taidna.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NullPointerExample {
    private static final Logger logger = Logger.getLogger(NullPointerExample.class.getName());

    public static void main(String[] args) {
        String text = getNullableText();

        if (text != null && !text.isEmpty()) {
            logger.log(Level.INFO, "Text is not empty");
        } else if (text == null) {
            logger.warning("Text is null");
        } else {
            logger.info("Text is empty");
        }
    }

    private static String getNullableText() {
        // Mô phỏng dữ liệu có thể null hoặc chuỗi rỗng
        return Math.random() > 0.5 ? "Hello" : null;
    }
}
