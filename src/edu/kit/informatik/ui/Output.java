package edu.kit.informatik.ui;

/**
 * Util-class, collection of methods to generate outputs and print them to the console
 * @author uppyo
 * @version 1.0
 */
public final class Output {

    private Output() { }

    /**
     * Attempt to print a string to the console. If the string is empty nothing is output.
     * @param message String message to be printed
     */
    public static void printMessage(String message) {
        if (message != null) {
            System.out.println(message);
        }
    }

    /**
     * Print an Error-message to the console
     * @param message error-message to be printed
     */
    public static void printError(String message) {
        System.out.println("Error, " + message);
    }

    /**
     * Print the message of a result, if a message is provided. If the result is unsuccessful an
     * error-message is printed
     * @param result Result-Object
     */
    public static void printResult(Result result) {
        if (result == null) {
            printError("no valid command was entered!");
            return;
        }
        if (result.hasMessage()) {
            if (result.isSuccess()) {
                printMessage(result.getResultMessage());
            } else {
                printError(result.getResultMessage());
            }
        }
    }


}
