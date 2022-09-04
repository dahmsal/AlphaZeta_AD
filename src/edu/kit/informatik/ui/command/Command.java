package edu.kit.informatik.ui.command;


import java.util.List;

import edu.kit.informatik.ui.Parsable;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.ui.Result;

/**
 * Description of a command
 * A command must have a regex-pattern and (optional) parameters and an execution-method
 * @author uppyo
 * @version 1.1
 */
public abstract class Command implements Parsable {

    /**
     * get the regex-pattern of the command
     * @return a regex-pattern
     */
    public abstract String getPattern();

    @Override
    public abstract List<Parameter<?>> getParameters();

    /**
     * Execute the command and generate a result-object
     * @return result of execution
     */
    public abstract Result execute();

    /**
     * Get the help-text of a command. A help-text must contain the pattern of the command as well as a
     * short description
     * @return help-text as String
     */
    public abstract String getHelpText();

}

