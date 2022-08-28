package edu.kit.informatik.game.resources.modules.support;

import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;

import java.util.List;

/**
 * Shield is a passive support module that reduces damage to ships
 * @author uppyo
 * @version 1.0
 */
public class Shield implements Module {
    private static final String NAME = "SHIELD";
    private static final ModuleType TYPE = ModuleType.SHIP_UNIQUE;
    private static final List<String> ACTIONS = List.of();
    private boolean status;

    public Shield() {
        status = true;
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
