package edu.kit.informatik.game.resources.modules.misc;

import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;

import java.util.List;

/**
 * A container is a special module for the collector ships, it has no actions and returns empty actions list
 * @author uppyo
 * @version 1.0
 */
public class Container extends Module {
    private static final String NAME = "CONTAINER";
    private static final ModuleType TYPE = ModuleType.MISC;

    public Container() {
        super();
        List<Action> actions = List.of();
        setActions(actions);
    }

    @Override
    public String toString() {
        return NAME;
    }

    @Override
    public List<ModuleType> getTypes() {
        return List.of(TYPE);
    }

}
