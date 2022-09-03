package edu.kit.informatik.game.resources.modules.support;

import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.logic.actions.Propel;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;
import edu.kit.informatik.util.math.Vector2D;

import java.util.List;

/**
 * A propulsion module enables additional mobility for ships.
 * @author uppyo
 * @version 1.0
 */
public class Propulsion extends Module {
    private static final String NAME = "PROPULSION";
    private static final ModuleType TYPE = ModuleType.SHIP_UNIQUE;

    public Propulsion() {
        super();
        List<Action> actions = List.of(new Propel());
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
