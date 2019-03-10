package com.qub.Technopoly.actor;

import com.qub.Technopoly.actions.category.NewTurnActionCategory;
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
    private NewTurnActionCategory newTurn;


    @Override
    public boolean IsActive() {
        return isActive;
    }

    @Override
    public void SetActive() {
        isActive = true;

        if (newTurn == null) {
            newTurn = new NewTurnActionCategory(this);
        }

        newTurn.describe();
    }

    @Override
    public void SetInactive() {
        isActive = false;
    }

    @Override
    public boolean Update() {
        if (!IsActive()) {
            throw new GameStateException(
                "Can't update actor " + actorName + " because it isn't that actors turn!");
        }

        if (newTurn == null) {
            newTurn = new NewTurnActionCategory(this);
        }

        return newTurn.execute();
    }
}
