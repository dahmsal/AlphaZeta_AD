package edu.kit.informatik.game.resources.modules;

/**
 * Collection of module-types, describes the add-rules for modules
 * @author uppyo
 * @version 1.0
 */
public enum ModuleType {
    /**
     * Used for modules that are allowed once per ship
     */
    SHIP_UNIQUE,
    /**
     * Used modules that are allowed once per fleet
     */
    FLEET_UNIQUE,
    /**
     * Used for weapon modules, that have a limit per ship
     */
    WEAPON,
    /**
     * Used for modules with no special rules
     */
    MISC
}
