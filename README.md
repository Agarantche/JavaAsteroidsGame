# Java Asteroids Arcade Game

A classic 2D arcade space shooter game, "Asteroids," implemented in Java using the Swing/AWT libraries for graphics and user interface. Players control a spaceship to navigate through an asteroid field, destroying asteroids and avoiding collisions to achieve a high score.

## Features

* **Spaceship Control:**
    * Thrust forward
    * Rotate left and right
    * Wrap-around movement (ship reappears on the opposite side of the screen when going off-bounds)
* **Shooting:** Fire bullets to destroy asteroids.
* **Asteroids:**
    * Multiple asteroids appear on screen with random trajectories and sizes.
    * Asteroids break into smaller pieces when shot (if implemented, otherwise they are just destroyed).
    * Wrap-around movement for asteroids.
* **Collision Detection:**
    * Ship collision with asteroids results in Game Over.
    * Bullet collision with asteroids destroys the asteroid (or breaks it).
* **Scoring:** Points awarded for destroying asteroids.
* **Game Over State:** Displays when the player's ship is hit.
* **Starry Background:** A simple starfield for visual effect.

## Technologies Used

* **Java:** Core programming language.
* **Java Swing & AWT:** Used for creating the game window, drawing graphics (ship, asteroids, bullets, stars), and handling user input (keyboard events).
* **Object-Oriented Programming (OOP):** Game elements like Ship, Asteroid, Bullet, and Star are designed as objects with their own behaviors and properties.

## How to Compile and Run

1.  **Prerequisites:**
    * Java Development Kit (JDK) installed (e.g., JDK 8 or later).

2.  **Download Files:**
    * Ensure you have all the necessary `.java` files for the project in a single directory:
        * `Asteroid.java`
        * `Asteroids.java` (This is likely the main class)
        * `Bullet.java`
        * `Circle.java` (Abstract class)
        * `Game.java` (Abstract class, likely provided framework)
        * `Polygon.java` (Abstract class)
        * `Ship.java`
        * `Star.java`

3.  **Compile:**
    * Open a terminal or command prompt.
    * Navigate to the directory where you saved the `.java` files.
    * Compile all the Java files using the Java compiler:
        ```bash
        javac *.java
        ```
        (This command compiles all `.java` files in the current directory. If you have a specific build order or package structure, adjust accordingly. Based on your files, they seem to be in the default package.)

4.  **Run:**
    * After successful compilation, run the main class (which is likely `Asteroids.java`):
        ```bash
        java Asteroids
        ```
    * A game window should appear.

## Controls

* **W:** Thrust forward
* **A:** Rotate ship left
* **D:** Rotate ship right
* **Spacebar:** Fire bullet

## Project Structure

(Briefly describe if there's any specific structure, otherwise this section can be omitted if all files are in the root)
* All `.java` source files are located in the root directory.
* `Game.java`, `Polygon.java`, and `Circle.java` act as base/abstract classes for the game elements.

---

*Developed by Adam Garantche.*

