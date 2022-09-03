package edu.kit.informatik.util.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static java.lang.Math.abs;

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

    /**
     * Get the direct neighbors of a vector as a list of vectors (North, East, South, West)
     * @param vector2D Object of interest
     * @return list of vectors (North, East, South, West) as Vector2D
     */
    public static List<Vector2D> getDirectNeighbors(Vector2D vector2D) {
        List<Vector2D> vectorList = new ArrayList<>();
        int x = vector2D.getX();
        int y = vector2D.getY();
        //North
        vectorList.add(Vector2D.getVector(x, y - 1));
        //East
        vectorList.add(Vector2D.getVector(x + 1, y));
        //South
        vectorList.add(Vector2D.getVector(x, y + 1));
        //West
        vectorList.add(Vector2D.getVector(x - 1, y));
        return  vectorList;
    }

    public static int manhattanDistance(Vector2D vectorA, Vector2D vectorB) {
        int sum = 0;
        sum += abs(vectorA.getX() - vectorB.getX());
        sum += abs(vectorA.getY() - vectorB.getY());
        return sum;
    }

    public static Vector2D subVector(Vector2D vectorA, Vector2D vectorB) {
        return new Vector2D(vectorB.getX() - vectorA.getX(),  vectorB.getY() - vectorA.getY());
    }

}
