package edu.kit.informatik.game.resources.modules.support;

import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;

import java.util.List;

/**
 * Sensor is a supportive module that is fleet-unique. Only one ship in a fleet can have a sensor.
 * @author uppyo
 * @version 1.0
 */
public class Sensor implements Module  {
    private static final String NAME = "SENSOR";
    private static final ModuleType TYPE = ModuleType.FLEET_UNIQUE;
    private static final List<String> ACTIONS = List.of("MARK");
    private boolean status;

    public Sensor() {
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
        return false;
    }

    public void activate() {
        this.status = false;
    }

    public void reset() {
        this.status = true;
    }
}
