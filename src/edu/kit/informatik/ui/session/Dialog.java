package edu.kit.informatik.ui.session;

import edu.kit.informatik.game.AlphaZeta;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.command.Command;
import edu.kit.informatik.ui.command.Quit;
import edu.kit.informatik.ui.parameter.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Dialog {
    private final AlphaZeta currentGame;
    private final Session currentSession;
    private final List<Command> commandList;
    private final List<Parameter<?>> parameterList;
    private boolean isRunning;

    public Dialog(AlphaZeta game, Session session) {
        this.currentGame = game;
        this.currentSession = session;
        this.commandList = new ArrayList<>();
        this.parameterList = new ArrayList<>();
        this.commandList.add(new Quit(session));
        this.isRunning = true;
    }

    public AlphaZeta getCurrentGame() {
        return currentGame;
    }

    public abstract String getInitialMessage();

    public abstract String getDialogMessage();

    public abstract Result executeStep(String userInput);

    public boolean isFinished() {
        return !this.isRunning;
    }

    public void endDialog() {
        this.isRunning = false;
    }
}
