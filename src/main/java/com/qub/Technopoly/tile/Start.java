package com.qub.Technopoly.tile;

import com.qub.Technopoly.actions.group.ActionGroup;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.StartConfig;
import com.qub.Technopoly.io.IOHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@RequiredArgsConstructor
public class Start implements Tile {

    private static final String DESCRIPTION_FORMAT = "Pass to earn %s %s!";

    private static final String PASSED_START_MESSAGE_FORMAT =
        "You passed Start! You got %s %s.\nYour new balance is %s %s.";

    @NonNull
    private final StartConfig startConfig;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return startConfig.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return format(DESCRIPTION_FORMAT, startConfig.getStartPassBonus(),
            Config.getConfig().getInventoryConfig().getCurrencyName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPass(Actor actor) {
        var inventory = actor.getInventory();
        var inventoryConfig = Config.getConfig().getInventoryConfig();

        actor.getInventory().add(startConfig.getStartPassBonus());
        IOHelper.getOutputSource().writeBody(
            format(PASSED_START_MESSAGE_FORMAT, startConfig.getStartPassBonus(),
                inventoryConfig.getCurrencyName(), inventory.getCurrentBalance(),
                inventoryConfig.getCurrencyName()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionGroup onLand(Actor actor, Board board) {
        // TODO - Add money to Actor?
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + getName() + "]";
    }
}
