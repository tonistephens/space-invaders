import java.util.Scanner;

/**
 * Declares two methods to handle user inputs given in Main class.
 * 
 * @param input       integer to be processed by handler
 * @param nextHandler reference to next handler in chain of responsibility,
 *                    forming linked list of handlers
 */

public interface InputHandler {
    void handleInput(char input);

    void setNextHandler(InputHandler nextHandler);
}

/**
 * Handles '1' input given by user in Main class which sets
 * movement of player to new instance of MoveLeftPlayer, which implements
 * Movement interface and moves player left.
 * If input given is not '1', delegates input handling to the next handler in
 * the chain.
 * 
 * @param input       integer to be processed
 * @param nextHandler sets the next handler in the chain
 */

class MoveLeftHandler implements InputHandler {
    private InputHandler nextHandler;

    @Override
    public void handleInput(char input) {
        if (input == '1') {
            Player.getInstance().setMovement(new MoveLeftPlayer(Player.getInstance()));
            Player.getInstance().move();
        } else if (nextHandler != null) {
            nextHandler.handleInput(input);
        }
    }

    @Override
    public void setNextHandler(InputHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

/**
 * Handles '2' input given by user in Main class which sets
 * movement of player to new instance of MoveRightPlayer, which implements
 * Movement interface and moves player right.
 * If input given is not '2', delegates input handling to the next handler in
 * the chain.
 * 
 * @param input       integer to be processed
 * @param nextHandler sets the next handler in the chain
 */

class MoveRightHandler implements InputHandler {
    private InputHandler nextHandler;

    @Override
    public void handleInput(char input) {
        if (input == '2') {
            Player.getInstance().setMovement(new MoveRightPlayer(Player.getInstance()));
            Player.getInstance().move();
        } else if (nextHandler != null) {
            nextHandler.handleInput(input);
        }
    }

    @Override
    public void setNextHandler(InputHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

/**
 * Handles '3' input given by user in Main class which invokes shoot
 * method in Player class.
 * If input given is not '3', delegates input handling to the next handler in
 * the chain.
 * 
 * @param input       integer to be processed
 * @param nextHandler sets the next handler in the chain
 */

class ShootHandler implements InputHandler {
    private InputHandler nextHandler;
    private char[][] gameGrid;

    @Override
    public void handleInput(char input) {
        if (input == '3') {
            Player player = Player.getInstance();
            player.shoot();

            int bulletPosition = player.getX();
            int bulletRow = player.getY() + 1; // Row above the player

            updateGameGrid(bulletRow, bulletPosition, '^');
        } else if (nextHandler != null) {
            nextHandler.handleInput(input);
        }
    }

    private void updateGameGrid(int row, int col, char symbol) {
        gameGrid[row][col] = symbol;
    }

    @Override
    public void setNextHandler(InputHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // @Override
    public void setGameGrid(char[][] gameGrid) {
        this.gameGrid = gameGrid;
    }
}

/**
 * Handles '4' input given by user in Main class which exits game,
 * terminating the program.
 * If input given is not '4', delegates input handling to the next handler in
 * the chain.
 * 
 * @param input       integer to be processed
 * @param nextHandler sets the next handler in the chain
 */

class QuitHandler implements InputHandler {
    @Override
    public void handleInput(char input) {
        if (input == '4') {
            System.out.println("Game Over - Player Quit");
            // prints message before terminating program
            System.exit(0);
        }
    }

    @Override
    public void setNextHandler(InputHandler nextHandler) {

    }
}
