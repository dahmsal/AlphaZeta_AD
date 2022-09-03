package edu.kit.informatik.ui.command;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.ui.parameter.ShipNameParameter;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.List;

public class ActionsCommand extends Command {
    private static final String PATTERN = "^actions";
    private static final String HELP_TEXT = "ACTIONS [NAME] - shows available actions of ship [NAME]";
    private final List<Parameter<?>> parameters;
    private final AlphaZeta currentGame;

    public ActionsCommand(AlphaZeta currentGame) {
        this.currentGame = currentGame;
        this.parameters = List.of(new ShipNameParameter());
    }

    @Override
    public String getPattern() {
        return PATTERN;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return this.parameters;
    }

    @Override
    public Result execute() {
        char shipID;
        if (this.parameters.get(0) == null) {
            return new Result(false, "no ship name was given!");
        }
        try {
            shipID = (char) this.parameters.get(0).getValue();
        } catch (ClassCastException e) {
            return new Result(false, "could not parse parameter!");
        }
        Spaceship spaceship = this.currentGame.getSpaceship(shipID);
        if (spaceship == null) {
            return new Result(false, "no ship with given name could be found!");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Available actions of ").append(spaceship.getId()).append(UtilStrings.getDdot());
        stringBuilder.append(UtilStrings.getWhitespace());
        for (String action : spaceship.shipActionsToString()) {
            stringBuilder.append(action).append(UtilStrings.getComma()).append(UtilStrings.getWhitespace());
        }
        int lastComma = stringBuilder.lastIndexOf(UtilStrings.getComma());
        stringBuilder.replace(lastComma, lastComma + 1, UtilStrings.getEmptyString());
        return new Result(true, stringBuilder.toString().trim());
    }

    @Override
    public String getHelpText() {
        return HELP_TEXT;
    }
}
