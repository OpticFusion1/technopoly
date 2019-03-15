package com.qub.Technopoly.tile;

import com.qub.Technopoly.actions.group.ActionGroup;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.board.Board;

public interface Tile {

    /**
     * Get the name of the tile
     * @return The name of the tile
     */
    String getName();

    /**
     * Get a flavour description of the tile
     * @return The description of the tile
     */
    String getDescription();

    /**
     * Called when an Actor passes the tile
     */
    void onPass(Actor actor);

    /**
     * Called when an Actor lands on the tile
     * Returns an {@link ActionGroup} with possible options for the tile
     */
    ActionGroup onLand(Actor actor, Board board);

    /**
     * A human readable string description of the {@link Tile}
     * @return A string description of the tile
     */
    String toString();
}
