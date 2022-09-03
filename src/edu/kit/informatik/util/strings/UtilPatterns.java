package edu.kit.informatik.util.strings;

import java.util.List;

public class UtilPatterns {
    private UtilPatterns() { }

    public static String getMultipleWordPattern(List<String> listOfWords) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String module: listOfWords) {
            stringBuilder.append("\\b");
            stringBuilder.append(module);
            stringBuilder.append("\\b|");
        }
        stringBuilder.replace(stringBuilder.lastIndexOf("|"), stringBuilder.lastIndexOf("|") + 1, UtilStrings.getEmptyString());
        return stringBuilder.toString();
    }
}
