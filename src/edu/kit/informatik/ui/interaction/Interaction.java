package edu.kit.informatik.ui.interaction;

import edu.kit.informatik.ui.Parsable;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.ui.Result;

import java.util.List;

/**
 * Interaction with the user are characterised through parameters, that can generate the desired data.
 * A interaction has a list of parameters and a some execution method that returns a result.
 * @author uppyo
 * @version 1.0
 */
public abstract class Interaction implements Parsable {
    /**
     * If the interaction has a custom message, it can be accessed through this method. Set to null if no message is
     * needed.
     * @return a message for the user, as a String
     */
    public abstract String getMessage();

    @Override
    public abstract List<Parameter<?>> getParameters();

    @Override
    public abstract Result execute();

}
