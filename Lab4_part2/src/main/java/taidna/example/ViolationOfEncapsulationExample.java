package taidna.example;

import java.util.logging.Level;
import java.util.logging.Logger;

class User {
    private static final Logger LOGGER = Logger.getLogger(User.class.getName());
    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void display() {
        LOGGER.log(Level.INFO, "Name: {0}, Age: {1}", new Object[]{name, age});
    }

    public static void main(String[] args) {
        User user = new User("Anh Tai", 25);
        user.display();
    }
}
