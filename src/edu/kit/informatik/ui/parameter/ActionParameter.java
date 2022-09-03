package edu.kit.informatik.ui.parameter;

import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.util.GameParam;
import edu.kit.informatik.util.strings.UtilPatterns;

import java.util.List;
import java.util.Locale;

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
