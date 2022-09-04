package edu.kit.informatik.util.strings;

import java.util.List;

/**
 * Collection of useful pattern-generators
 * @author uppyo
 * @version 1.0
 */
public final class UtilPatterns {
    private UtilPatterns() { }

    /**
     * Get a regex-pattern matching a list of words
     * @param listOfWords list of String, words that should be matched
     * @return regex-pattern as String
     */
    public static String getMultipleWordPattern(List<String> listOfWords) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String module: listOfWords) {
            stringBuilder.append("\\b");
            stringBuilder.append(module);
            stringBuilder.append("\\b|");
        }
        stringBuilder.replace(stringBuilder.lastIndexOf("|"), stringBuilder.lastIndexOf("|") + 1,
                UtilStrings.getEmptyString());
        return stringBuilder.toString();
    }
}
