package edu.kit.informatik.game.logic.actions.attack;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.logic.actions.misc.Decider;
import edu.kit.informatik.game.logic.actions.misc.Target;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.math.Math;
import edu.kit.informatik.util.math.Vector2D;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.List;

/**
 * Describes the Attack subsection of ship-actions. Attacks attempt to inflict damage based on a dice-throw. This class
 * handles processing the attack and creating a decider-type object to complete a attack interaction with
 * (in some cases) optional user input.
 * @author uppyo
 * @version 1.0
 */
public abstract class Attack extends Action {
    private Decider decider;

    /**
     * Get the decider-object of an attack. Has to be set before queried using the process-attack methode.
     * @return the decider-type result of an processed attack
     */
    public Decider getDecider() {
        return decider;
    }

    //check target-validity based on range and fleet-association
    private void checkTargetValidity(Spaceship actor, Spaceship target, AlphaZeta game, int minRange, int maxRange)
            throws ParameterException {
        Player actorPlayer = game.getPlayerByShip(actor);
        if (actorPlayer.getFleet().getAllSpaceships().contains(target)) {
            throw new  ParameterException("invalid target! Player " + actorPlayer
                    + " is not allowed to attack it's own fleet!");
        }
        if (Math.manhattanDistance(actor.getPosition(), target.getPosition()) > maxRange) {
            throw new ParameterException("target is out range!");
        }
        if (Math.manhattanDistance(actor.getPosition(), target.getPosition()) < minRange) {
            throw new ParameterException("target is too close!");
        }
    }


    private int throwDice(AlphaZeta game) {
        return game.getCurrentGameSettings().getDice().throwDice();
    }

    /**
     * Process an attack. If successful the decider-field is set and a successful result is returned
     * @param currentGame the currently played game
     * @param actor acting spaceship
     * @param target targeted spaceship
     * @param attackProperties properties of the attack used
     * @return a result object. If successful the decider is set and can be processed further, if fail a conclusive
     * message is bundled with the result.
     */
    protected Result processCommand(AlphaZeta currentGame, Spaceship actor, Target target,
                                     AttackProperties attackProperties) {
        //check command validity
        Spaceship targetShip;
        try {
            targetShip = super.getShipTarget(currentGame, target);
        } catch (ParameterException e) {
            return new Result(false, e.getMessage());
        }
        try {
            checkTargetValidity(actor, targetShip, currentGame, attackProperties.getMinRange(),
                    attackProperties.getMaxRange());
        } catch (ParameterException e) {
            return new Result(false, e.getMessage());
        }

        // execute attack
        int diceThrow = throwDice(currentGame);
        this.decider = attackProperties.createDecider(diceThrow, actor, targetShip, currentGame);
        return new Result(true, "Roll " + diceThrow + UtilStrings.getLinebreak()
                + "Bonus " + calculateBonus(actor, targetShip, currentGame));

    }

    /**
     * Calculates the bonus of an attack. The bonus takes cover and shields into account and is used to reduce the
     * throw of an attacking player to be benefit of the target.
     * @param actor attacking/acting spaceship
     * @param target targeted spaceship
     * @param currentGame the game currently played
     * @return the bonus as int-value. If no bonus applies, (int) 0  is returned
     */
    protected static int calculateBonus(Spaceship actor, Spaceship target, AlphaZeta currentGame) {
        int result = 0;
        if (target.hasShield()) {
            result--;
        }
        //calculate cover
        int cover = 0;
        if (Math.manhattanDistance(actor.getPosition(), target.getPosition()) > 1) {
            Vector2D vectorAT = Math.subVector(target.getPosition(), actor.getPosition());
            List<Vector2D> targetNeighbors = Math.getDirectNeighbors(target.getPosition());
            //check north
            if (vectorAT.getY() < 0) {
                if (currentGame.getBoard().getCoverValue(targetNeighbors.get(0)) > cover) {
                    cover = currentGame.getBoard().getCoverValue(targetNeighbors.get(0));
                }
            }
            //check east
            if (vectorAT.getX() > 0) {
                if (currentGame.getBoard().getCoverValue(targetNeighbors.get(1)) > cover) {
                    cover = currentGame.getBoard().getCoverValue(targetNeighbors.get(1));
                }
            }
            //check south
            if (vectorAT.getY() > 0) {
                if (currentGame.getBoard().getCoverValue(targetNeighbors.get(2)) > cover) {
                    cover = currentGame.getBoard().getCoverValue(targetNeighbors.get(2));
                }
            }
            //check west
            if (vectorAT.getX() < 0) {
                if (currentGame.getBoard().getCoverValue(targetNeighbors.get(3)) > cover) {
                    cover = currentGame.getBoard().getCoverValue(targetNeighbors.get(3));
                }
            }
        }
        result -= cover;
        return result;
    }
}
