package edu.kit.informatik.ui.session;

import edu.kit.informatik.game.AlphaZeta;
import edu.kit.informatik.game.Player;
import edu.kit.informatik.ui.Output;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.command.Command;
import edu.kit.informatik.ui.command.Quit;
import edu.kit.informatik.ui.parser.CommandParser;
import edu.kit.informatik.ui.session.initialisation.BoardInitDialog;
import edu.kit.informatik.ui.session.initialisation.FleetInitDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Abstract description of a session. A session has a internal "running" value.
 * @author uppyo
 * @version 1.0
 */
public class Session {
    private static final String WELCOME = "Welcome to AlphaZeta!";
    protected AlphaZeta currentGame;
    private final List<Command> commandList;
    private boolean running;
    private final Scanner scanner;
    private Dialog activeDialog;

    /**
     * Initialise a session by setting "running" as true and adding the quit command
     */
    public Session(AlphaZeta currentGame) {
        this.running = true;
        this.currentGame = currentGame;
        this.commandList = new ArrayList<>();
        this.commandList.add(new Quit(this));
        this.scanner = new Scanner(System.in);
    }

    /**
     * check if the session is running
     * @return true if "running" was set
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * quit a session by setting "running" as false
     */
    public void quit() {
        this.running = false;
    }

    /**
     * Run the session, when implemented the session should be exited when "session.quit" is called.
     */
    public void runSession() {
        Output.printMessage(WELCOME);
        // initialise fleets
        /**
        for (Player player: currentGame.getPlayers()) {
            if (this.running) {
                this.activeDialog = new FleetInitDialog(player, currentGame, this);
                runDialog();
            }
        }**/
        if (this.running) {
            this.activeDialog = new BoardInitDialog(currentGame, this);
            runDialog();
        }
        do {


        } while (this.running);
    }

    private void runDialog() {
        Output.printMessage(this.activeDialog.getInitialMessage());
        do {
            Result inputResult = null;
            Output.printMessage(this.activeDialog.getDialogMessage());
            String userInput = this.scanner.nextLine();
            inputResult = CommandParser.processCommand(userInput, this.commandList);
            // if no registered command was called proceed with dialog
            if (inputResult == null) {
                inputResult = this.activeDialog.executeStep(userInput);
            }
            Output.printResult(inputResult);
        } while (!activeDialog.isFinished() && this.running);
    }


}
