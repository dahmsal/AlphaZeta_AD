package edu.kit.informatik.game.resources.fleet;

import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.resources.modules.misc.Engine;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.support.Shield;
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
    private boolean isDestroyed;
    private boolean isMarked;

    /**
     * Initialize a spaceship with the necessary engine-module
     * @param id identifying character
     */
    public Spaceship(char id) {
        this.modules = new ArrayList<>();
        this.modules.add(new Engine());
        this.maxModuleCount = GameParam.getMaxModuleCount();
        this.id = id;
        this.isDestroyed = false;
        this.isMarked = false;
    }

    /**
     * Get all modules of a spaceship
     * @return modules as List of modules
     */
    public List<Module> getModules() {
        return modules;
    }

    /**
     * Check if a shield-module is installed on the ship
     * @return true, if the ship currently has a shield-module
     */
    public boolean hasShield() {
        return this.modules.stream().anyMatch(module -> module.getClass().equals(Shield.class));
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
        throw new ParameterException("Module: " + newModule.toString()
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
     * Attempt to destroy/remove a module of a given class from the ship
     * @param moduleType module-class to be destroyed
     * @throws ParameterException if the module could not be found on the ship
     * @return the destroyed module
     */
    public Module destroyModuleType(Class<? extends Module> moduleType) throws ParameterException {
        try {
            Module destroyedModule = this.modules.stream().filter(module -> module.getClass().equals(moduleType))
                    .findFirst().orElse(null);
            this.modules.remove(destroyedModule);
            return destroyedModule;
        } catch (NullPointerException e) {
            throw new ParameterException("Module: " + moduleType.getName() + " could not be found.");
        }
    }

    /**
     * Attempt to destroy/remove a module from the ship
     * @param module module to be destroyed
     * @throws ParameterException if the module could not be found on the ship
     * @return the destroyed module
     */
    public Module destroyModule(Module module) throws ParameterException {
        try {
            this.modules.remove(module);
            return module;
        } catch (NullPointerException e) {
            throw new ParameterException("Module: " + module.toString() + " could not be found.");
        }
    }

    /**
     * Get the identifier of the ship
     * @return id as character
     */
    public char getId() {
        return this.id;
    }

    /**
     * Get the current position of the ship
     * @return position of the ship as vector2D
     */
    public Vector2D getPosition() {
        return this.position;
    }

    /**
     * Set the ships position
     * @param newPosition new position as vector2D object
     */
    public void setPosition(Vector2D newPosition) {
        this.position = newPosition;
    }

    /**
     * Check if the ship was destroyed
     * @return status of the isDestroyed flag as boolean
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Set the isDestroyed flag to indicate that the ship is destroyed
     */
    public void destroy() {
        this.isDestroyed = true;
    }

    /**
     * List all ship actions
     * @return List of all actions as Action Objects
     */
    public List<Action> shipActions() {
        List<Action> actions = new ArrayList<>();
        for (Module module: this.getModules()) {
            actions.addAll(module.getActiveActions());
        }
        return actions;
    }

    /**
     * Reset all modules and remove marking. Used to reset the ships status at the beginning of a round.
     */
    public void rearmShip() {
        for (Module module: this.modules) {
            module.resetModule();
        }
        this.isMarked = false;
    }

    /**
     * Mark a spaceship, the mark-action can mark ships for additional effects on-attack
     */
    public void markShip() {
        this.isMarked = true;
    }

    /**
     * Check if the ship is currently marked
     * @return true if the ship was marked
     */
    public boolean isMarked() {
        return isMarked;
    }

    /**
     * String-representation of the ship
     * @return identifier as String
     */
    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
