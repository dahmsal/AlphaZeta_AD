package edu.kit.informatik.ui.command;

import edu.kit.informatik.game.resources.board.Board;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.Parameter;

import java.util.List;

/**
 * The board-command enables the user to query a graphical representation of the board.
 * This command has no parameters.
 * @author uppyo
 * @version 1.0
 */
public class BoardCommand extends Command {
    private static final String PATTERN = "^board";
    private static final String HELP_TEXT = "BOARD - shows the whole board";
    private final Board currentBoard;

    /**
     * The board-command needs the currently played board to be initialised
     * @param currentBoard the currently active board
     */
    public BoardCommand(Board currentBoard) {
        this.currentBoard = currentBoard;
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
        return new Result(true, currentBoard.toString());
    }

    @Override
    public String getHelpText() {
        return HELP_TEXT;
    }
}
