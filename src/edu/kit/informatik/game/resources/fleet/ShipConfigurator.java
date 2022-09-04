package edu.kit.informatik.game.resources.fleet;

import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.ModuleType;
import edu.kit.informatik.util.GameParam;
import edu.kit.informatik.util.exception.ParameterException;

/**
 * Util-class to configure a ship by adding modules.
 * @author uppyo
 * @version 1.0
 */
public final class ShipConfigurator {

    private ShipConfigurator() { }

    /**
     * Attempt to add a module to a ship, check all rules associated with the module
     * @param fleet current fleet, is needed for fleet-exclusive modules
     * @param id identifying char of a spaceship
     * @param module new module, which is to be added to the spaceship
     * @throws ParameterException if any rule was violated
     */
    public static void addModule(Fleet fleet, char id, Module module) throws ParameterException {

        Battleship battleship = fleet.getBattleship(id);
        for (ModuleType moduleType : module.getTypes()) {
            switch (moduleType) {
                case WEAPON:
                    if (battleship.getWeaponCount() == GameParam.getMaxWeaponCount()) {
                        battleship.resetModules();
                        throw new ParameterException("Module: " + module
                                + " could not be added. The ship has no more weapon slots!");
                    }
                    break;
                case SHIP_UNIQUE:
                    if (battleship.moduleExists(module.toString())) {
                        battleship.resetModules();
                        throw new ParameterException("Module: " + module
                                + " can only be added once!");
                    }
                    break;
                case FLEET_UNIQUE:
                    for (Battleship shipsInFleet: fleet.getAllBattleships()) {
                        if (shipsInFleet.moduleExists(module.toString())) {
                            battleship.resetModules();
                            throw new ParameterException("Module: " + module
                                    + " already exist in the fleet!");
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        battleship.addModule(module);
    }
}
