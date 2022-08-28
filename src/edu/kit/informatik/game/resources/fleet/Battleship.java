package edu.kit.informatik.game.resources.fleet;

import edu.kit.informatik.game.resources.modules.ModuleType;

/**
 * A battleship can be equipped with different modules. To aid the process some useful queries are implemented here.
 * @author uppyo
 * @version 1.0
 */
public class Battleship extends Spaceship  {

    /**
     * Initialise a spaceship using an identifier as char
     * @param id identifying char
     */
    public Battleship(char id) {
        super(id);
    }

    /**
     * Get the number of weapons that have been added to the battleship
     * @return the total count of WEAPON-type modules
     */
    public int getWeaponCount() {
        return (int) this.getModules().stream().filter(module -> module.getTypes().contains(ModuleType.WEAPON)).count();
    }

    /**
     * Check if a module with the same name has been added to the battleship
     * @param moduleName name of the module as String
     * @return true if moduleName matches any module previously added to the ship
     */
    public boolean moduleExists(String moduleName) {
        return this.getModules().stream().anyMatch(module -> module.getName().equals(moduleName));
    }
}
