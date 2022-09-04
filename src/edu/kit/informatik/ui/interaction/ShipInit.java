package edu.kit.informatik.ui.interaction;

import edu.kit.informatik.game.resources.fleet.Fleet;
import edu.kit.informatik.game.resources.fleet.ShipConfigurator;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.ModuleParameter;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.util.GameParam;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.ArrayList;
import java.util.List;

/**
 * Ship initialisation is handled through this interaction. It takes up to MAX_MODULE_COUNT - 1, since an engine
 * is mandatory one less module can be configured by the player, module-parameters and attempts to add them to a ship.
 * If this fails a negative result, including a conclusive message, is returned.
 * @author uppyo
 * @version 1.0
 */
public class ShipInit extends Interaction {
    private static final String MESSAGE = "Choose upto NUMBER Modules for Ship PLACEHOLDER (seperated by comma):";
    private final Fleet fleet;
    private final char shipID;
    private final  List<Parameter<?>> parameters;

    /**
     * Initialise the interaction using the fleet of the ship that is configured and the id of the ship
     * @param fleet fleet of ship that is to be configured
     * @param id identifier of the ship
     */
    public ShipInit(Fleet fleet, char id) {
        super();
        this.fleet = fleet;
        this.shipID = id;
        this.parameters = new ArrayList<>();
        for (int i = 1; i < GameParam.getMaxModuleCount(); i++) {
            this.parameters.add(new ModuleParameter());
        }

    }


    @Override
    public String getMessage() {
        return MESSAGE.replaceAll("PLACEHOLDER", String.valueOf(shipID))
                .replaceAll("NUMBER", String.valueOf(GameParam.getMaxModuleCount() - 1))
                + UtilStrings.getLinebreak() + ModuleParameter.getModules();
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return this.parameters;
    }

    @Override
    public Result execute() {
        for (Parameter<?> parameter: this.parameters) {
            if (parameter.hasValue()) {
                Module newModule;
                try {
                    newModule = (Module) parameter.getValue();
                } catch (ClassCastException e) {
                    return new Result(false, "could not parse parameter!");
                }
                try {
                    ShipConfigurator.addModule(this.fleet, this.shipID, newModule);
                } catch (ParameterException e) {
                    return new Result(false, e.getMessage());
                }
            }
        }
        return new Result(true);
    }

}
