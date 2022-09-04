package edu.kit.informatik.ui.session;

import edu.kit.informatik.ui.Result;


/**
 * A Dialog handles user-interaction in different phases of the game. It enables repeated calls of interactions until
 * the endDialog method is called. An initial message can be given, that is only displayed once when starting the dialog
 * @author uppyo
 * @version 1.0
 */
public abstract class Dialog {
    private boolean isRunning;

    /**
     * Initialise the dialog by setting the isRunning flag
     */
    public Dialog() {
        this.isRunning = true;
    }

    /**
     * Get the initial-message of a dialog. This message will only displayed once during the dialog.
     * @return String initial-message
     */
    public abstract String getInitialMessage();

    /**
     * Get the dialog-message. This message will be displayed before awaiting a user input at every dialog step
     * @return String dialog message
     */
    public abstract String getDialogMessage();

    /**
     * Execute the dialog-step
     * @param userInput user-input which will be passed on, to the interaction
     * @return a Result, if unsuccessful the result will be bundled with a error-message.
     */
    public abstract Result executeStep(String userInput);

    /**
     * Returns the state of the isRunning flag
     * @return true if the interaction is running
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * End the dialog by setting the isRunning flag to false
     */
    public void endDialog() {
        this.isRunning = false;
    }
}
