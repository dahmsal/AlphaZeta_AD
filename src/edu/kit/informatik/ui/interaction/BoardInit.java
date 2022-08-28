package edu.kit.informatik.ui.interaction;

import edu.kit.informatik.game.AlphaZeta;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.BoardParameter;
import edu.kit.informatik.ui.parameter.Parameter;

import java.util.ArrayList;
import java.util.List;

public class BoardInit extends Interaction {
    private final BoardParameter boardParam;
    private final AlphaZeta currentGame;

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
        if (!boardParam.hasValue()) {
            return new Result(false, "board has no value!");
        }
        int boardFields = currentGame.getCurrentGameSettings().getBoardSize()
                * currentGame.getCurrentGameSettings().getBoardSize();
        if (boardParam.getValue().size() != boardFields) {
            String message = "the input has to be ";
            message += boardFields;
            message += " characters long, " + boardParam.getValue().size() + " were given!";
            return new Result(false, message);
        }
        currentGame.getBoard().configureBoard(boardParam.getValue(), getShipList(boardParam.getShipID()));
        if (!currentGame.getBoard().validateBoard()) {
            return new Result(false, "the configured board is not mirrored!");
        }
        return new Result(true, currentGame.getBoard().toString());
    }

    private List<Spaceship> getShipList(List<Character> idList) {
        List<Spaceship> returnList = new ArrayList<>();
        for (char c : idList) {
            returnList.add(currentGame.getSpaceship(c));
        }
        return returnList;
    }
}
