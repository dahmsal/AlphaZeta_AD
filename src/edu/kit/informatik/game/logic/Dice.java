package edu.kit.informatik.game.logic;

import java.util.Random;

/**
 * The dice use a random number generator to simulate a dice throw
 * @author uppyo
 * @version 1.0
 */
public class Dice {
    private final Random numberGenerator;

    /**
     * Initialise the number-generator
     * @param seed seed for the generator, to insure deterministic results
     */
    public Dice(long seed) {
        this.numberGenerator = new Random(seed);
    }

    /**
     * Throw the dice once (only use when necessary to insure deterministic results)
     * @return A pseudo-random number between 1 and 6 as int
     */
    public int throwDice() {
        return numberGenerator.nextInt(6) + 1;
    }
}
