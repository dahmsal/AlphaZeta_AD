package edu.kit.informatik.game.resources.modules.misc;

import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.logic.actions.Move;
import edu.kit.informatik.game.logic.actions.attack.Ramm;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;

import java.util.List;

/**
 * Engine is a mandatory module for every ship. The action ram can be enabled for additional functionality.
 * @author uppyo
 * @version 1.0
 */
public class Engine extends Module {
    private static final String NAME = "ENGINE";
    private static final ModuleType TYPE = ModuleType.MISC;

    /**
     * Initialise an engine by adding a new move and ramm action to the actions list
     */
    public Engine() {
        super();
        List<Action> actions = List.of(new Move(), new Ramm());
        super.setActions(actions);
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
