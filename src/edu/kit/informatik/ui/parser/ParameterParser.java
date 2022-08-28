package edu.kit.informatik.ui.parser;

import edu.kit.informatik.ui.Output;
import edu.kit.informatik.ui.Parsable;
import edu.kit.informatik.ui.parameter.Parameter;
import edu.kit.informatik.util.exception.InputException;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterParser {

    private ParameterParser() { }

    public static void processParameter(Parsable parsable, String inputString, String delimiter) throws InputException {
        String temp = inputString;
        StringTokenizer stringTokenizer = new StringTokenizer(inputString, delimiter);
        for (Parameter<?> parameter: parsable.getParameters()) {
            parameter.clearParameter();
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
        //Pattern additionalArgs = Pattern.compile("[a-zA-Z\\d]+");
        //Matcher matcher = additionalArgs.matcher(temp);
        //if (matcher.find()) {
        //    throw new InputException("Input: \"" + temp + " \" could not be parsed");
        //}
    }
}
