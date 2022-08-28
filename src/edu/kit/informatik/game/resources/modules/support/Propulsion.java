package edu.kit.informatik.game.resources.modules.support;

import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;

import java.util.List;

/**
 * A propulsion module enables additional mobility for ships.
 * @author uppyo
 * @version 1.0
 */
public class Propulsion implements Module {
    private static final String NAME = "PROPULSION";
    private static final ModuleType TYPE = ModuleType.SHIP_UNIQUE;
    private static final List<String> ACTIONS = List.of("PROPEL");
    public boolean status;

    public Propulsion() {
        status = true;
    }

    public boolean isActive() {
        return status;
    }

    public void activate() {
        this.status = false;
    }

    public void reset() {
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
}
