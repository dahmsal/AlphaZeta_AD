package edu.kit.informatik.game.resources.fleet;

import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.resources.modules.misc.Engine;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.support.Shield;
import edu.kit.informatik.util.GameParam;
import edu.kit.informatik.util.math.Vector2D;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.strings.StringComposer;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * Attempt to destroy/remove a module from the ship
     * @param moduleType module-type to be destroyed
     * @throws ParameterException if the module could not be found on the ship
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

    public Vector2D getPosition() {
        return this.position;
    }

    public void setPosition(Vector2D newPosition) {
        this.position = newPosition;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void destroy() {
        this.isDestroyed = true;
    }

    public String printModules(boolean includeEngine) {
        return StringComposer.listToString(this.modules);
    }

    public List<String> shipActionsToString() {
        List<String> actions = new ArrayList<>();
        for (Module module: this.getModules()) {
            actions.addAll(module.getActiveActions().stream().map(Action::getName).collect(Collectors.toList()));
        }
        return actions.stream().sorted().collect(Collectors.toList());
    }

    public List<Action> shipActions() {
        List<Action> actions = new ArrayList<>();
        for (Module module: this.getModules()) {
            actions.addAll(module.getActiveActions());
        }
        return actions;
    }

    public void rearmShip() {
        for (Module module: this.modules) {
            module.resetModule();
        }
        this.isMarked = false;
    }

    public void markShip() {
        this.isMarked = true;
    }

    public boolean isMarked() {
        return isMarked;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
