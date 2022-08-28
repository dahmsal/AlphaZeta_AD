package edu.kit.informatik.ui.parser;

import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.command.Command;
import edu.kit.informatik.util.exception.InputException;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parsing logic for command-type interactions with the user
 * @author uppyo
 * @version 1.0
 */
public class CommandParser {

    private CommandParser() { }

    /**
     * Check if a active command was called, parse and execute the command
     * @param inputString full user-input
     * @param commandList list of active commands
     * @return Result of execution, is NULL if no command was called
     */
    public static Result processCommand(String inputString, List<Command> commandList) {
        for (Command command: commandList) {
            Pattern pattern = Pattern.compile(command.getPattern());
            Matcher matcher = pattern.matcher(inputString);
            if (matcher.find()) {
                String argString = matcher.replaceFirst(UtilStrings.getWhitespace());
                try {
                    ParameterParser.processParameter(command, argString, UtilStrings.getWhitespace());
                } catch (InputException e) {
                    return new Result(false, e.getMessage());
                }
                return command.execute();
            }
        }
        return null;
    }
}
