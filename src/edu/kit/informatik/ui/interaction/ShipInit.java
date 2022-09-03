package edu.kit.informatik.ui.interaction;

import edu.kit.informatik.game.resources.fleet.Battleship;
import edu.kit.informatik.game.resources.fleet.Fleet;
import edu.kit.informatik.game.resources.fleet.ShipConfigurator;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.ModuleParameter;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.List;

/**
 * Ship initialisation
 */
public class ShipInit extends Interaction {
    private static final String MESSAGE = "Choose upto 3 Modules for Ship PLACEHOLDER (seperated by comma):";
    private final Fleet fleet;
    private final char shipID;
    private final  List<Parameter<?>> parameters;

    public ShipInit(Fleet fleet, char id) {
        super();
        this.fleet = fleet;
        this.shipID = id;
        this.parameters = List.of(new ModuleParameter(), new ModuleParameter(), new ModuleParameter());
    }


    @Override
    public String getMessage() {
        return MESSAGE.replaceAll("PLACEHOLDER", String.valueOf(shipID)) + UtilStrings.getLinebreak()
                + ModuleParameter.getModules();
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
