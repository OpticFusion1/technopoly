package com.qub.Technopoly.tile;

import com.qub.Technopoly.actions.group.ActionGroup;
import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.board.Board;

public interface Tile {
    String getName();

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

    String toString();
}
