package edu.kit.informatik.util.math;

public class Vector2D {
    private int x;
    private int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setVector(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    /**
     * Mirror a Vector on the sw-ne axis of the board
     * @param vector2D a given 2d-vector
     * @param boardLength side-length of the board
     * @return mirrored vector as Vector2D
     */
    public static Vector2D mirrorVector(Vector2D vector2D, int boardLength) {
        //get the max-id of a board axis
        int maxID = boardLength - 1;
        return getVector(maxID - vector2D.y, maxID - vector2D.x);
    }


}
