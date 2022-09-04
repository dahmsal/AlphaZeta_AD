package edu.kit.informatik.game.logic.actions;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.actions.misc.Target;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.util.exception.ParameterException;

/**
 * Description of an action. Actions are typically assigned to modules and can be used once per turn. In general an
 * action is targeted toward either a location or an other spaceship.
 * @author uppyo
 * @version 1.0
 */
public abstract class Action {
    private boolean status;

    /**
     * Initialise an action by setting the status-flag to true. The flag indicates if an action is ready to be used.
     */
    public Action() {
        this.status = true;
    }

    /**
     * Set the action-flag to indicate an attack was successfully used.
     */
    protected void activate() {
        this.status = false;
    }

    /**
     * Get the name of an action as string. Names are (by convention) in uppercase.
     * @return name of an action as String
     */
    public abstract String getName();

    /**
     * Attempt to execute the action. If execution was successful, activate() should be called !
     * @param currentGame the game currently played
     * @param actor the acting spaceship
     * @param target the targeted spaceship
     * @return a Result object. If the execution was not successful the result includes a conclusive message.
     */
    public abstract Result execute(AlphaZeta currentGame, Spaceship actor, Target target);

    /**
     * Reset the status flag of the action to indicate that it can be used again.
     */
    public void reset() {
        this.status = true;
    }

    /**
     * Check if the action is able to be used in the current turn
     * @return true if the status flag was set.
     */
    public boolean isOnline() {
        return this.status;
    }

    /**
     * Get the spaceship indicated by a target object.
     * @param currentGame game currently played
     * @param target target-object, can indicate either a location or a ship
     * @return the targeted spaceship
     * @throws ParameterException if the target-object was not correctly set or if the ship could not be found
     */
    protected Spaceship getShipTarget(AlphaZeta currentGame, Target target) throws ParameterException {
        if (target.getIdTarget() == null) {
            throw new ParameterException("no ship-target was set!");
        }
        char targetID = target.getIdTarget();
        Spaceship targetShip = currentGame.getSpaceship(targetID);
        if (targetShip == null) {
            throw new ParameterException("targeted ship: " + targetID + " could not be found!");
        }
        return targetShip;
    }
}
