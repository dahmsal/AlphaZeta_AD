package edu.kit.informatik.game.resources.board;

/**
 * Description of Objects that could be on a tile
 * @author uppyo
 * @version 1.0
 */
public enum TileTypes {
    /**
     * A free tile, represented by a '.' char
     */
    FREE('.'),
    /**
     * Half-cover tile, represented by a '1' char
     */
    HALF_COVER('1'),
    /**
     * Full-cover tile, represented by a '2' char
     */
    FULL_COVER('2'),
    /**
     * Indicator that a spaceship is on the tile, default is a 'S' char
     */
    SPACESHIP('S');

    private final char id;

    TileTypes(char id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    /**
     * Get the representation of a tile-object as char
     * @return char, according to object
     */
    public char getId() {
        return id;
    }
}
