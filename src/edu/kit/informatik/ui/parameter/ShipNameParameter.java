package edu.kit.informatik.ui.parameter;

import java.util.Locale;

public class ShipNameParameter implements Parameter<Character> {
    private final static String PATTERN = "[A-Z]";
    private Character value;

    public ShipNameParameter() {
        this.value = null;
    }

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
