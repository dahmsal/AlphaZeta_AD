package edu.kit.informatik.ui.command;

import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.interaction.Interaction;
import edu.kit.informatik.ui.interaction.ShipAction;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.ArrayList;
import java.util.List;

public class Help extends Command {
    private List<Command> registeredCommands;
    private Interaction registeredInteraction;
    private static String PATTERN = "^help";
    private static String HELP_TEXT = "HELP - shows this text";

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
        stringBuilder.append(ShipAction.getHelpText()).append(UtilStrings.getLinebreak());
        return new Result(true, stringBuilder.toString().trim());
    }

    @Override
    public String getHelpText() {
        return HELP_TEXT;
    }

}
