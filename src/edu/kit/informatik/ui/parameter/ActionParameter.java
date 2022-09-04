package edu.kit.informatik.ui.parameter;

import edu.kit.informatik.util.GameParam;
import edu.kit.informatik.util.strings.UtilPatterns;

import java.util.List;

/**
 * The action-parameter is used to parse user-inputs that call ship-actions. To make a action accessible to the user,
 * the name of the action has to be added to the GameParam class.
 * @author uppyo
 * @version 1.0
 */
public class ActionParameter implements Parameter<String> {
    private static final List<String> ACTIONS = GameParam.gameActions();

    private String value;

    @Override
    public String getPattern() {
        return UtilPatterns.getMultipleWordPattern(ACTIONS);
    }

    @Override
    public void clearParameter() {
        this.value = null;
    }

    @Override
    public void setValue(String value) {
        this.value = value.toUpperCase();
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean hasValue() {
        return this.value != null;
    }
}
