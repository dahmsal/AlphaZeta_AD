package edu.kit.informatik.game.logic;

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

    /**
     * Initialise a player using provided data
     * @param name Name of the Player as String
     * @param initialChar initial char of fleet, used to dynamically create the fleet-id's
     * @param collector id of the collector-ship
     * @param numberContainer number of containers,as specified in the game-settings
     * @throws ParameterException if the creation of a fleet fails
     */
    public Player(String name, Character initialChar, Character collector, int numberContainer)
            throws ParameterException {
        this.name = name;
        this.fleet = new Fleet(initialChar, collector, numberContainer);
    }

    /**
     * Get the specified name of a player
     * @return name as String
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Get the fleet of a player. A fleet includes all battleships and the collector.
     * @return the current fleet as fleet-object
     */
    public Fleet getFleet() {
        return fleet;
    }

}
