package edu.kit.informatik.ui.command;

import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.interaction.ShipAction;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.ArrayList;
import java.util.List;

/**
 * The help-command creates an overview of all commands and interactions the user can engage with. Help-text are defined
 * in the respective classes of the commands.
 * This command takes no parameters.
 * @author uppyo
 * @version 1.0
 */
public class Help extends Command {
    private static final String PATTERN = "^help";
    private static final String HELP_TEXT = "HELP - shows this text";

    private final List<Command> registeredCommands;

    /**
     * Initialise the command by passing a list of active commands of the session
     * @param commandList list of all active commands of the session.
     */
    public Help(List<Command> commandList) {
        this.registeredCommands = new ArrayList<>();
        this.registeredCommands.add(this);
        this.registeredCommands.addAll(commandList);
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
        StringBuilder stringBuilder = new StringBuilder();
        for (Command command: this.registeredCommands) {
            stringBuilder.append(command.getHelpText()).append(UtilStrings.getLinebreak());
        }
        // add the ship-action help-text
        stringBuilder.append(ShipAction.getHelpText()).append(UtilStrings.getLinebreak());
        return new Result(true, stringBuilder.toString().trim());
    }

    @Override
    public String getHelpText() {
        return HELP_TEXT;
    }

}
