package edu.kit.informatik.util.strings;


/**
 * Collection of useful string elements
 * @author uppyo
 * @version 1.1
 */
public final class UtilStrings {
    private static final String WHITESPACE = " ";
    private static final String LINEBREAK = "\n";
    private static final String EMPTYSTRING = "";
    private static final String COMMA = ",";
    private static final String DDOT = ":";

    private UtilStrings() { }

    /**
     * pre-defined string element
     * @return string element
     */
    public static String getWhitespace() {
        return WHITESPACE;
    }

    /**
     * pre-defined string element
     * @return string element
     */
    public static String getLinebreak() {
        return LINEBREAK;
    }

    /**
     * pre-defined string element
     * @return string element
     */
    public static String getEmptyString() {
        return EMPTYSTRING;
    }


    /**
     * pre-defined string element
     * @return string element
     */
    public static String getDdot() { return DDOT; }

    /**
     * pre-defined string element
     * @return string element
     */
    public static String getComma() {
        return COMMA;
    }

}
