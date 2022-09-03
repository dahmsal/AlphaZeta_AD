package edu.kit.informatik.ui.command;

import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.ui.session.Dialog;
import edu.kit.informatik.ui.session.Session;

import java.util.List;

public class EndTurnCommand extends Command {
    private static final String PATTERN = "^endturn";
    private static final String HELP_TEXT = "ENDTURN - ends the current player's turn";
    private Session currentSession;

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
