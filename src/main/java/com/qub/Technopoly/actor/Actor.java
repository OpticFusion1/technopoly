package com.qub.Technopoly.actor;

import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.inventory.Inventory;

public interface Actor {
    /**
     * Check whether it is this actors turn
     * @return
     */
    boolean IsActive();

    /**
     * Set this Actor active, i.e., it is this actor's turn.
     * @param board The board this actor is activated on
     */
    void SetActive(Board board);

    /**
     * Set this Actor inactive, i.e., it is no longer this actor's turn.
     */
    void SetInactive();

    /**
     * update the Actor
     * @param board The board this actor exists on
     * @return True if the actor updated, False is the actor did not update
     */
    boolean Update(Board board);

    /**
     * @return The actor's name
     */
    String getActorName();

    /**
     * @return The actor's inventory
     */
    Inventory getInventory();

    /**
     * Check whether or not an actor is bankrupt or not
     * @return
     */
    boolean isBankrupt();
}
