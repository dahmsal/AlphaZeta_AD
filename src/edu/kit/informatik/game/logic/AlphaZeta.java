package edu.kit.informatik.game.logic;

import edu.kit.informatik.game.resources.board.Board;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Output;
import edu.kit.informatik.util.GameSettings;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.strings.StringComposer;

import java.util.ArrayList;
import java.util.List;

/**
 * AlphaZeta describes the game by collecting and organising the board, players and current game-settings.
 * @author uppyo
 * @version 1.0
 */
public class AlphaZeta {
    private final List<Player> players;
    private final Board board;
    private final GameSettings currentGameSettings;

    /**
     * Initialise a game using provided game-settings. Here the board and players are initially created.
     * @param gameSettings game-settings for the game, usually set using cli-arguments
     */
    public AlphaZeta(GameSettings gameSettings) {
        this.currentGameSettings = gameSettings;
        this.players = new ArrayList<>();
        this.board = new Board(currentGameSettings.getBoardSize());
        try {
            this.players.addAll(currentGameSettings.getPlayers());
        } catch (ParameterException e) {
            Output.printError(e.getMessage());
        }

    }

    /**
     * Get a spaceship from the game, independent from player association.
     * @param id identifier of the ship as char
     * @return th corresponding spaceship, null if the ship could not be found
     */
    public Spaceship getSpaceship(char id) {
        List<Spaceship> allShips = new ArrayList<>();
        for (Player player: this.players) {
            allShips.addAll(player.getFleet().getAllBattleships());
            allShips.add(player.getFleet().getCollector());
        }
        return allShips.stream().filter(spaceship -> spaceship.getId() == id).findFirst().orElse(null);
    }

    /**
     * Get the player that has the given spaceship in its fleet
     * @param spaceship spaceship of desired player
     * @return corresponding player-object, or null if none could be found
     */
    public Player getPlayerByShip(Spaceship spaceship) {
        for (Player player: this.players) {
            if (player.getFleet().getAllSpaceships().contains(spaceship)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Check if the game is over. The game is over if any player looses a collector-spaceship.
     * @return true if the game is over
     */
    public boolean gameOver() {
        for (Player player: this.players) {
            if (player.getFleet().getCollector().isDestroyed()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the winning message of the game including the winning players
     * @return String representing the winners of the game
     */
    public String getWinner() {
        Player looser = null;
        for (Player player: this.players) {
            if (player.getFleet().getCollector().isDestroyed()) {
                looser = player;
            }
        }
        if (looser != null) {
            this.players.remove(looser);
            return StringComposer.listToString(this.players) + " won!";
        }
        return null;
    }

    /**
     * Get the board of the current game
     * @return boar-object on which the game is currently played
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Get a list of players that are playing the game (by default Alpha and Zeta)
     * @return List of player-objects
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Get the current game-settings of the game
     * @return game-settings bundled as GameSettings object
     */
    public GameSettings getCurrentGameSettings() {
        return currentGameSettings;
    }
}
