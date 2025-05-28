/**
 * Represents the player and coordinates/movement.
 * Movement controlled by Movement interface.
 */
public class Player {
    private static Player instance;
    private int x;
    private int y;
    private Movement movement;

    /**
     * Constructs player with specified x,y coordinates.
     * Private to enforce singleton pattern.
     * 
     * @param x initial x-coordinate
     * @param y initial y-coordinate
     */
    private Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns instance of Player class.
     * If the instance does not exist, it is created with initial coordinates (0,0).
     * 
     * @return singleton instance of Player class
     */
    public static Player getInstance() {
        if (instance == null) {
            instance = new Player(0, 0);
        }
        return instance;
    }

    /**
     * Moves player horizontally by specified amount.
     * 
     * @param a amount x-coordinate is adjusted
     */
    public void moveX(int a) {
        this.x += a;
    }

    /**
     * Sets movement behaviour for player.
     * 
     * @param movement movement behaviour to be assigned to player
     */
    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    // Initiates movement of bullet given its assigned movement behaviour
    public void move() {
        if (movement != null) {
            movement.move();
        }
    }

    // Initiates player shooting action, firing a bullet from player stub
    public void shoot() {
        Bullet bullet = Bullet.getInstance(this.getX(), this.getY());
        System.out.println("Player has fired!");
    }

    // getters for x and y
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
