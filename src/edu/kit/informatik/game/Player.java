package edu.kit.informatik.game;

import edu.kit.informatik.game.resources.fleet.Fleet;
import edu.kit.informatik.util.exception.ParameterException;

/**
 * Description of a players that are part of the game.
 * @author uppyo
 * @version 1.0
 */
public class Player {
    private final String name;
    private final Fleet fleet;

    public Player(String name, Character initialChar, Character collector, int numberContainer)
            throws ParameterException {
        this.name = name;
        this.fleet = new Fleet(initialChar, collector, numberContainer);
    }
    /**
     * Get the specified name of a player
     * @return name as String
     */
    public String toString() {
        return this.name;
    }

    public Fleet getFleet() {
        return fleet;
    }

}
