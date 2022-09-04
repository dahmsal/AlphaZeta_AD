package edu.kit.informatik.game.logic.actions.attack;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.actions.misc.Target;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;

/**
 * Longshot is a ranged attack with a chance to destroy two modules.
 * @author uppyo
 * @version 1.0
 */
public class Longshot extends Attack {
    private static final String NAME = "LONGSHOT";
    private static final int MAX_RANGE = 6;
    private static final int MIN_RANGE = 5;
    private static final int HIT_THROW = 2;
    private static final int CRIT_THROW = 6;
    private static final int SPECIAL_HIT_THROW = 4;

    private final AttackProperties attackProperties;

    /**
     * Initialise a longshot-action by setting the required and optional attack-properties
     */
    public Longshot() {
        this.attackProperties = new AttackProperties(MIN_RANGE, MAX_RANGE, HIT_THROW);
        this.attackProperties.setCritThrow(CRIT_THROW);
        this.attackProperties.setSpecialHit(SPECIAL_HIT_THROW);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Result execute(AlphaZeta currentGame, Spaceship actor, Target target) {
        return super.processCommand(currentGame, actor, target, this.attackProperties);
    }

}
