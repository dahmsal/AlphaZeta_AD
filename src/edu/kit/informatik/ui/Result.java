package edu.kit.informatik.ui;

/**
 * Result of a command-execution
 * Optional result message
 * @author uppyo
 * @version 1.0
 */
public class Result {

    private final boolean success;
    private final String resultMessage;

    /**
     * Result of an execution with no message
     * @param success boolean is the execution a success
     */
    public Result(final boolean success) {
        this.success = success;
        this.resultMessage = null;
    }

    /**
     * Result with a message
     * @param success boolean is the execution a success
     * @param resultMessage optional resultmessage
     */
    public Result(final boolean success, final String resultMessage) {
        this.success = success;
        this.resultMessage = resultMessage;
    }

    /**
     * Was the execution successful ?
     * @return true if result is a success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Check if the result has a message
     * @return true if result has a message
     */
    public boolean hasMessage() { return resultMessage != null; }

    /**
     * Some executions may have a result-message
     * @return result-message as a string
     */
    public String getResultMessage() {
        return resultMessage;
    }
}
