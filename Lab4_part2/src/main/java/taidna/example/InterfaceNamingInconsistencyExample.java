package taidna.example;

import java.util.logging.Level;
import java.util.logging.Logger;

// Interface đặt tên đúng chuẩn và có kiểu trả về rõ ràng
interface LoginHandler {
    void login(String username, String password);
}

// Class triển khai interface
class SimpleLoginHandler implements LoginHandler {
    private static final Logger LOGGER = Logger.getLogger(SimpleLoginHandler.class.getName());

    @Override
    public void login(String username, String password) {
        if ("admin".equals(username) && "123456".equals(password)) {
            LOGGER.log(Level.INFO, "Login successful for user: {0}", username);
        } else {
            LOGGER.log(Level.WARNING, "Login failed for user: {0}", username);
        }
    }

    public static void main(String[] args) {
        LoginHandler handler = new SimpleLoginHandler();
        handler.login("admin", "123456");
        handler.login("user", "wrongpass");
    }
}
