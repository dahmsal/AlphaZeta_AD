package edu.kit.informatik.game.logic.actions;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.actions.misc.Target;
import edu.kit.informatik.game.resources.board.Tile;
import edu.kit.informatik.game.resources.board.TileTypes;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.math.Vector2D;

public class Move extends Action {
    public static final String NAME = "MOVE";
    private static final int RANGE = 3;

    public Move() { }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Result execute(AlphaZeta currentGame, Spaceship actor, Target targetObj) {
        //check command validity
        Vector2D target = targetObj.getVectorTarget();
        if (target == null) {
            return new Result(false, "target position was not set");
        }
        Tile targetTile = currentGame.getBoard().getTile(target);
        if (targetTile == null || !targetTile.getTileType().equals(TileTypes.FREE)) {
            return new Result(false, "cannot move to tile, invalid target!");
        }
        Tile shipTile;
        try {
            shipTile = currentGame.getBoard().findSpaceship(actor);
        } catch (ParameterException e) {
            return new Result(false, e.getMessage());
        }
        if (Vector2D.manhattanDistance(shipTile.getPosition(), targetTile.getPosition()) > RANGE) {
            return new Result(false, "cant reach target, the distance"
                    + Vector2D.manhattanDistance(shipTile.getPosition(), targetTile.getPosition()) + "is too high!");
        }
        //execute the command
        shipTile.setTileType(TileTypes.FREE);
        shipTile.removeSpaceship();
        targetTile.setTileType(TileTypes.SPACESHIP);
        targetTile.setSpaceship(actor);
        super.activate();
        return new Result(true);
    }
}
