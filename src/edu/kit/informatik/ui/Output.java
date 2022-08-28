package edu.kit.informatik.ui;

public class Output {

    public static void printMessage(String message) {
        if (message != null) {
            System.out.println(message);
        }
    }

    public static void printError(String message) {
        System.out.println("Error, " + message);
    }

    public static void printResult(Result result) {
        if (result.hasMessage()) {
            if (result.isSuccess()) {
                printMessage(result.getResultMessage());
            } else {
                printError(result.getResultMessage());
            }
        }
    }

}
