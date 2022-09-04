package edu.kit.informatik.ui.interaction;

import edu.kit.informatik.game.logic.actions.misc.Decider;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.misc.Engine;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.parameter.ModuleParameter;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.strings.StringComposer;

import java.util.ArrayList;
import java.util.List;

/**
 * Interactive destruction of modules. This interaction determines if user input is required to select destroyed modules
 * and creates appropriate outputs for the user. The the destruction is processed. If the interaction was not successful
 * a negative Result is returned, with a conclusive message.
 * @author uppyo
 * @version 1.0
 */
public class DestroyModules extends Interaction {
    private final List<Module> relevantModules;
    private final Spaceship targetShip;
    private final List<Parameter<?>> parameters;
    private final int moduleCount;

    /**
     * Initialise the interaction using a decider-object. Decider-Objects are created when Attack-Actions are
     * successfully executed
     * @param decider decider-object detailing the destruction-process
     */
    public DestroyModules(Decider decider) {
        this.relevantModules = new ArrayList<>();
        this.relevantModules.addAll(decider.getTargetShip().getModules());
        this.relevantModules.removeIf(module -> module.getClass().equals(Engine.class));
        this.targetShip = decider.getTargetShip();
        this.moduleCount = decider.getModuleCount();
        this.parameters = new ArrayList<>();
        for (int i = 0; i < this.moduleCount; i++) {
            this.parameters.add(new ModuleParameter());
        }
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public List<Parameter<?>> getParameters() {
        return this.parameters;
    }

    @Override
    public Result execute() {
        List<Module> selectedModules = new ArrayList<>();
        //find selected modules on ship
        for (Parameter<?> parameter: this.parameters) {
            if (!parameter.hasValue()) {
                return new Result(false, "please enter " +  this.moduleCount + " valid module(s)!");
            }
            Module newModule;
            try {
                newModule = (Module) parameter.getValue();
            } catch (ClassCastException e) {
                return new Result(false, "could not parse parameter!");
            }
            Module foundModule = this.relevantModules.stream()
                    .filter(module -> module.toString().equals(newModule.toString())).findFirst().orElse(null);
            if (foundModule == null) {
                return new Result(false, "please select module(s) from list!");
            }
            selectedModules.add(foundModule);
        }
        //destroy selected modules
        for (Module module: selectedModules) {
            try {
                this.targetShip.destroyModule(module);
            } catch (ParameterException e) {
                return new Result(false, e.getMessage());
            }
        }
        return new Result(true, StringComposer.destroyModuleOutput(selectedModules, targetShip.getId()));
    }
}
