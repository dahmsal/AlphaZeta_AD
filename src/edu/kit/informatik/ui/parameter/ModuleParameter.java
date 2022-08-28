package edu.kit.informatik.ui.parameter;

import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.misc.Engine;
import edu.kit.informatik.game.resources.modules.support.Propulsion;
import edu.kit.informatik.game.resources.modules.support.Sensor;
import edu.kit.informatik.game.resources.modules.support.Shield;
import edu.kit.informatik.game.resources.modules.weapons.RailGun;
import edu.kit.informatik.game.resources.modules.weapons.Sword;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.List;
import java.util.stream.Collectors;

public class ModuleParameter implements Parameter<Module> {
    //ToDo: better dynamic solution for regex-pattern
    private static final List<String> MODULES = List.of("RAILGUN", "SWORD", "SHIELD", "SENSOR", "PROPULSION");

    private Module value;

    public ModuleParameter() {
        this.value = null;
    }

    @Override
    public String getPattern() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String module: MODULES) {
            stringBuilder.append("\\b");
            stringBuilder.append(module);
            stringBuilder.append("\\b|");
        }
        stringBuilder.replace(stringBuilder.lastIndexOf("|"), stringBuilder.lastIndexOf("|") + 1, UtilStrings.getEmptyString());
        return stringBuilder.toString();
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

    public static String getModules() {
        List<String> modules = MODULES;
        modules = modules.stream().sorted().collect(Collectors.toList());
        return modules.toString().replaceAll("\\[", UtilStrings.getEmptyString())
                .replaceAll("\\]", UtilStrings.getEmptyString());
    }


}
