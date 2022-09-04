package edu.kit.informatik.util.math;

/**
 * Description of a two-dimensional vector. Used to indicate positions on the board for example.
 * @author uppyo
 * @version 1.0
 */
public class Vector2D {
    private final int x;
    private final int y;

    /**
     * Create a new Vector using an x and y coordinate
     * @param x int x-coordinate
     * @param y int y-coordinate
     */
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x-coordinate of the vector
     * @return as integer
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y-coordinate of the vector
     * @return as integer
     */
    public int getY() {
        return y;
    }

    /**
     * Get a new vector-object using x and y coordinates
     * @param x int x-coordinate
     * @param y int y-coordinate
     * @return a new Vector2D object
     */
    public static Vector2D getVector(int x, int y) {
        return new Vector2D(x, y);
    }

    @Override
    public boolean equals(Object other) {
        if (other.getClass() == this.getClass()) {
            Vector2D otherVector = (Vector2D) other;
            return otherVector.x == this.x && otherVector.y == this.y;
        }
        return false;
    }

}
