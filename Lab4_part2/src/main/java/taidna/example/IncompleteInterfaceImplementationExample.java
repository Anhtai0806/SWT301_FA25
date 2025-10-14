package taidna.example;

import java.util.logging.Level;
import java.util.logging.Logger;

interface Shape {
    void draw();
    void resize();
}

class Square implements Shape {
    private static final Logger LOGGER = Logger.getLogger(Square.class.getName());

    @Override
    public void draw() {
        LOGGER.log(Level.INFO, "Drawing square");
    }

    @Override
    public void resize() {
        LOGGER.log(Level.INFO, "Resizing square");
    }

    public static void main(String[] args) {
        Shape square = new Square();
        square.draw();
        square.resize();
    }
}
