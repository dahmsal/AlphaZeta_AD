package edu.kit.informatik.util.math;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Collection of useful math-operations, also includes vector2D operations
 * @author uppyo
 * @version 1.0
 */
public final class Math {
    private Math() { }

    /**
     * Check if an given integer is odd
     * @param i integer
     * @return true if the integer is odd
     */
    public static boolean isOdd(int i) {
        return (i & 1) == 1;
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
        return Vector2D.getVector(maxID - vector2D.getY(), maxID - vector2D.getX());
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

    /**
     * Get the manhattan distance between two vectors. The distance is calculated using the deltas between both
     * coordinates.
     * @param vectorA a Vector2D object
     * @param vectorB a Vector2D object
     * @return the distance as int
     */
    public static int manhattanDistance(Vector2D vectorA, Vector2D vectorB) {
        int sum = 0;
        sum += abs(vectorA.getX() - vectorB.getX());
        sum += abs(vectorA.getY() - vectorB.getY());
        return sum;
    }

    /**
     * Subtract two vectors to get the connecting vector. Vector A is subtracted from B
     * @param vectorA Vector2D A
     * @param vectorB Vector2D B
     * @return The vector from A to B as Vector2D
     */
    public static Vector2D subVector(Vector2D vectorA, Vector2D vectorB) {
        return new Vector2D(vectorB.getX() - vectorA.getX(),  vectorB.getY() - vectorA.getY());
    }



}
