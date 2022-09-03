package edu.kit.informatik.game.logic.actions.attack;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.logic.actions.misc.Decider;
import edu.kit.informatik.game.logic.actions.misc.Target;
import edu.kit.informatik.game.resources.board.Tile;
import edu.kit.informatik.game.resources.board.TileTypes;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.math.Vector2D;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.List;

public abstract class Attack extends Action {
    private Decider decider;


    protected void createDecider(Player decider, int modules, Spaceship target) {
        this.decider = new Decider(decider, modules, target);
    }

    public Decider getDecider() {
        return decider;
    }

    protected void checkTargetValidity(Spaceship actor, Spaceship target, AlphaZeta game, int minRange, int maxRange)
            throws ParameterException {
        Player actorPlayer = game.getPlayerByShip(actor);
        if (actorPlayer.getFleet().getAllSpaceships().contains(target)) {
            throw new  ParameterException("invalid target! Player " + actorPlayer
                    + " is not allowed to attack it's own fleet!");
        }
        if (Vector2D.manhattanDistance(actor.getPosition(), target.getPosition()) > maxRange) {
            throw new ParameterException("target is out range!");
        }
        if (Vector2D.manhattanDistance(actor.getPosition(), target.getPosition()) < minRange) {
            throw new ParameterException("target is too close!");
        }
    }

    protected int throwDice(AlphaZeta game) {
        return game.getCurrentGameSettings().getDice().throwDice();
    }

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

    protected static int calculateBonus(Spaceship actor, Spaceship target, AlphaZeta currentGame) {
        int result = 0;
        if (target.hasShield()) {
            result--;
        }
        //calculate cover
        int cover = 0;
        if (Vector2D.manhattanDistance(actor.getPosition(), target.getPosition()) > 1) {
            Vector2D vectorAT = Vector2D.subVector(target.getPosition(), actor.getPosition());
            List<Vector2D> targetNeighbors = Vector2D.getDirectNeighbors(target.getPosition());
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
