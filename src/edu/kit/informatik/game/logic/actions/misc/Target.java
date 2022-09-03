package edu.kit.informatik.game.logic.actions.misc;

import edu.kit.informatik.util.math.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Target {
    private Character idTarget;
    private final List<Integer> inputs = new ArrayList<>();

    public Target() {
        this.idTarget = null;
    }

    public void setTargetChar(char input) {
        this.idTarget = input;
    }

    public void addToVector(int i) {
        this.inputs.add(i);
    }

    public Character getIdTarget() {
        return idTarget;
    }

    public Vector2D getVectorTarget() {
        if (this.inputs.size() == 2) {
            return new Vector2D(this.inputs.get(0), this.inputs.get(1));
        }
        return null;
    }
}
