/*
 * Copyright (c) 2022, KASTEL. All rights reserved.
 */

package edu.kit.informatik.util.exception;

/**
 * An exception indicating that a user input is invalid.
 *
 * @author Peter Oettig
 * @author Moritz Gstuer
 * @author uppyo
 * @version 1.0
 */
public class InputException extends Exception {
    private static final long serialVersionUID = -8855047592046284760L;

    /**
     * Constructs a new instance of InputException.
     *
     * @param message The error message to display to the user.
     */
    public InputException(String message) {
        super(message);
    }
}