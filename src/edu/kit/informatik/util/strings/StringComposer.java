package edu.kit.informatik.util.strings;

import edu.kit.informatik.game.resources.modules.Module;

import java.util.List;
import java.util.stream.Collectors;

public class StringComposer {
    private StringComposer() { }

    public static String listToString(List<?> list) {
        if (list.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            List<String> sortedStrings = list.stream().map(Object::toString).sorted().collect(Collectors.toList());
            for (String module : sortedStrings) {
                stringBuilder.append(module).append(", ");
            }
            int lastComma = stringBuilder.lastIndexOf(",");
            stringBuilder.replace(lastComma, lastComma + 1, UtilStrings.getEmptyString());
            return stringBuilder.toString().trim();
        }
        else return null;
    }

    public static String destroyModuleOutput(List<Module> modules, char shipID) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Module module: modules) {
            stringBuilder.append(shipID).append(UtilStrings.getWhitespace());
            stringBuilder.append("looses").append(UtilStrings.getWhitespace());
            stringBuilder.append(module.toString()).append(UtilStrings.getLinebreak());
        }
        return stringBuilder.toString().trim();
    }

}
