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

    @Override
    public abstract Result execute();

    public abstract String getHelpText();

}

