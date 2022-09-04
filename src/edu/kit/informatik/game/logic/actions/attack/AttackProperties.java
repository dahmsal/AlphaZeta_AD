package edu.kit.informatik.game.logic.actions.attack;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.logic.actions.misc.Decider;
import edu.kit.informatik.game.resources.fleet.Spaceship;

/**
 * Describes the properties of an attack. Required properties are min- and max-range and the required throw for a hit
 * @author uppyo
 * @version 1.0
 */
public class AttackProperties {
    private final Integer minRange;
    private final Integer maxRange;
    private final Integer hitThrow;
    private Integer specialHit;
    private Integer critThrow;
    private Integer critMissThrow;

    /**
     * Initialise the attack-properties using the required properties of range and hit-throw. A dice-throw with bonus
     * has to surpass the hit-throw property to hit a target.
     * @param minRange minimum range of an attack as int
     * @param maxRange maximum range of an attack as int
     * @param hitThrow required throw for hits
     */
    public AttackProperties(int minRange, int maxRange, int hitThrow) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.hitThrow = hitThrow;
    }

    /**
     * Set an additional crit-throw property. A critical hit enables the attacker to decide the destroyed modules.
     * @param critThrow required throw for a critical hit
     */
    public void setCritThrow(int critThrow) {
        this.critThrow = critThrow;
    }

    /**
     * Set an additional special-hit property. A special hit enables the destruction of two modules.
     * @param specialHit required throw for a special hit
     */
    public void setSpecialHit(int specialHit) {
        this.specialHit = specialHit;
    }

    /**
     * Set an additional critical miss property. A critical miss damages the attacking ship instead of the intended
     * target.
     * @param critMissThrow required throw for a critical miss
     */
    public void setCritMissThrow(int critMissThrow) {
        this.critMissThrow = critMissThrow;
    }

    /**
     * Create a decider object based on the properties of the attack. This includes setting the deciding player, the
     * damage and the damaged ship.
     * @param diceThrow the dice-throw for the attack as int
     * @param actor the acting spaceship that attempts an attack
     * @param target the targeted spaceship
     * @param currentGame the game that is currently played
     * @return a decider-type object for further processing of the attack
     */
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

    /**
     * Get the minimum range of an attack
     * @return range as int
     */
    public Integer getMinRange() {
        return minRange;
    }

    /**
     * Get the maximum range of an attack
     * @return range as int
     */
    public Integer getMaxRange() {
        return maxRange;
    }
}
