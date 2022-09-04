package edu.kit.informatik.ui.session.initialisation;

import edu.kit.informatik.game.logic.AlphaZeta;
import edu.kit.informatik.game.logic.Player;
import edu.kit.informatik.game.resources.board.TileTypes;
import edu.kit.informatik.game.resources.fleet.Spaceship;
import edu.kit.informatik.ui.Result;
import edu.kit.informatik.ui.interaction.BoardInit;
import edu.kit.informatik.ui.interaction.Interaction;
import edu.kit.informatik.ui.parser.ParameterParser;
import edu.kit.informatik.ui.session.Dialog;
import edu.kit.informatik.util.exception.InputException;
import edu.kit.informatik.util.strings.UtilStrings;

/**
 * This dialog handles the board-initialisation through a user-generated configuration. The dialog concludes after a
 * valid board was configured. The BoardInit Interaction handles direct user interaction.
 * @author uppyo
 * @version 1.0
 */
public class BoardInitDialog extends Dialog {
    private final Interaction currentInteraction;
    private final AlphaZeta game;

    /**
     * The dialog is initialised using the game and session.The BoardInit interaction is initialised.
     * @param game current game, in configuration
     */
    public BoardInitDialog(AlphaZeta game) {
        super();
        this.currentInteraction = new BoardInit(game);
        this.game = game;
    }

    private String generateInitialMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Use").append(UtilStrings.getWhitespace());
        stringBuilder.append(TileTypes.FREE).append(" for free tiles;").append(UtilStrings.getWhitespace());
        stringBuilder.append(TileTypes.HALF_COVER).append(UtilStrings.getComma())
                .append(TileTypes.FULL_COVER).append(" for cover;").append(UtilStrings.getWhitespace());
        for (Player player: this.game.getPlayers()) {
            stringBuilder.append(player.getFleet().getCollector().getId()).append(UtilStrings.getComma());
            for (Spaceship spaceship: player.getFleet().getAllBattleships()) {
                stringBuilder.append(spaceship.getId()).append(UtilStrings.getComma());
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(UtilStrings.getComma()));
        stringBuilder.append(" for ships");
        return stringBuilder.toString();
    }

    @Override
    public String getInitialMessage() {
        return generateInitialMessage();
    }

    @Override
    public String getDialogMessage() {
        return this.currentInteraction.getMessage();
    }

    @Override
    public Result executeStep(String userInput) {
        try {
            ParameterParser.processParameter(this.currentInteraction, userInput, UtilStrings.getWhitespace());
        } catch (InputException e) {
            return new Result(false, e.getMessage());
        }
        Result result = this.currentInteraction.execute();
        if (result.isSuccess()) {
            this.endDialog();
        }
        return this.currentInteraction.execute();
    }
}
