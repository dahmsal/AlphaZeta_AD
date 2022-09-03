package edu.kit.informatik.ui.session.gameplay;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.game.resources.modules.Module;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.interaction.ShipAction;
import edu.kit.informatik.ui.parser.ParameterParser;
import edu.kit.informatik.ui.session.Dialog;
import edu.kit.informatik.ui.session.Session;
import edu.kit.informatik.util.exception.InputException;
import edu.kit.informatik.util.strings.UtilStrings;

public class PlayerTurn extends Dialog {
    private static final String INIT_MESSAGE = "'s turn";
    private final Player player;
    private ShipAction shipAction;
    private Session session;
    private AlphaZeta game;

    public PlayerTurn(Player player, AlphaZeta game, Session session) {
        super(game, session);
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
        this.shipAction = new ShipAction(game, player);
        try {
            ParameterParser.processParameter(this.shipAction, userInput, UtilStrings.getWhitespace());
        } catch (InputException e) {
            return new Result(false, e.getMessage());
        }
        Result result = this.shipAction.execute();
        if (result.isSuccess() && this.shipAction.hasDecider()) {
            ProcessAttack processAttack
                    = new ProcessAttack(this.shipAction.getDecider(), this.game, this.session, this);
            this.session.setActiveDialog(processAttack);
            return new Result(result.isSuccess(), result.getResultMessage() + UtilStrings.getLinebreak()
                    + processAttack.getInitialMessage());
        }
        return result;
    }
}
