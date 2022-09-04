package edu.kit.informatik.game.resources.modules.weapons;

import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.logic.actions.attack.Longshot;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;

import java.util.List;

/**
 * The rail gun module is a ranged weapon module, that is limited to one per ship.
 * @author uppyo
 * @version 1.0
 */
public class RailGun extends Module {
    private static final String NAME = "RAILGUN";
    private static final ModuleType TYPE_A = ModuleType.WEAPON;
    private static final ModuleType TYPE_B = ModuleType.SHIP_UNIQUE;

    /**
     * Initialise a railgun by adding a new longshot action to the actions list
     */
    public RailGun() {
        super();
        List<Action> actions = List.of(new Longshot());
        super.setActions(actions);
    }

    @Override
    public String toString() {
        return NAME;
    }

    @Override
    public List<ModuleType> getTypes() {
        return List.of(TYPE_A, TYPE_B);
    }

}
