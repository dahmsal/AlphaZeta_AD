package edu.kit.informatik.util.strings;

import edu.kit.informatik.game.resources.modules.Module;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Util-Class contains methods to create String-representations
 * @author uppyo
 * @version 1.0
 */
public final class StringComposer {
    private StringComposer() { }

    /**
     * Print a list to a string, seperated by commas
     * @param list input-list
     * @return a string of all list-elements toString representations
     */
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

    /**
     * Generate a destruction output message based on a given list of modules
     * @param modules destroyed modules
     * @param shipID name of the ship as char
     * @return destruction output-message
     */
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
