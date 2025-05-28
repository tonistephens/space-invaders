import java.util.ArrayList;
import java.util.List;

/**
 * Represents the enemies and coordinates/movement.
 * Movement controlled by Movement interface.
 */
public class Enemy {
    private int x;
    private int y;
    private Movement movement;

    /**
     * Constructs enemy with specified x,y coordinates.
     * 
     * @param x initial x-coordinate
     * @param y initial y-coordinate
     */
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Moves enemy vertically (down the screen) by specified amount.
     * 
     * @param a amount y-coordinate is adjusted
     */
    public void moveY(int a) {
        this.y += a;
    }

    /**
     * Sets movement behaviours for enemies.
     * 
     * @param movement movement behaviour to be assigned to enemy
     */
    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    // Initiates movement of enemy given its assigned movement behaviour
    public void move() {
        if (movement != null) {
            movement.move();
        }
    }

    // getters for x and y
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
