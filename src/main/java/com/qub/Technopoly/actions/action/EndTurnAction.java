package com.qub.Technopoly.actions.action;

import lombok.NonNull;

/**
 * {@inheritDoc}
 * Used to end a users turn
 */
public class EndTurnAction implements Action {

    private static final String NAME = "End Turn";
    private static final String DESCRIPTION = "End your turn";

    /**
     * {@inheritDoc}
     */
    @Override
    public @NonNull String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() {
        return true;
    }
}
