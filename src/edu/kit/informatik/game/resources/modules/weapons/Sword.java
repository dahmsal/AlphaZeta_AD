package edu.kit.informatik.game.resources.modules.weapons;

import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.logic.actions.attack.Strike;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;

import java.util.List;

/**
 * The sword is a close range weapon module, a ship can have multiple swords
 * @author uppyo
 * @version 1.0
 */
public class Sword extends Module {
    private static final String NAME = "SWORD";
    private static final ModuleType TYPE = ModuleType.WEAPON;

    public Sword() {
        super();
        List<Action> actions = List.of(new Strike());
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
