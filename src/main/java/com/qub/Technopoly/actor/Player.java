package com.qub.Technopoly.actor;

import com.qub.Technopoly.actions.group.NewTurnActionGroup;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.exception.GameStateException;
import com.qub.Technopoly.inventory.Inventory;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Player implements Actor {

    @Getter
    @NonNull
    private final String actorName;
    @Getter
    @NonNull
    private final Inventory inventory;

    private boolean isActive = false;
    private NewTurnActionGroup newTurn;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean IsActive() {
        return isActive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void SetActive(Board board) {
        isActive = true;

        if (newTurn == null) {
            newTurn = new NewTurnActionGroup(this, board);
        }

        newTurn.describe();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void SetInactive() {
        isActive = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean Update(Board board) {
        if (!IsActive()) {
            throw new GameStateException(
                "Can't update actor " + actorName + " because it isn't that actors turn!");
        }

        if (newTurn == null) {
            newTurn = new NewTurnActionGroup(this, board);
        }

        return newTurn.execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBankrupt() {
        return inventory.getCurrentBalance() < 0;
    }
}
