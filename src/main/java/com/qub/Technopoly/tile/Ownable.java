package com.qub.Technopoly.tile;

import com.qub.Technopoly.actor.Actor;

public interface Ownable {
    /**
     * Get the name of the ownable
     * @return The name of the ownable
     */
    String getName();

    /**
     * Get the {@link Actor} that owns this ownable
     * @return The owner of the ownable
     */
    Actor getOwner();
}
