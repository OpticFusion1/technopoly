package com.qub.Technopoly.actions.action;

import lombok.NonNull;

/**
 * {@inheritDoc}
 * Used to go back without ending a users turn
 */
public class BackAction implements Action {

    private static final String NAME = "Back";
    private static final String DESCRIPTION = "Go back to main menu";

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
        return false;
    }
}
