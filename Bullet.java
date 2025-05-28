public class Bullet {
    private static Bullet instance;
    private int x;
    private int y;
    private Movement movement;

    /**
     * Constructs bullet with specified x,y coordinates.
     * 
     * @param x initial x-coordinate
     * @param y initial y-coordinate
     */
    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns instance of Bullet class.
     * If the instance does not exist, it is created with initial coordinates to
     * match player as the bullet will shoot out from player stub.
     * 
     * @return singleton instance of Bullet class
     */
    public static Bullet getInstance(int playerX, int playerY) {
        if (instance == null) {
            instance = new Bullet(playerX, playerY);
        }
        return instance;
    }

    /**
     * Moves bullet vertically (up the screen) by specified amount.
     * 
     * @param a amount y-coordinate is adjusted
     */
    public void moveY(int a) {
        this.x += a;
    }

    /**
     * Sets movement behaviour for bullet.
     * 
     * @param movement movement behaviour to be assigned to bullet
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

    // getters for x and y
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
