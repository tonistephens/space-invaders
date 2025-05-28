## 🛸 Space Invaders (Java Game)

### 🎮 Description
This is a simple recreation of the classic *Space Invaders* arcade game, developed using Java AWT and object-oriented programming concepts.

The player controls a red ship (`O`) at the bottom of the screen and must avoid or shoot descending green enemies (`X`). The game ends when an enemy reaches the base.

---

### 🧠 Design Patterns & Principles

| File               | Applied Principles / Patterns                        |
|--------------------|------------------------------------------------------|
| **Main.java**      | Chain of Responsibility                              |
| **InputHandler.java** | Chain of Responsibility, Single Responsibility      |
| **Player.java**    | Singleton                                             |
| **Enemy.java**     | (Can be extended to Strategy or State)               |
| **Bullet.java**    | Singleton                                             |
| **Movement.java**  | Open/Closed Principle (via strategy pattern)         |

---

### 🔍 Explanation

- **Singleton**  
  Ensures only one instance exists of critical classes like `Player` and `Bullet`.

- **Chain of Responsibility**  
  Used in input handling — each handler (e.g., `MoveLeftHandler`, `ShootHandler`) processes specific key presses and passes others down the chain.

- **Single Responsibility Principle**  
  Each class has one responsibility — e.g., `InputHandler` only deals with input delegation.

- **Open/Closed Principle**  
  The movement logic is open to extension (add new movement types), but closed to modification of existing code (Strategy pattern).

---
