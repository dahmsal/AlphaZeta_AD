package edu.kit.informatik.ui.session.gameplay;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.interaction.ShipAction;
import edu.kit.informatik.ui.parser.ParameterParser;
import edu.kit.informatik.ui.session.Dialog;
import edu.kit.informatik.ui.session.Session;
import edu.kit.informatik.util.exception.InputException;
import edu.kit.informatik.util.strings.UtilStrings;

/**
 * The player-turn dialog handles possible action inputs from the player and activates the attack-processing pipeline,
 * if necessary. The player-turn dialog cannot end itself, it has to be ended externally either through the end-turn
 * command or if the game ends.
 * @author uppyo
 * @version 1.0
 */
public class PlayerTurn extends Dialog {
    private static final String INIT_MESSAGE = "'s turn";
    private final Player player;
    private final Session session;
    private final AlphaZeta game;

    /**
     * Initialise the player-turn by setting the current player and resetting the modules of all ships in fleet.
     * @param player current player that has the turn
     * @param game currently played game
     * @param session current session
     */
    public PlayerTurn(Player player, AlphaZeta game, Session session) {
        super();
        this.game = game;
        this.session = session;
        this.player = player;
        //reset modules
        for (Spaceship spaceship: player.getFleet().getAllSpaceships()) {
            spaceship.rearmShip();
        }
    }

    @Override
    public String getInitialMessage() {
        return this.player.toString() + INIT_MESSAGE;
    }

    @Override
    public String getDialogMessage() {
        return null;
    }

    @Override
    public Result executeStep(String userInput) {
        // reset ship-action
        ShipAction shipAction = new ShipAction(game, player);
        try {
            ParameterParser.processParameter(shipAction, userInput, UtilStrings.getWhitespace());
        } catch (InputException e) {
            return new Result(false, e.getMessage());
        }
        Result result = shipAction.execute();
        if (result.isSuccess() && shipAction.hasDecider()) {
            ProcessAttack processAttack
                    = new ProcessAttack(shipAction.getDecider(), this.game, this.session, this);
            this.session.setActiveDialog(processAttack);
            return new Result(result.isSuccess(), result.getResultMessage() + UtilStrings.getLinebreak()
                    + processAttack.getInitialMessage());
        }
        return result;
    }
}
