package edu.kit.informatik.ui;

import edu.kit.informatik.ui.parameter.Parameter;

import java.util.List;

/**
 * Describes all user-interactions that can be parsed, common implementations are commands and interaction-chains
 * A parsable interaction has to include a list of parameters and an execution methode that returns a Result-type object
 * @author uppyo
 * @version 1.0
 */
public interface Parsable {

    /**
     * get a list of parameters
     * @return parameters in correct order, is empty if the command has no parameters
     */
    List<Parameter<?>> getParameters();

}
