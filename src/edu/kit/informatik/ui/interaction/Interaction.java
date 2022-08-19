package edu.kit.informatik.ui.interaction;

import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.ui.session.Result;

import java.util.List;

/**
 * Interaction with the user are characterised through parameters, that can generate the desired data.
 * A interaction has a list of parameters and a some execution method that returns a result.
 * @author uppyo
 * @version 1.0
 */
public interface Interaction {
    /**
     * If the interaction has a custom message, it can be accessed through this method. Set to null if no message is
     * needed.
     * @return a message for the user, as a String
     */
    String getMessage();

    /**
     * get a list of parameters
     * @return parameters in correct order, is empty if the command has no parameters
     */
    List<Parameter<?>> getParameters();

    /**
     * Execute the interaction and return the result of the execution
     * @param parameters parameters with data
     * @return result of execution
     */
    Result exec(List<Parameter<?>> parameters);
}
