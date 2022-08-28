package edu.kit.informatik.game.resources.modules;

import java.util.List;

/**
 * Abstract description of a module
 * @author uppyo
 * @version 1.0
 */
public interface Module {

    /**
     * Get the name of a module as a String
     * @return name as String
     */
    String getName();

    /**
     * Get a list of all available actions of a module
     * @return actions as a string
     */
    List<String> getActions();

    /**
     * Get the types of a module, a module can have multiple rules when added
     * @return type as ModuleType field
     */
    List<ModuleType> getTypes();

    boolean isActive();
}
