package edu.kit.informatik.game.resources.modules.support;

import edu.kit.informatik.game.logic.actions.Action;
import edu.kit.informatik.game.logic.actions.Mark;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;

import java.util.List;

/**
 * Sensor is a supportive module that is fleet-unique. Only one ship in a fleet can have a sensor.
 * @author uppyo
 * @version 1.0
 */
public class Sensor extends Module  {
    private static final String NAME = "SENSOR";
    private static final ModuleType TYPE = ModuleType.FLEET_UNIQUE;

    public Sensor() {
        super();
        List<Action> actions = List.of(new Mark());
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
