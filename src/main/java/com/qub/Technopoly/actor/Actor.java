package com.qub.Technopoly.actor;

import com.qub.Technopoly.Game;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.inventory.Inventory;

public interface Actor {
    /**
     * Check whether it is this actors turn
     * @return Whether the actor is active or not
     */
    boolean isActive();

    /**
     * Set this Actor active, i.e., it is this actor's turn.
     * @param game The game instance
     */
    void setActive(Game game);

    /**
     * Set this Actor inactive, i.e., it is no longer this actor's turn.
     */
    void setInactive();

    /**
     * update the Actor
     * @param game The game instance
     * @return True if the actor updated, False is the actor did not update
     */
    boolean update(Game game);

    /**
     * @return The actor's name
     */
    String getActorName();

    /**
     * @return The actor's inventory
     */
    Inventory getInventory();

    /**
     * Set a player to bankrupt
     */
    void setBankrupt();
}
