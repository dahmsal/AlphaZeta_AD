package edu.kit.informatik.game.resources.modules;

import edu.kit.informatik.game.logic.actions.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract description of a module
 * @author uppyo
 * @version 1.0
 */
public abstract class Module {
    private  List<Action> actions = List.of();
    private static final String NAME = "default";

    protected void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Module() { }
    /**
     * Get the name of a module as a String
     * @return name as String
     */
    @Override
    public abstract String toString();
    /**
     * Get a list of all available actions of a module
     * @return actions as a string
     */
    public List<Action> getActiveActions() {
        List<Action> result = new ArrayList<>();
        for (Action action: this.actions) {
            if (action.isOnline()) {
                result.add(action);
            }
        }
        return result;
    }

    public void resetModule() {
        for (Action action: this.actions) {
            action.reset();
        }
    }

    /**
     * Get the types of a module, a module can have multiple rules when added
     * @return type as ModuleType field
     */
    public abstract List<ModuleType> getTypes();

}
