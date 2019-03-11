package com.qub.Technopoly.tile;

import com.qub.Technopoly.actions.group.ActionGroup;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.board.Board;

public interface Tile {

    /**
     * Get the name of the tile
     * @return
     */
    String getName();

    /**
     * Get a flavour description of the tile
     * @return
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
     * @return
     */
    String toString();
}
