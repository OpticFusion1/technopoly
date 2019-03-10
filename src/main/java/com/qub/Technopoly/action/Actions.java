package com.qub.Technopoly.action;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.tile.Property;
import com.qub.Technopoly.tile.Tile;

public class Actions {
    private Actions() {
        // Prevent instantiation
    }

    /**
     * Gets the action that corresponds to this tile for the specified {@link Actor}
     *
     * @param actor The actor to get the action for (The current active actor)
     * @param tile  The tile the actor is on (landed on)
     * @return The action representing this tile, for the specific actor
     */
    public Action getTileAction(Actor actor, Tile tile) {
        if (tile instanceof Property) {
            var property = (Property) tile;
            var owner = property.getOwner();

            return owner == null ? new UnownedPropertyAction(actor, property) : new OwnedPropertyAction(actor, property);
        }
        return null;
    }
}
