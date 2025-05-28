import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the game's functionality by extending the AWT Frame class.
 * Manages game grid, stubs and input handling.
 */
public class Main extends Frame {
    private static final int NUM_ROWS = 24;
    private static final int GRID_WIDTH = 90;
    private char[][] gameGrid = new char[NUM_ROWS][GRID_WIDTH];
    private List<Enemy> enemies = new ArrayList<>();
    private Player player = Player.getInstance();
    private InputHandler moveLeftHandler = new MoveLeftHandler();
    private InputHandler moveRightHandler = new MoveRightHandler();
    private InputHandler shootHandler = new ShootHandler();
    private InputHandler quitHandler = new QuitHandler();

    public Main() {
        /**
         * Constructs game screen.
         * Initialises title and size of frame and visibility is true.
         * Background is black like in original space invaders game.
         * Allows frame to be closed.
         */
        setTitle("Space Invaders");
        setSize(1200, 800);
        setVisible(true);
        setBackground(Color.BLACK);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        // chain of responsibility for handling input
        moveLeftHandler.setNextHandler(moveRightHandler);
        moveRightHandler.setNextHandler(shootHandler);
        shootHandler.setNextHandler(quitHandler);

        // initialises enemies, 3 cells apart
        for (int i = 0; i < NUM_ROWS; i++) {
            enemies.add(new Enemy(i * 3, 0));
        }

        // allows for player movement
        player.setMovement(new MoveLeftPlayer(player));
        player.move();

        // game loop
        while (true) {
            updateGame();
            renderGame();

            // adds timer so enemies move down screen in 1600ms increments
            try {
                Thread.sleep(1600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates enemy positions by calling move() method in Enemy class so they move
     * down the screen during play.
     * If enemies reach the player, game over.
     */
    private void updateGame() {
        // Update enemy positions and handle countdown
        for (Enemy enemy : enemies) {
            if (enemy.getY() >= 0) {
                enemy.setMovement(new MoveDown(enemy));
                enemy.move();
            }
            if (enemy.getY() == NUM_ROWS + 1) {
                System.out.println("Game Over - Enemy Reached Your Base");
                System.exit(0);
            }
        }
    }

    /**
     * Initialise game grid with empty spaces.
     * Place enemies at top row of screen.
     * Place player at bottom row of screen.
     */
    private void renderGame() {
        for (char[] row : gameGrid) {
            Arrays.fill(row, ' ');
        }

        for (Enemy enemy : enemies) {
            int enemyPosition = enemy.getX();
            gameGrid[enemy.getY()][enemyPosition] = 'X';
        }

        int playerPosition = player.getX();
        gameGrid[NUM_ROWS - 1][playerPosition] = 'O';

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // calls paint method from JPanel

        int cellWidth = getWidth() / GRID_WIDTH;
        int cellHeight = getHeight() / NUM_ROWS;
        int offset = 1;

        // Draw the game grid and enemies/player graphics
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                char symbol = gameGrid[i][j];
                if (symbol == 'X') { // enemy
                    g.setColor(Color.GREEN);
                    int enemyX = j * (cellWidth + 25) + offset * (cellWidth + 30);
                    int enemyY = i * cellHeight;
                    g.fillRect(enemyX, enemyY, (cellWidth + 30), cellHeight);
                    int legWidth = cellWidth / 3;
                    int legHeight = cellHeight / 4;
                    g.fillRect(enemyX + legWidth, enemyY + cellHeight, legWidth, legHeight); // left leg
                    g.fillRect(enemyX + 8 * legWidth, enemyY + cellHeight, legWidth, legHeight); // right leg
                } else if (symbol == 'O') { // player
                    g.setColor(Color.RED);
                    int largerRectY = i * cellHeight;
                    g.fillRect(j * (cellWidth + 60), largerRectY, (cellWidth + 60), cellHeight);
                    int smallerRectY = largerRectY - (cellHeight / 2) / 2;
                    int largerRectWidth = (cellWidth + 60);
                    int smallerRectX = j * largerRectWidth + 60 / 2;
                    g.fillRect(smallerRectX, smallerRectY, (cellWidth + 8), (cellHeight / 2));
                } else if (symbol == '^') { // bullet
                    g.setColor(Color.WHITE);
                    g.fillRect(j * cellWidth, i * (cellHeight / 2), cellWidth, (cellHeight / 2));

                }
            }
        }
    }

    /**
     * Delegates handling of user input to InputHandler interface.
     * 
     * @param handler reference to handler for InputHandler
     * @param input   integer to be processed
     */
    private void playerInput(InputHandler handler, char input) {
        handler.handleInput(input);
    }

    /**
     * Creates new instance of Main class.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Main();
    }
}
