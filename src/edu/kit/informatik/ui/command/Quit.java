package edu.kit.informatik.ui.command;

import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.ui.session.Session;

import java.util.List;

/**
 * Call the internal quit function of a session to end execution of the program. The command requires no parameters.
 * @author uppyo
 * @version 1.0
 */
public class Quit extends Command {
    private static final String PATTERN = "^quit";
    private static final String HELP_TEXT = "QUIT - quits the game";
    private final Session session;


    /**
     * Initialise the quit-command by saving the session
     * @param session current session, which should be able to quit using this command
     */
    public Quit(Session session) {
        this.session = session;
    }

    @Override
    public String getHelpText() {
        return HELP_TEXT;
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
        this.session.quit();
        return new Result(true);
    }
}
