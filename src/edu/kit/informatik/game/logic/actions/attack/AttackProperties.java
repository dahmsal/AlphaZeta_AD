package edu.kit.informatik.game.logic.actions.attack;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.logic.actions.misc.Decider;
import edu.kit.informatik.game.resources.fleet.Spaceship;

public class AttackProperties {
    private final Integer minRange;
    private final Integer maxRange;
    private final Integer hitThrow;
    private Integer specialHit;
    private Integer critThrow;
    private Integer critMissThrow;


    public AttackProperties(int minRange, int maxRange, int hitThrow) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.hitThrow = hitThrow;
    }

    public void setCritThrow(int critThrow) {
        this.critThrow = critThrow;
    }

    public void setSpecialHit(int specialHit) {
        this.specialHit = specialHit;
    }

    public void setCritMissThrow(int critMissThrow) {
        this.critMissThrow = critMissThrow;
    }

    public Decider createDecider(int diceThrow, Spaceship actor, Spaceship target, AlphaZeta currentGame) {
        int value = diceThrow;
        int damage = 1;
        Player actorPlayer = currentGame.getPlayerByShip(actor);
        Player targetPlayer = currentGame.getPlayerByShip(target);
        value += Attack.calculateBonus(actor, target, currentGame);
        //critical miss: Actor gets 1 damage
        if (this.critMissThrow != null && value <= this.critMissThrow) {
            return new Decider(actorPlayer, damage, actor);
        }
        //miss: O damage is dealt
        if (value < this.hitThrow) {
            return new Decider(targetPlayer, 0, target);
        }
        //special hit: damage is increased to 2
        if (this.specialHit != null && value >= this.specialHit) {
            damage++;
        }
        //crit: actor gets to choose
        if ((this.critThrow != null && value >= this.critThrow) || target.isMarked()) {
            return new Decider(actorPlayer, damage, target);
        }
        //normal hit
        return new Decider(targetPlayer, damage, target);
    }

    public Integer getMinRange() {
        return minRange;
    }

    public Integer getMaxRange() {
        return maxRange;
    }
}
