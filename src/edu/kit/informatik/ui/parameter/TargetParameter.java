package edu.kit.informatik.ui.parameter;

import edu.kit.informatik.game.logic.actions.misc.Target;

/**
 * The target parameter is used to parses either a digit or a char to create an target object (either char or vector2D)
 * @author uppyo
 * @version 1.0
 */
public class TargetParameter implements Parameter<Target> {
    private static final String CHAR_TARGET = "[A-Z]|[a-z]";
    private static final String INT_TARGET = "\\d";
    private Target value;

    @Override
    public String getPattern() {
        return CHAR_TARGET + "|" + INT_TARGET;
    }

    @Override
    public void clearParameter() {
        this.value = new Target();
    }

    @Override
    public void setValue(String value) {
        if (value.matches(CHAR_TARGET)) {
            this.value.setTargetChar(value.toUpperCase().charAt(0));
        }
        else if (value.matches(INT_TARGET)) {
            int i = Integer.parseInt(value);
            this.value.addToVector(i);
        }
        else  {
            this.value = null;
        }
    }

    @Override
    public Target getValue() {
        return this.value;
    }

    @Override
    public boolean hasValue() {
        return this.value != null;
    }
}
