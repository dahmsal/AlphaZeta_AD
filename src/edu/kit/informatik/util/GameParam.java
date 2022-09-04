package edu.kit.informatik.util;

import java.util.List;

/**
 * Collection of fixed game-parameters. If a module or action should be accessible by the user, it has to be registered
 * here.
 * @author uppyo
 * @version 1.0
 */
public  final class GameParam {

    private static final int MAX_MODULE_COUNT = 4;
    private static final int MAX_WEAPON_COUNT = 2;
    private static final int FLEET_SIZE = 4;


    private GameParam() { }

    /**
     * Get the number of modules that is allowed per ship, default is 4
     * @return number of modules as int
     */
    public static int getMaxModuleCount() {
        return MAX_MODULE_COUNT;
    }

    /**
     * Get the number of weapon-type modules that is allowed per ship, default is 2
     * @return number of modules as int
     */
    public static int getMaxWeaponCount() {
        return MAX_WEAPON_COUNT;
    }

    /**
     * Get the fleet-size of the game, default is 4 ships per player
     * @return fleet-size as int
     */
    public static int getFleetSize() {
        return FLEET_SIZE;
    }

    /**
     * List of all accessible modules
     * @return String list of module-names
     */
    public static List<String> gameModules() {
        return List.of("RAILGUN", "SWORD", "SHIELD", "SENSOR", "PROPULSION");
    }

    /**
     * List of all accessible actions
     * @return Sting list of action-names
     */
    public static List<String> gameActions() {
        return List.of("MOVE", "PROPEL", "STRIKE", "MARK", "RAMM", "LONGSHOT");
    }
}
