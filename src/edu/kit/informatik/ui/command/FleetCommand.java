package edu.kit.informatik.ui.command;

import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.resources.fleet.Battleship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.List;

public class FleetCommand extends Command {
    private static final String PATTERN = "^fleet";
    private static final String HELP_TEXT = "FLEET - shows details of all ships of the AIs";
    private List<Player> players;

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
            output.append(player.getFleet().getCollector().printModules(true)).append(UtilStrings.getLinebreak());
            for (Battleship battleship: player.getFleet().getAllBattleships()) {
                if (!battleship.isDestroyed()) {
                    output.append("<-> ").append(battleship.getId()).append(":");
                    output.append(battleship.printModules(true)).append(UtilStrings.getLinebreak());
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
}
