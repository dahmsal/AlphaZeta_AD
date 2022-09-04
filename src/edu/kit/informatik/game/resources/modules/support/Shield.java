package edu.kit.informatik.game.resources.modules.support;

import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;

import java.util.List;

/**
 * Shield is a passive support module that reduces damage to ships
 * @author uppyo
 * @version 1.0
 */
public class Shield extends Module {
    private static final String NAME = "SHIELD";
    private static final ModuleType TYPE = ModuleType.SHIP_UNIQUE;

    /**
     * Initialise a new shield. A shield has no active actions thus the actions list is empty
     */
    public Shield() {
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
