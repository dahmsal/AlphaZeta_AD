package edu.kit.informatik.util;

import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.logic.Dice;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.math.Math;

import java.util.List;

/**
 * Collection of settings for a game. These usually are set using the CLI-Arguments of the program.
 * @author uppyo
 * @version 1.0
 */
public class GameSettings {
    private final int boardSize;
    private final int containers;
    private final Dice dice;

    /**
     * Initialise the games-settings: seed, board-size and container-count
     * @param args CLI-args as string-array
     * @throws ParameterException if any game-setting was faulty
     */
    public GameSettings(String[] args) throws ParameterException {
        long seed;
        try {
            seed = Long.parseLong(args[0]);
            this.boardSize = Integer.parseInt(args[1]);
            this.containers = Integer.parseInt(args[2]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ParameterException("wrong inline arguments!");
        }
        if (!Math.isOdd(this.boardSize) || this.containers > GameParam.getMaxModuleCount() - 1) {
            throw new ParameterException("wrong inline arguments!");
        }
        this.dice = new Dice(seed);
    }

    /**
     * Get the configured board-size (side-length)
     * @return side-length of the board as int
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * List of all active players (by default Alpha and Zeta)
     * @return List of players
     * @throws ParameterException if a player is incorrectly initialised
     */
    public List<Player> getPlayers() throws  ParameterException {
        return List.of(playerAlpha(), playerZeta());
    }

    /**
     * Get the dice of the game, generated using the given seed
     * @return the current Dice-Object
     */
    public Dice getDice() { return  this.dice; }

    //description of the players
    private Player playerAlpha() throws ParameterException {
        return new Player("Alpha", 'A', 'A', this.containers);
    }

    private Player playerZeta() throws ParameterException {
        return new Player("Zeta", 'W', 'Z', this.containers);
    }

}
