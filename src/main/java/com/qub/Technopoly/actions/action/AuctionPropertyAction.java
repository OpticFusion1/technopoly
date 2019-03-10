package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.InputSource;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Property;
import com.qub.Technopoly.util.Field;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static java.lang.String.format;

@RequiredArgsConstructor
public class AuctionPropertyAction implements Action {

    private static String EXECUTION_MESSAGE_TITLE_FORMAT = "Action for %s (Field: %s) started!";
    private static String EXECUTION_MESSAGE_DESCRIPTION_FORMAT = "Starting bid is %s %s.";
    private static String EXECUTION_PROMPT_MESSAGE_FORMAT = "Would you like to bid %s? (y/N)";
    private static String EXECUTION_BID_MESSAGE_FORMAT =
        "%s, please enter your bid. (Minimum %s %s)";
    private static String BID_TOO_SMALL_ERROR = "Sorry, this bid is too small";
    private static String NOT_ENOUGH_MONEY_FORMAT = "Sorry %s, you do not have enough money";

    private static String NAME = "Auction Property";
    private static String DESCRIPTION = "Don't want the property? Can't afford it? Auction it!";

    private static final String CURRENT_BID_MESSAGE_FORMAT = "Current bid is %s %s by %s.";
    private static final String AUCTION_WON_MESSAGE_FORMAT =
        "Congratulations %s! You won the auction!";
    private static final String NO_WINNER_MESSAGE = "Nobody bid. Property not sold.";

    @NonNull
    private final Property property;

    @NonNull
    private final Board board;

    private final OutputSource outputSource = IOHelper.getOutputSource();

    private final InputSource inputSource = IOHelper.getInputSource();

    @Override
    public @NonNull String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public boolean execute() {
        outputSource.writeTitle(format(EXECUTION_MESSAGE_TITLE_FORMAT, property.getName(),
            Field.getFieldForProperty(property).getName()));

        var startingBid = Math.round(property.getPrice() * 0.1f);
        var currency = Config.getConfig().getInventoryConfig().getCurrencyName();
        outputSource.writeBody(format(EXECUTION_MESSAGE_DESCRIPTION_FORMAT, startingBid, currency));

        var done = false;
        var actors = Arrays.asList(board.getActors());

        var currentBid = startingBid;
        Actor currentWinner = null;
        while (!done) {
            done = true;
            for (var actor : actors) {

                if (actor == currentWinner)
                    continue;

                if (currentBid >= actor.getInventory().getCurrentBalance()) {
                    outputSource.writeBody(format(NOT_ENOUGH_MONEY_FORMAT, actor));
                } else {
                    outputSource
                        .writeBody(format(EXECUTION_PROMPT_MESSAGE_FORMAT, actor.getActorName()));
                }

                if (wantsToBid(inputSource.getNextString())) {
                    var selectedBid = 0;
                    do {
                        outputSource.writeBody(
                            format(EXECUTION_BID_MESSAGE_FORMAT, actor.getActorName(),
                                currentBid + 1, currency));
                        selectedBid = inputSource.getNextInt();
                        if (currentBid >= selectedBid) {
                            outputSource.writeBody(BID_TOO_SMALL_ERROR);
                        }
                        if (selectedBid > actor.getInventory().getCurrentBalance()) {
                            outputSource.writeBody(format(NOT_ENOUGH_MONEY_FORMAT, actor.getActorName()));
                            selectedBid = 0;
                        }

                    } while (currentBid >= selectedBid);

                    currentBid = selectedBid;
                    outputSource.writeBody(format(CURRENT_BID_MESSAGE_FORMAT, currentBid, currency,
                        actor.getActorName()));
                    done = false;

                    currentWinner = actor;
                }
            }
        }

        if (currentWinner == null) {
            outputSource.writeBody(NO_WINNER_MESSAGE);
            return true;
        }

        outputSource.writeTitle(format(AUCTION_WON_MESSAGE_FORMAT, currentWinner.getActorName()));
        new BuyPropertyAction(currentWinner, property).execute();
        return true;
    }

    private boolean wantsToBid(String selected) {
        return selected.equalsIgnoreCase("y");
    }
}
