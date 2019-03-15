package com.qub.Technopoly.actor;

import com.qub.Technopoly.Game;
import com.qub.Technopoly.actions.action.ExitGameAction;
import com.qub.Technopoly.actions.group.NewTurnActionGroup;
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

    private Game game;

    private boolean isActive = false;
    private NewTurnActionGroup newTurn;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        return isActive;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActive(Game game) {
        isActive = true;
        this.game = game;

        if (newTurn == null) {
            newTurn = new NewTurnActionGroup(this, game);
        }

        newTurn.describe();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInactive() {
        isActive = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Game game) {
        this.game = game;

        if (!isActive()) {
            throw new GameStateException(
                "Can't update actor " + actorName + " because it isn't that actors turn!");
        }

        if (newTurn == null) {
            newTurn = new NewTurnActionGroup(this, game);
        }

        return newTurn.execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBankrupt() {
        if(this.game == null){
            throw new GameStateException("Game is null");
        }
        new ExitGameAction(game).execute();
    }
}
