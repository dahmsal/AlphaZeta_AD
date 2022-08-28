package edu.kit.informatik.game;

import edu.kit.informatik.game.resources.board.Board;
import edu.kit.informatik.game.resources.fleet.Fleet;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Output;
import edu.kit.informatik.util.GameSettings;
import edu.kit.informatik.util.exception.ParameterException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
