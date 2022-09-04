package edu.kit.informatik.ui.parameter;


/**
 * This parameter parses possible ship-names and generates char-objects
 * @author uppyo
 * @version 1.0
 */
public class ShipNameParameter implements Parameter<Character> {
    private static final String PATTERN = "[A-Z]";
    private Character value;

    @Override
    public String getPattern() {
        return PATTERN;
    }

    @Override
    public void clearParameter() {
        this.value = null;
    }

    @Override
    public void setValue(String value) {
        String valueNorm = value.toUpperCase();
        this.value = valueNorm.charAt(0);
    }

    @Override
    public Character getValue() {
        return this.value;
    }

    @Override
    public boolean hasValue() {
        return this.value != null;
    }
}
