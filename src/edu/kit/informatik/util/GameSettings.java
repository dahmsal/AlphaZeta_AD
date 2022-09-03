package edu.kit.informatik.util;

import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.logic.Dice;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.math.Math;

import java.util.List;

public class GameSettings {
    private final int boardSize;
    private final long seed;
    private final int containers;
    private final Dice dice;

    public GameSettings(String[] args) throws ParameterException {
        try {
            this.seed = Long.parseLong(args[0]);
            this.boardSize = Integer.parseInt(args[1]);
            this.containers = Integer.parseInt(args[2]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new ParameterException("wrong inline arguments!");
        }
        if (!Math.isOdd(boardSize) || containers > 3) {
            throw new ParameterException("wrong inline arguments!");
        }
        this.dice = new Dice(this.seed);
    }

    public int getBoardSize() {
        return boardSize;
    }

    public long getSeed() {
        return seed;
    }

    public int getContainers() {
        return containers;
    }

    private Player playerAlpha() throws ParameterException {
        return new Player("Alpha", 'A', 'A', this.containers);
    }

    private Player playerZeta() throws ParameterException {
        return new Player("Zeta", 'W', 'Z', this.containers);
    }

    public List<Player> getPlayers() throws  ParameterException {
        return List.of(playerAlpha(), playerZeta());
    }

    public Dice getDice() { return  this.dice; }
}
