package edu.kit.informatik.game.resources.fleet;

import edu.kit.informatik.game.resources.modules.misc.Engine;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.util.GameParam;
import edu.kit.informatik.util.math.Vector2D;
import edu.kit.informatik.util.exception.ParameterException;

import java.util.ArrayList;
import java.util.List;

/**
 * Description of a space-ship piece. Every Spaceship has to have an engine module and a position indicated by a vector.
 * @author uppyo
 * @version 1.0
 */
public abstract class Spaceship {
    private final List<Module> modules;
    private Vector2D position;
    private final int maxModuleCount;
    private final char id;

    /**
     * Initialize a spaceship with the necessary engine-module
     * @param id identifying character
     */
    public Spaceship(char id) {
        this.modules = new ArrayList<>();
        this.modules.add(new Engine());
        this.maxModuleCount = GameParam.getMaxModuleCount();
        this.id = id;
    }

    /**
     * Get all modules of a spaceship
     * @return modules as List of modules
     */
    public List<Module> getModules() {
        return modules;
    }


    /**
     * Add a module to a ship ignoring additional rules
     * @param newModule module to be added
     * @throws ParameterException if the maximum module-count was exceeded
     */
    protected void addModule(Module newModule) throws ParameterException {
        if (this.modules.size() < this.maxModuleCount) {
            this.modules.add(newModule);
            return;
        }
        throw new ParameterException("Module: " + newModule.getName()
                + " could not be added, ship is at maximum modules!");
    }

    /**
     * Reset a ship to its default state (only engine module)
     */
    public void resetModules() {
        this.modules.clear();
        this.modules.add(new Engine());
    }

    /**
     * Attempt to destroy/remove a module from the ship
     * @param destroyedModule module to be destroyed
     * @throws ParameterException if the module could not be found on the ship
     */
    public void destroyModule(Module destroyedModule) throws ParameterException {
        try {
            this.modules.remove(destroyedModule);
        } catch (NullPointerException e) {
            throw new ParameterException("Module: " + destroyedModule.getName() + " could not be found.");
        }

    }

    /**
     * Get the identifier of the ship
     * @return id as character
     */
    public char getId() {
        return this.id;
    }

    public Vector2D getPosition() {
        return this.position;
    }

    public void setPosition(Vector2D newPosition) {
        this.position = newPosition;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
