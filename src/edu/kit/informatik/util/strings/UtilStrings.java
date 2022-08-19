package edu.kit.informatik.util.strings;


/**
 * Collection of useful string elements
 * @author uppyo
 * @version 1.0
 */
public final class UtilStrings {
    private static final String WHITESPACE = " ";
    private static final String LINEBREAK = "\n";
    private static final String EMPTYSTRING = "";
    private static final String LBRACKET = "[";
    private static final String RBRACKET = "]";
    private static final String GLQQ = "(";
    private static final String GRQQ = ")";
    private static final String COMMA = ",";
    private static final String DOT = ".";
    private static final String DDOT = ":";
    private static final String SEMICOLON = ";";
    private static final String AND = "and";
    private static final String ANDSYMBOL = "&";

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
    public static String getDot() {
        return DOT;
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

    /**
     * pre-defined string element
     * @return string element
     */
    public static String getSemicolon() { return SEMICOLON; }

    /**
     * pre-defined string element
     * @return string element
     */
    public static String getAnd() {
        return AND;
    }

    /**
     * pre-defined string element
     * @return string element
     */
    public static String getAndSymbol() {
        return ANDSYMBOL;
    }

    /**
     * put brackets around number [i]
     * @param i given integer
     * @return string element
     */
    public static String getBracketInt(int i) {
        return LBRACKET + i + RBRACKET;
    }

    /**
     * put round brackets around number (i)
     * @param i given integer
     * @return string element
     */
    public static String getQQInt(int i) {
        return GLQQ + i + GRQQ;
    }

    /**
     * put a string in quotes "string"
     * @param n given string
     * @return string element
     */
    public static String inQuotes(String n) { return ("\"" + n + "\"" ); }


}
