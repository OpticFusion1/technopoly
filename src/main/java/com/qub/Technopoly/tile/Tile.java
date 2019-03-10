package com.qub.Technopoly.tile;

import com.qub.Technopoly.actions.category.ActionCategory;
import com.qub.Technopoly.actor.Actor;

public interface Tile {
    String getName();

    String getDescription();

    /**
     * Called when an Actor passes the tile
     */
    void onPass(Actor actor);

    /**
     * Called when an Actor lands on the tile
     * Returns an {@link ActionCategory} with possible options for the tile
     */
    ActionCategory onLand(Actor actor);
}
