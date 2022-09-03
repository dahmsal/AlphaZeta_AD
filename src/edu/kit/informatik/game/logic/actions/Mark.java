package edu.kit.informatik.game.logic.actions;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.actions.misc.Target;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.util.exception.ParameterException;

public class Mark extends Action {
    private static final String NAME = "MARK";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Result execute(AlphaZeta currentGame, Spaceship actor, Target target) {
        //check command validity
        Spaceship targetShip;
        try {
            targetShip = super.getShipTarget(currentGame, target);
        } catch (ParameterException e) {
            return new Result(false, e.getMessage());
        }
        if ( currentGame.getPlayerByShip(actor) == currentGame.getPlayerByShip(targetShip)) {
            return new Result(false,
                    currentGame.getPlayerByShip(actor).toString() + " cant mark it's own ships!");
        }
        //mark ship
        targetShip.markShip();
        super.activate();
        return new Result(true);
    }

}
