package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.Game;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.qub.Technopoly.config.Config.getConfig;
import static java.lang.String.format;

/**
 * {@inheritDoc}
 * Exits the game / Terminates the program
 */
@RequiredArgsConstructor
public class ExitGameAction implements Action {

    private static final String EXECUTE_MESSAGE = "Thanks for playing Technopoly! See you later.";
    private static final String WINNERS_MESSAGE_FORMAT =
        "Congratulations %s! You win with a balance of %s %s!";

    private static final String NAME = "Exit Game";
    private static final String DESCRIPTION = null;

    private final OutputSource outputSource = IOHelper.getOutputSource();

    @NonNull
    private final Game game;

    /**
     * {@inheritDoc}
     */
    @Override
    public @NonNull String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() {
        if (game.isGameRunning()) {

            var winners = game.getBoard().getActorsWithMostMoney();

            if (winners != null && winners.size() > 0) {
                var highestScore = winners.get(0).getInventory().getCurrentBalance();
                if (winners.size() == 1) {
                    var winner = winners.get(0);
                    outputSource.writeTitle(
                        format(WINNERS_MESSAGE_FORMAT, winner.getActorName(), highestScore,
                            getConfig().getInventoryConfig().getCurrencyName()));
                } else {
                    var winString = "";
                    for (var i = 0; i < winners.size(); i++) {
                        winString += winners.get(i).getActorName();

                        if (i < winners.size() - 1) {
                            winString += " and ";
                        }
                    }

                    outputSource.writeTitle(format(WINNERS_MESSAGE_FORMAT, winString, highestScore,
                        getConfig().getInventoryConfig().getCurrencyName()));
                }
            }

            game.stop();
        }

        outputSource.writeTitle(EXECUTE_MESSAGE);
        return true;
    }
}
