package com.qub.Technopoly.tile;

import com.qub.Technopoly.actions.group.ActionGroup;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.FreeParkingConfig;
import com.qub.Technopoly.io.IOHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static com.qub.Technopoly.io.IOHelper.getOutputSource;
import static java.lang.String.format;

@RequiredArgsConstructor
public class FreeParking implements Tile {

    private static final String DESCRIPTION = "Free parking in this street!";

    @NonNull
    private final FreeParkingConfig freeParkingConfig;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return freeParkingConfig.getName();
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
    public void onPass(Actor actor) {
        // Do Nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionGroup onLand(Actor actor, Board board) {
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
