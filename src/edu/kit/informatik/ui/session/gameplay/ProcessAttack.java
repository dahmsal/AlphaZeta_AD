package edu.kit.informatik.ui.session.gameplay;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.actions.misc.Decider;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.game.resources.modules.misc.Engine;
import edu.kit.informatik.ui.Output;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.interaction.Interaction;
import edu.kit.informatik.ui.interaction.DestroyModules;
import edu.kit.informatik.ui.parser.ParameterParser;
import edu.kit.informatik.ui.session.Dialog;
import edu.kit.informatik.ui.session.Session;
import edu.kit.informatik.util.exception.InputException;
import edu.kit.informatik.util.exception.ParameterException;
import edu.kit.informatik.util.strings.StringComposer;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This Dialog handles the attack-processing after an attack was called. Using a decider-object the dialog will
 * determine if user-selection of modules is necessary and trigger an DestroyModules interaction. Then the modules
 * will be destroyed and an corresponding message will be generated. If a ship will be destroyed, it will be flagged and
 * removed from the board. If the destruction leads to a winning game, the winning message will be displayed and the
 * session will be terminated using the quit command. Otherwise the player-turn will continue
 * @author uppyo
 * @version 1.0
 */
public class ProcessAttack extends Dialog {
    private final Decider decider;
    private boolean choiceIsNeeded;
    private final List<Module> destroyedModules;
    private final Session session;
    private final PlayerTurn currentTurn;
    private final AlphaZeta game;
    private Interaction interaction;

    /**
     * The attack-processing requires a valid decider-object. If a player-choice is needed, the choiceIsNeeded flag will
     * be raised.
     * @param decider Decider, detailing the deciding player, destroyed modules and target-ship
     * @param game current game
     * @param session current session, so it can be immediately terminated when the game is over
     * @param currentTurn current PlayerTurn Dialog
     */
    public ProcessAttack(Decider decider, AlphaZeta game, Session session, PlayerTurn currentTurn) {
        super();
        this.decider = decider;
        this.session = session;
        this.game = game;
        this.currentTurn = currentTurn;
        this.choiceIsNeeded = true;
        this.destroyedModules = new ArrayList<>();
        //check if a choice is needed: total module count <= destroyed modules
        //  -> only one unique module-type (excluding engine)
        Set<Class<? extends Module>> uniqueModuleTypes;
        uniqueModuleTypes = this.decider.getTargetShip().getModules().stream()
                .map(Module::getClass).collect(Collectors.toSet());
        uniqueModuleTypes.remove(Engine.class);
        if (this.decider.getTargetShip().getModules().size() <= this.decider.getModuleCount()) {
            try {
                destroyShip();
            } catch (ParameterException e) {
                Output.printError(e.getMessage());
            }
            this.choiceIsNeeded = false;
        }
        else if (uniqueModuleTypes.size() <= 1) {
            while (this.destroyedModules.size() < this.decider.getModuleCount()) {
                this.destroyedModules.add(destroyModule(uniqueModuleTypes.stream().findFirst().orElse(null)));
            }
            this.choiceIsNeeded = false;
        } else if (this.decider.getModuleCount() == 0) {
            this.choiceIsNeeded = false;
        }

    }

    @Override
    public String getInitialMessage() {
        //check if dialog is needed
        if (this.choiceIsNeeded) {
            List<Module> relevantModules = new ArrayList<>(this.decider.getTargetShip().getModules());
            relevantModules.removeIf(module -> module.getClass().equals(Engine.class));
            String message
                    = this.decider.getDecidingPlayer().toString() + " must select " + this.decider.getModuleCount();
            message += " of " + this.decider.getTargetShip().getId() + "s module(s) (seperated by comma):\n";
            message += StringComposer.listToString(relevantModules);
            this.interaction = new DestroyModules(this.decider);
            return message;
        }
        //check for miss
        if (this.decider.getModuleCount() == 0) {
            endDialog();
            return "missed";
        }
        //No-Dialog destruction
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(StringComposer.destroyModuleOutput(this.destroyedModules,
                this.decider.getTargetShip().getId()));
        if (this.decider.getTargetShip().isDestroyed()) {
            stringBuilder.append(UtilStrings.getLinebreak());
            stringBuilder.append(this.decider.getTargetShip().getId()).append(UtilStrings.getWhitespace());
            stringBuilder.append("was destroyed").append(UtilStrings.getLinebreak());
            if (this.game.gameOver()) {
                stringBuilder.append((this.game.getWinner()));
                this.session.quit();
            }
        }
        endDialog();
        return stringBuilder.toString().trim();
    }

    @Override
    public String getDialogMessage() {
        return null;
    }

    @Override
    public Result executeStep(String userInput) {
        try {
            ParameterParser.processParameter(this.interaction, userInput, UtilStrings.getComma());
        } catch (InputException e) {
            return new Result(false, e.getMessage());
        }
        Result result = this.interaction.execute();
        if (result.isSuccess()) {
            endDialog();
        }
        return result;
    }

    private Module destroyModule(Class<? extends Module> type) {
        try {
            return this.decider.getTargetShip().destroyModuleType(type);
        } catch (ParameterException e) {
            return null;
        }
    }

    private void destroyShip() throws ParameterException {
        List<Module> shipModules = new ArrayList<>(this.decider.getTargetShip().getModules());
        for (Module module: shipModules) {
            this.destroyedModules.add(this.decider.getTargetShip().destroyModule(module));
        }
        this.decider.getTargetShip().destroy();
        this.game.getBoard().findSpaceship(this.decider.getTargetShip()).removeSpaceship();
    }

    @Override
    public void endDialog() {
        this.session.setActiveDialog(this.currentTurn);
        super.endDialog();
    }
}
