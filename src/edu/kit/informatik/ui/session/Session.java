package edu.kit.informatik.ui.session;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.ui.Output;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.command.ActionsCommand;
import edu.kit.informatik.ui.command.BoardCommand;
import edu.kit.informatik.ui.command.Command;
import edu.kit.informatik.ui.command.EndTurnCommand;
import edu.kit.informatik.ui.command.FleetCommand;
import edu.kit.informatik.ui.command.Help;
import edu.kit.informatik.ui.command.Quit;
import edu.kit.informatik.ui.interaction.ShipAction;
import edu.kit.informatik.ui.parser.CommandParser;
import edu.kit.informatik.ui.session.gameplay.PlayerTurn;
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

    public Dialog getActiveDialog() {
        return activeDialog;
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
        for (Player player: this.currentGame.getPlayers()) {
            this.activeDialog = new FleetInitDialog(player, this.currentGame, this);
            runDialog();
        }
        //initialise board
        this.activeDialog = new BoardInitDialog(currentGame, this);
        runDialog();

        //run the game
        this.commandList.add(new BoardCommand(this.currentGame.getBoard()));
        this.commandList.add(new FleetCommand(this.currentGame.getPlayers()));
        this.commandList.add(new ActionsCommand(this.currentGame));
        this.commandList.add(new EndTurnCommand(this));
        this.commandList.add(new Help(this.commandList));

        while (this.running) {
            List<Player> players = new ArrayList<>(this.currentGame.getPlayers());
            for (Player player: players) {
                this.activeDialog = new PlayerTurn(player, this.currentGame, this);
                runDialog();
            }
        }
    }

    private void runDialog() {
        if (this.running) {
            Output.printMessage(this.activeDialog.getInitialMessage());
        }
        while (!activeDialog.isFinished() && this.running) {
            Result inputResult = null;
            Output.printMessage(this.activeDialog.getDialogMessage());
            String userInput = null;
            if(!activeDialog.isFinished()) {
                userInput = this.scanner.nextLine();
            }
            inputResult = CommandParser.processCommand(userInput, this.commandList);
            // if no registered command was called proceed with dialog
            if (inputResult == null) {
                inputResult = this.activeDialog.executeStep(userInput);
            }
            Output.printResult(inputResult);
        }
    }

    public void setActiveDialog(Dialog activeDialog) {
        this.activeDialog = activeDialog;
    }
}
