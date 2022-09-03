package edu.kit.informatik.game.logic.actions.misc;

import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.resources.fleet.Spaceship;

public class Decider {
    private final Player decidingPlayer;
    private final int moduleCount;
    private final Spaceship targetShip;

    public Decider(Player player, int moduleCount, Spaceship spaceship) {
        this.decidingPlayer = player;
        this.moduleCount = moduleCount;
        this.targetShip = spaceship;
    }

    public Player getDecidingPlayer() {
        return decidingPlayer;
    }

    public int getModuleCount() {
        return moduleCount;
    }

    public Spaceship getTargetShip() {
        return targetShip;
    }
}
