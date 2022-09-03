package edu.kit.informatik.ui.session.initialisation;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.resources.fleet.Battleship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.interaction.Interaction;
import edu.kit.informatik.ui.interaction.ShipInit;
import edu.kit.informatik.ui.parser.ParameterParser;
import edu.kit.informatik.ui.session.Dialog;
import edu.kit.informatik.ui.session.Session;
import edu.kit.informatik.util.exception.InputException;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.List;

public class FleetInitDialog extends Dialog {
    private final List<Battleship> unfinishedShips;

    private static final String PLAYER_PROMPT = " configures its ships";
    private final Player player;
    private Interaction currentInteraction;

    public FleetInitDialog(Player player, AlphaZeta game, Session session) {
        super(game, session);
        this.player = player;
        this.unfinishedShips = player.getFleet().getAllBattleships();
        Battleship battleship = this.unfinishedShips.get(0);
        this.currentInteraction = new ShipInit(player.getFleet(), battleship.getId());
    }

    @Override
    public String getInitialMessage() { return this.player.toString() + PLAYER_PROMPT; }

    @Override
    public String getDialogMessage() {
        return currentInteraction.getMessage();
    }


    @Override
    public Result executeStep(String input) {
        try {
            ParameterParser.processParameter(this.currentInteraction, input, UtilStrings.getComma());
        } catch (InputException e) {
            return new Result(false, e.getMessage());
        }

        Result initResult = this.currentInteraction.execute();
        if (initResult.isSuccess()) {
            this.unfinishedShips.remove(0);
            if (this.unfinishedShips.isEmpty()) {
                this.endDialog();
                return initResult;
            }
        }
        Battleship battleship = this.unfinishedShips.get(0);
        this.currentInteraction = new ShipInit(this.player.getFleet(), battleship.getId());
        return initResult;
    }
}
