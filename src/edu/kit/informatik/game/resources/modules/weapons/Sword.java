package edu.kit.informatik.game.resources.modules.weapons;

import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;

import java.util.List;

/**
 * The sword is a close range weapon module, a ship can have multiple swords
 * @author uppyo
 * @version 1.0
 */
public class Sword implements Module {
    private static final String NAME = "SWORD";
    private static final ModuleType TYPE = ModuleType.WEAPON;
    private static final List<String> ACTIONS = List.of("STRIKE");
    private boolean status;

    public Sword() {
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
        return List.of(TYPE);
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
