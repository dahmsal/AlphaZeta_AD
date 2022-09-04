package edu.kit.informatik.ui.command;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.ui.parameter.ShipNameParameter;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The actions command outputs all available actions of a given ship.
 * The command takes a ship name as parameter
 * @author uppyo
 * @version 1.0
 */
public class ActionsCommand extends Command {
    private static final String PATTERN = "^actions";
    private static final String HELP_TEXT = "ACTIONS [NAME] - shows available actions of ship [NAME]";
    private final List<Parameter<?>> parameters;
    private final AlphaZeta currentGame;

    /**
     * Initialise the command by passing the current game
     * @param currentGame current game that is played
     */
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
        for (String action : shipActionsToString(spaceship)) {
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

    /**
     * Output all ship-actions to a String
     * @return List of Strings representing available actions on the ship
     */
    private List<String> shipActionsToString(Spaceship spaceship) {
        List<String> actions = new ArrayList<>();
        for (Module module: spaceship.getModules()) {
            actions.addAll(module.getActiveActions().stream().map(Action::getName).collect(Collectors.toList()));
        }
        return actions.stream().sorted().collect(Collectors.toList());
    }

}
