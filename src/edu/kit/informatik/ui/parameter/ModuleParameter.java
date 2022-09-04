package edu.kit.informatik.ui.parameter;

import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.support.Propulsion;
import edu.kit.informatik.game.resources.modules.support.Sensor;
import edu.kit.informatik.game.resources.modules.support.Shield;
import edu.kit.informatik.game.resources.modules.weapons.RailGun;
import edu.kit.informatik.game.resources.modules.weapons.Sword;
import edu.kit.informatik.util.GameParam;
import edu.kit.informatik.util.strings.UtilPatterns;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The module-parameter creates a new module based on user-input. If new modules are added, the pattern has to be added
 * here as well to enable parsing of the module.
 * @author uppyo
 * @version 1.0
 */
public class ModuleParameter implements Parameter<Module> {
    //ToDo: better dynamic solution for regex-pattern
    private static final List<String> MODULES = GameParam.gameModules();

    private Module value;

    @Override
    public String getPattern() {
        return UtilPatterns.getMultipleWordPattern(MODULES);
    }

    @Override
    public void clearParameter() {
        this.value = null;
    }

    @Override
    public void setValue(String moduleString) {
        String moduleName = moduleString.toUpperCase();
        switch (moduleName) {
            case "RAILGUN":
                this.value = new RailGun();
                break;
            case "SWORD":
                this.value = new Sword();
                break;
            case "SHIELD":
                this.value = new Shield();
                break;
            case "SENSOR":
                this.value = new Sensor();
                break;
            case "PROPULSION":
                this.value = new Propulsion();
                break;
            default:
                this.value = null;
        }

    }

    @Override
    public Module getValue() {
        return this.value;
    }

    @Override
    public boolean hasValue() {
        return this.value != null;
    }

    /**
     * Returns a string-representation of all module-parameters included in the GameParams
     * @return String listing of all registered modules
     */
    public static String getModules() {
        List<String> modules = MODULES;
        modules = modules.stream().sorted().collect(Collectors.toList());
        return modules.toString().replaceAll("\\[", UtilStrings.getEmptyString())
                .replaceAll("]", UtilStrings.getEmptyString());
    }


}
