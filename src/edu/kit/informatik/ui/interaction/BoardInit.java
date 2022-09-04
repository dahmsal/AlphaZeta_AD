package edu.kit.informatik.ui.interaction;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.BoardParameter;
import edu.kit.informatik.ui.parameter.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * This interaction handles the user driven configuration of the board. It takes a board-configuration as parameter and
 * attempts to create a board using the parameter. If the creation was not successful a Result with conclusive
 * error - message is returned.
 * @author uppyo
 * @version 1.0
 */
public class BoardInit extends Interaction {
    private final BoardParameter boardParam;
    private final AlphaZeta currentGame;

    /**
     * Initialise the interaction using the current game.
     * @param currentGame game that is currently played
     */
    public BoardInit(AlphaZeta currentGame) {
        this.boardParam = new BoardParameter();
        this.currentGame = currentGame;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return List.of(boardParam);
    }

    @Override
    public Result execute() {
        if (!this.boardParam.hasValue()) {
            return new Result(false, "board has no value!");
        }
        int boardFields = this.currentGame.getCurrentGameSettings().getBoardSize()
                * this.currentGame.getCurrentGameSettings().getBoardSize();
        if (this.boardParam.getValue().size() != boardFields) {
            String message = "the input has to be ";
            message += boardFields;
            message += " characters long, " + this.boardParam.getValue().size() + " were given!";
            return new Result(false, message);
        }
        this.currentGame.getBoard().
                configureBoard(this.boardParam.getValue(), getShipList(this.boardParam.getShipID()));
        if (!this.currentGame.getBoard().validateSymmetrical()) {
            return new Result(false, "the configured board is not mirrored!");
        }
        if (!this.currentGame.getBoard().validateIntegrity()) {
            return new Result(false, "a tile is surrounded by cover!");
        }
        return new Result(true);
    }


    private List<Spaceship> getShipList(List<Character> idList) {
        List<Spaceship> returnList = new ArrayList<>();
        for (char c : idList) {
            returnList.add(this.currentGame.getSpaceship(c));
        }
        return returnList;
    }
}
