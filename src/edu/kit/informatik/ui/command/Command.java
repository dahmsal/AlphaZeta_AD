package edu.kit.informatik.ui.command;


import java.util.List;

import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.ui.session.Result;
/**
 * Description of a command
 * A command must have a regex-pattern and (optional) parameters and an execution-method
 * @author uppyo
 * @version 1.1
 */
public abstract class Command {

    /**
     * get the regex-pattern of the command
     * @return a regex-pattern
     */
    public abstract String getPattern();

    public abstract List<Parameter<?>> getParameters();

     public abstract Result exec(List<Parameter<?>> parameters);

    /**
     * A command has cannot have a message to be displayed before accessing. Since commands get called by the user it is
     * impossible to display such a message.
     * @return null, no message
     */
    public String getMessage() {
        return null;
    }

}

