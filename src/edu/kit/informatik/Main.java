package edu.kit.informatik;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.ui.Output;
import edu.kit.informatik.ui.session.Session;
import edu.kit.informatik.util.GameSettings;
import edu.kit.informatik.util.exception.ParameterException;

public class Main {

    public static void main(String[] args) {
        try {
            GameSettings gameSettings = new GameSettings(args);
            AlphaZeta newGame = new AlphaZeta(gameSettings);
            Session session = new Session(newGame);
            session.runSession();
        } catch (ParameterException e) {
            Output.printError(e.getMessage());
        }
    }
}
