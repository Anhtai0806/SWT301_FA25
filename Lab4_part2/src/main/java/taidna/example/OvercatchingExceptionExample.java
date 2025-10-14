package taidna.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OvercatchingExceptionExample {
    private static final Logger logger = Logger.getLogger(OvercatchingExceptionExample.class.getName());

    public static void main(String[] args) {
        try {
            int[] arr = {10, 20, 30, 40, 50}; // mảng có 5 phần tử
            int index = 4; // truy cập phần tử hợp lệ
            logger.log(Level.INFO, "Element at index {0}: {1}", new Object[]{index, arr[index]});
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Array index out of bounds: {0}", e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error: {0}", e.getMessage());
        }
    }
}
