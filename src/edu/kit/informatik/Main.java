package edu.kit.informatik;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.ui.Output;
import edu.kit.informatik.ui.session.Session;
import edu.kit.informatik.util.GameSettings;
import edu.kit.informatik.util.exception.ParameterException;

/**
 * Main-methode of AlphaZeta
 * @author uppyo
 * @version 1.0
 */
public class Main {

    /**
     * Initialise the game-settings using CLI, then create a game and start a session.
     * If CLI were wrong, an error-message is displayed and then the execution is stopped
     * @param args CLI-arguments
     */
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
