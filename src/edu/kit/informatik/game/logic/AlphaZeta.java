package edu.kit.informatik.game.logic;

import edu.kit.informatik.game.resources.board.Board;
import edu.kit.informatik.game.resources.fleet.Fleet;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Output;
import edu.kit.informatik.util.GameSettings;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.strings.StringComposer;

import java.util.ArrayList;
import java.util.List;

public class AlphaZeta {
    private final List<Player> players;
    private final Board board;
    private final GameSettings currentGameSettings;

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

    public Spaceship getSpaceship(char id) {
        List<Spaceship> allShips = new ArrayList<>();
        for (Player player: this.players) {
            allShips.addAll(player.getFleet().getAllBattleships());
            allShips.add(player.getFleet().getCollector());
        }
        return allShips.stream().filter(spaceship -> spaceship.getId() == id).findFirst().orElse(null);
    }

    public Player getPlayerByShip(Spaceship spaceship) {
        for (Player player: this.players) {
            if (player.getFleet().getAllSpaceships().contains(spaceship)) {
                return player;
            }
        }
        return null;
    }

    public boolean gameOver() {
        for (Player player: this.players) {
            if (player.getFleet().getCollector().isDestroyed()) {
                return true;
            }
        }
        return false;
    }

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

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Fleet getFleet(Player player) {
        return player.getFleet();
    }

    public GameSettings getCurrentGameSettings() {
        return currentGameSettings;
    }

    public long getSeed() {
        return currentGameSettings.getSeed();
    }
}
