package edu.kit.informatik.game.logic.actions;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.actions.misc.Target;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.util.exception.ParameterException;

public abstract class Action {
    public boolean status;

    public Action() {
        this.status = true;
    }

    protected void activate() {
        this.status = false;
    }

    public abstract String getName();

    public abstract Result execute(AlphaZeta currentGame, Spaceship actor, Target target);

    public void reset() {
        this.status = true;
    }

    public boolean isOnline() {
        return this.status;
    }

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
