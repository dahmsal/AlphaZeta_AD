package edu.kit.informatik.ui.parameter;

import edu.kit.informatik.game.resources.board.Tile;
import edu.kit.informatik.game.resources.board.TileTypes;
import edu.kit.informatik.util.exception.ParameterException;

import java.util.ArrayList;
import java.util.List;

public class BoardParameter implements Parameter<List<TileTypes>> {
    private static final String PATTERN = "(?i)[[.][a-d][w-z][1-2]]+";
    private List<TileTypes> value;
    private List<Character> shipID;

    public BoardParameter() {
        this.value = null;
        this.shipID = new ArrayList<>();
    }

    @Override
    public String getPattern() {
        return PATTERN;
    }

    @Override
    public void clearParameter() {
        value = null;
    }

    @Override
    public void setValue(String input) {
        char[] charArray = input.toCharArray();
        this.value = new ArrayList<>();
        for (char c: charArray) {
            if (c == TileTypes.FREE.getId()) {
                this.value.add(TileTypes.FREE);
            }
            else if (c == TileTypes.HALF_COVER.getId()) {
                this.value.add(TileTypes.HALF_COVER);
            }
            else if (c == TileTypes.FULL_COVER.getId()) {
                this.value.add(TileTypes.FULL_COVER);
            }
            else {
                this.shipID.add(c);
                this.value.add(TileTypes.SPACESHIP);
            }
        }
    }

    public List<Character> getShipID() {
        return shipID;
    }

    @Override
    public List<TileTypes> getValue() {
        return this.value;
    }

    @Override
    public boolean hasValue() {
        return this.value != null;
    }
}
