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
import edu.kit.informatik.ui.session.Session;
import edu.kit.informatik.util.exception.InputException;
import edu.kit.informatik.util.strings.UtilStrings;

public class BoardInitDialog extends Dialog {
    private final Interaction currentInteraction;

    private String generateInitialMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Use").append(UtilStrings.getWhitespace());
        stringBuilder.append(TileTypes.FREE.toString()).append(" for free tiles;").append(UtilStrings.getWhitespace());
        stringBuilder.append(TileTypes.HALF_COVER.toString()).append(UtilStrings.getComma())
                .append(TileTypes.FULL_COVER.toString()).append(" for cover;").append(UtilStrings.getWhitespace());
        for (Player player: super.getCurrentGame().getPlayers()) {
            stringBuilder.append(player.getFleet().getCollector().getId()).append(UtilStrings.getComma());
            for (Spaceship spaceship: player.getFleet().getAllBattleships()) {
                stringBuilder.append(spaceship.getId()).append(UtilStrings.getComma());
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(UtilStrings.getComma()));
        stringBuilder.append(" for ships");
        return stringBuilder.toString();
    }

    public BoardInitDialog(AlphaZeta game, Session session) {
        super(game, session);
        this.currentInteraction = new BoardInit(game);
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
