package edu.kit.informatik.game.resources.modules.misc;

import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;

import java.util.ArrayList;
import java.util.List;

/**
 * Engine is a mandatory module for every ship. The action ram can be enabled for additional functionality.
 * @author uppyo
 * @version 1.0
 */
public class Engine implements Module {
    private static final String NAME = "ENGINE";
    private static final ModuleType TYPE = ModuleType.MISC;
    private final List<String> actions = new ArrayList<>();
    private boolean status;

    /**
     * Add the move action to the actions list
     */
    public Engine() {
        this.actions.add("MOVE");
        enableRam();
        this.status = true;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<String> getActions() {
        return actions;
    }

    @Override
    public List<ModuleType> getTypes() {
        return List.of(TYPE);
    }

    /**
     * Enable the ram action for a engine-module
     */
    public void enableRam() {
        this.actions.add("RAM");
    }

    @Override
    public boolean isActive() {
        return this.status;
    }

    public void activate() {
        this.status = false;
    }

    public void reset() {
        this.status = true;
    }

}
