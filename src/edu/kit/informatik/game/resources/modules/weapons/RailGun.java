package edu.kit.informatik.game.resources.modules.weapons;

import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;

import java.util.List;

/**
 * The rail gun module is a ranged weapon module, that is limited to one per ship.
 * @author uppyo
 * @version 1.0
 */
public class RailGun implements Module {
    private static final String NAME = "RAILGUN";
    private static final ModuleType TYPE_A = ModuleType.WEAPON;
    private static final ModuleType TYPE_B = ModuleType.SHIP_UNIQUE;
    private static final List<String> ACTIONS = List.of("LONGSHOT");
    private boolean status;

    public RailGun() {
        this.status = true;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<String> getActions() {
        return ACTIONS;
    }

    @Override
    public List<ModuleType> getTypes() {
        return List.of(TYPE_A, TYPE_B);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    public void activate() {
        this.status = false;
    }

    public void reset() {
        this.status = true;
    }

}
