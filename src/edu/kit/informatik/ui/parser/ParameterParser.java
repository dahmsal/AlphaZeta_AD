package edu.kit.informatik.ui.parser;

import edu.kit.informatik.ui.Parsable;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.util.exception.InputException;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parsing logic for parameters
 * @author uppyo
 * @version 1.2
 */
public final class ParameterParser {

    private ParameterParser() { }

    /**
     * Process a parsable object and parse all parameters of the object. When a parameter is successfully processed,
     * the value of the parameter will be set. Otherwise a parameter will have an internal value of NULL. If the input
     * deviates from convention a appropriate error-message will be generated.
     * @param parsable A parsable object, f.E a interaction or a command
     * @param inputString user-input arguments
     * @param delimiter the delimiter separating the arguments
     * @throws InputException if the user-input is faulty
     */
    public static void processParameter(Parsable parsable, String inputString, String delimiter) throws InputException {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString, delimiter);
        for (Parameter<?> parameter: parsable.getParameters()) {
            parameter.clearParameter();
        }
        for (Parameter<?> parameter: parsable.getParameters()) {
            Pattern pattern = Pattern.compile(parameter.getPattern(), Pattern.CASE_INSENSITIVE);
            if (stringTokenizer.hasMoreTokens()) {
                String token = stringTokenizer.nextToken();
                Matcher matcher = pattern.matcher(token);
                if (matcher.matches()) {
                    parameter.setValue(matcher.group(0));
                } else {
                    throw new InputException("Input: \"" + token + " \" could not be parsed");
                }
            }
        }
        if (stringTokenizer.hasMoreTokens()) {
            StringBuilder message = new StringBuilder("wrong number of inputs. Input(s): ");
            while (stringTokenizer.hasMoreTokens()) {
                message.append(stringTokenizer.nextToken());
                message.append(UtilStrings.getComma());
            }
            message.append(" were excessive!");
            throw new InputException(message.toString());
        }
    }
}
