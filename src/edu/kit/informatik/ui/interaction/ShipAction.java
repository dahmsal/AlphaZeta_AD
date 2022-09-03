package edu.kit.informatik.ui.interaction;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.logic.actions.attack.Attack;
import edu.kit.informatik.game.logic.actions.misc.Decider;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.ActionParameter;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.ui.parameter.ShipNameParameter;
import edu.kit.informatik.ui.parameter.TargetParameter;
import edu.kit.informatik.util.exception.ParameterException;

import java.util.List;

public class ShipAction extends Interaction {
    private final List<Parameter<?>> parameters;
    private final AlphaZeta currentGame;
    private final Player player;
    private final ShipNameParameter shipNameParameter = new ShipNameParameter();
    private final ActionParameter actionParameter = new ActionParameter();
    private final TargetParameter targetParameter = new TargetParameter();
    private Decider decider;

    public ShipAction(AlphaZeta currentGame, Player player) {
        this.currentGame = currentGame;
        this.player = player;
        this.parameters = List.of(this.shipNameParameter, this.actionParameter,
                this.targetParameter, this.targetParameter);
        this.decider = null;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return this.parameters;
    }

    @Override
    public Result execute() {
        //check parameters
        if (!this.shipNameParameter.hasValue()) {
            return new Result(false, "no ship name was provided!");
        }
        if (!this.actionParameter.hasValue()) {
            return new Result(false, "no action was provided!");
        }
        if (!this.targetParameter.hasValue()) {
            return new Result(false, "no target was provided!");
        }
        //check action availability
        Spaceship actor;
        try {
            actor = this.player.getFleet().getSpaceship(this.shipNameParameter.getValue());
        } catch (ParameterException e) {
            return new Result(false, e.getMessage());
        }
        String actionName = this.actionParameter.getValue();
        Action availableAction = actor.shipActions().stream().filter(action -> action.getName().equals(actionName))
                .findFirst().orElse(null);
        if (availableAction == null) {
            return new Result(false, "action: \"" + actionName
                    + "\" is not available on " + actor.toString());
        }
        Result result = availableAction.execute(this.currentGame, actor, this.targetParameter.getValue());
        // check if an attack was called and a decider is needed
        if (result.isSuccess() && availableAction instanceof Attack) {
            this.decider = ((Attack) availableAction).getDecider();
        }
        return result;
    }

    public static String getHelpText() {
        String result = "[SHIP] [ACTION] [X] [Y] - let [SHIP] perform [ACTION] on field (X,Y)\n";
        result += result.replaceFirst("\\[X\\] \\[Y\\]", "[TARGET]")
                .replaceFirst("field \\(X,Y\\)", "[TARGET]");
        return result;
    }

    public boolean hasDecider() {
        return this.decider != null;
    }

    public Decider getDecider() {
        return this.decider;
    }
}
