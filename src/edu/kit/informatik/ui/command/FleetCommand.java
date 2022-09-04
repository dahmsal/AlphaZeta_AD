package edu.kit.informatik.ui.command;

import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.resources.fleet.Battleship;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.util.strings.StringComposer;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.List;

/**
 * The fleet command generates a graphical representation of all fleets of the current game.
 * This command takes no parameters.
 * @author uppyo
 * @version 1.0
 */
public class FleetCommand extends Command {
    private static final String PATTERN = "^fleet";
    private static final String HELP_TEXT = "FLEET - shows details of all ships of the AIs";
    private final List<Player> players;

    /**
     * Initialise the command by passing all active players
     * @param players active players of the game
     */
    public FleetCommand(List<Player> players) {
        this.players = players;
    }

    @Override
    public String getPattern() {
        return PATTERN;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return List.of();
    }

    @Override
    public Result execute() {
        StringBuilder output = new StringBuilder();
        for (Player player: this.players) {
            output.append(player.toString()).append("'s fleet").append(UtilStrings.getLinebreak());
            output.append("<C> ").append(player.getFleet().getCollector().getId()).append(":");
            output.append(printModules(player.getFleet().getCollector())).append(UtilStrings.getLinebreak());
            for (Battleship battleship: player.getFleet().getAllBattleships()) {
                if (!battleship.isDestroyed()) {
                    output.append("<-> ").append(battleship.getId()).append(":");
                    output.append(printModules(battleship)).append(UtilStrings.getLinebreak());
                }
            }
            for (Battleship battleship: player.getFleet().getAllBattleships()) {
                if (battleship.isDestroyed()) {
                    output.append("~X~ ").append(battleship.getId()).append(UtilStrings.getLinebreak());
                }
            }
        }
        return new Result(true, output.toString().trim());
    }

    @Override
    public String getHelpText() {
        return HELP_TEXT;
    }

    /**
     * Output all modules of the ship to a String
     * @return String representing modules of the ship
     */
    private String printModules(Spaceship spaceship) {
        return StringComposer.listToString(spaceship.getModules());
    }
}
