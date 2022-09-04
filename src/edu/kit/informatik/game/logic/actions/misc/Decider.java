package edu.kit.informatik.game.logic.actions.misc;

import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.resources.fleet.Spaceship;

/**
 * The Decider object stores and organises all relevant information to continue attack-processing. If an attack-action
 * was successfully executed a decider is created, which is processed using the DestroyModules interaction. A Decider
 * includes information about the deciding player, the damage of the attack and the spaceship that is targeted.
 * @author uppyo
 * @version 1.0
 */
public class Decider {
    private final Player decidingPlayer;
    private final int moduleCount;
    private final Spaceship targetShip;

    /**
     * Create a decider by setting a deciding player, the count of destroyed modules and the target-spaceship
     * @param player Player object, deciding player; can choose modules to be destroyed
     * @param moduleCount Integer number of modules to be destroyed
     * @param spaceship the spaceship on which modules are destroyed
     */
    public Decider(Player player, int moduleCount, Spaceship spaceship) {
        this.decidingPlayer = player;
        this.moduleCount = moduleCount;
        this.targetShip = spaceship;
    }

    /**
     * Get the deciding player
     * @return Player object, deciding player
     */
    public Player getDecidingPlayer() {
        return decidingPlayer;
    }

    /**
     * Get the Module-count
     * @return modules to be destroyed as int
     */
    public int getModuleCount() {
        return moduleCount;
    }

    /**
     * Get the ship on which modules are destroyed
     * @return targeted spaceship
     */
    public Spaceship getTargetShip() {
        return targetShip;
    }
}
