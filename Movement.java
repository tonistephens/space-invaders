/**
 * Declares classes implementing movement options for enemies/players, using
 * open/closed principle.
 */

public interface Movement {
    void move();
}

/**
 * Implementation for ability to move left in Player class.
 * If player is at x=0, cannot move any further left.
 * Otherwise, moves player left by 2 units.
 */

class MoveLeft implements Movement {
    private Player player;

    public MoveLeft(Player player) {
        this.player = player;
    }

    @Override
    public void move() {
        if (player.getX() > 0) {
            player.moveX(-2);
        } else {
            System.out.println("Cannot move left - already at leftmost position");
        }
    }
}

/**
 * Implementation for ability to move right in Player class.
 * If player is at, cannot move any further right.
 * Moves player right by 2 units.
 */

class MoveRight implements Movement {
    private Player player;

    public MoveRight(Player player) {
        this.player = player;
    }

    @Override
    public void move() {
        try {
            player.moveX(2);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Cannot move right - already at rightmost position");
        }
    }
}

/**
 * Implementation for ability to move down in Enemy class.
 * Moves enemy down by 1 unit.
 */

class MoveDown implements Movement {
    private Enemy enemy;

    public MoveDown(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void move() {
        enemy.moveY(1);
    }
}

/**
 * Implementation for ability to move up in Bullet class.
 * Moves bullet up by 1 unit.
 */
class MoveBullet implements Movement {
    private Bullet bullet;

    public MoveBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    @Override
    public void move() {
        bullet.moveY(-1);
    }
}
