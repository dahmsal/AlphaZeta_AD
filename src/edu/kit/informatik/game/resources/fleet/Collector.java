package edu.kit.informatik.game.resources.fleet;

import edu.kit.informatik.game.resources.modules.misc.Container;
import edu.kit.informatik.util.exception.ParameterException;

/**
 * A collector only uses containers as modules, the number of containers is defined in the game-settings.
 * @author uppyo
 * @version 1.0
 */
public class Collector extends Spaceship {

    /**
     * Initialise a collector with a specified number of containers
     * @param numberOfContainers containers to be added, cannot exceed the maximum module count
     * @param id identifying character
     * @throws ParameterException if the maximum module-count was exceeded
     */
    public Collector(int numberOfContainers, char id) throws ParameterException {
        super(id);
        for (int i = 0; i < numberOfContainers; i++) {
            this.addModule(new Container());
        }
    }
}
