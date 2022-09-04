package edu.kit.informatik.game.logic.actions.misc;

import edu.kit.informatik.util.math.Vector2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Description of targets for actions of ships. A target can either be another ship or a position on the board.
 * @author uppyo
 * @version 1.0
 */
public class Target {
    private Character idTarget;
    private final List<Integer> inputs = new ArrayList<>();

    /**
     * Set a character target, used to indicate a ship-target
     * @param input char, representing the id of a ship
     */
    public void setTargetChar(char input) {
        this.idTarget = input;
    }

    /**
     * Add a value to the target-vector indicating a position on the board
     * @param i integer coordinate of vector
     */
    public void addToVector(int i) {
        this.inputs.add(i);
    }

    /**
     * Get the target id if it was set.
     * @return char id of targeted ship or null if it was not set
     */
    public Character getIdTarget() {
        return idTarget;
    }

    /**
     * Get the targeted position on the board
     * @return Vector2D position targeted or null if the vector was not correctly set
     */
    public Vector2D getVectorTarget() {
        if (this.inputs.size() == 2) {
            return new Vector2D(this.inputs.get(0), this.inputs.get(1));
        }
        return null;
    }
}
