package edu.kit.informatik.game.logic.actions.attack;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.logic.actions.misc.Target;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;
import edu.kit.informatik.ui.Result;

public class Ramm extends Attack {
    private static final String NAME = "RAMM";
    private static final int MAX_RANGE = 1;
    private static final int MIN_RANGE = 1;
    private static final int HIT_THROW = 5;
    private static final int CRIT_MISS_THROW = 1;

    private final AttackProperties attackProperties;

    public Ramm() {
        this.attackProperties = new AttackProperties(MIN_RANGE, MAX_RANGE, HIT_THROW);
        this.attackProperties.setCritMissThrow(CRIT_MISS_THROW);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Result execute(AlphaZeta currentGame, Spaceship actor, Target target) {
        if (actor.getModules().stream().anyMatch(module -> module.getTypes().contains(ModuleType.WEAPON))) {
            return new Result(false, "ramm can only be used if no weapon modules exist on ship!");
        }
        return super.processCommand(currentGame, actor, target, attackProperties);
    }

}
