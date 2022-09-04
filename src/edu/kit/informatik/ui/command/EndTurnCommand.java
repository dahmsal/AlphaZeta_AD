package edu.kit.informatik.ui.command;

import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.ui.session.Session;

import java.util.List;

/**
 * The endturn command ends the turn of a player.
 * This command takes no parameters.
 * @author uppyo
 * @version 1.0
 */
public class EndTurnCommand extends Command {
    private static final String PATTERN = "^endturn";
    private static final String HELP_TEXT = "ENDTURN - ends the current player's turn";
    private final Session currentSession;

    /**
     * Initialise the command using the current user-session
     * @param currentSession current session that handles user-interaction
     */
    public EndTurnCommand(Session currentSession) {
        this.currentSession = currentSession;
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
        this.currentSession.getActiveDialog().endDialog();
        return new Result(true);
    }

    @Override
    public String getHelpText() {
        return HELP_TEXT;
    }
}
